package com.bellota.rest.lx.compras.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.FacturaDto;
import com.bellota.rest.lx.compras.dtos.PedidoDto;
import com.bellota.rest.lx.compras.maps.FacturaMapper;
import com.bellota.rest.lx.compras.repositories.FacturaRepository;
import com.bellota.rest.lx.compras.service.IFacturaService;
import com.bellota.rest.lx.compras.utils.Constantes;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class FacturaServiceImpl implements IFacturaService {

	@Value("${servicios.factura.url}")
	private String url;

	@Value("${servicios.factura.autenticacion}")
	private String autenticacion;

	private final FacturaRepository facturaRepository;

	@Override
	public ResponseEntity<Object> procesarFacturas(List<FacturaDto> facturas) {
		log.info("Inicio metodo procesarFacturas");
		ArrayList<PedidoDto> pedidos = new ArrayList<>(0);
		String respuesta = "";
		if (Objects.nonNull(facturas) && !facturas.isEmpty()) {
			for (FacturaDto item : facturas) {
				respuesta += "Factura " + item + " = ";
				Object cabecera = this.facturaRepository.obtenerCabecera(item.getPrefijo(), item.getNumero());
				if (Objects.nonNull(cabecera)) {
					PedidoDto pedido = FacturaMapper.INSTANCE.mapearCabecera(cabecera, item);
					List<Object> lineas = this.facturaRepository.obtenerLineas(item.getPrefijo(), item.getNumero());

					if (Objects.nonNull(lineas) && !lineas.isEmpty()) {
						pedido.setLineas(FacturaMapper.INSTANCE.mapearLinea(lineas));
					} else {
						respuesta += Constantes.SIN_LINEAS;
					}
					// pedidos.add(pedido);
					try {
						PedidoDto pedidoNegativo = pedido.clonar();
						pedidoNegativo.setLineas(FacturaMapper.INSTANCE.mapearLineaNegativa(pedidoNegativo));
						pedidoNegativo.setPedido(item.getNumero());
						pedidoNegativo.setPrefijoDoc(item.getPrefijo());
						pedidos.add(pedidoNegativo);

					} catch (CloneNotSupportedException e) {
						log.error("Error: {} ", e);
					}
				} else {
					respuesta += Constantes.NO_HAY_RESULTADO;
				}
			}
		}

		log.info("respuesta: {} ", respuesta);
		String respuestaWS = "";
		if (!pedidos.isEmpty()) {
			for (PedidoDto item : pedidos) {
				respuestaWS = respuestaWS + " " + item.getPedido() + "-" + enviarData(item);
			}
		}
		return new ResponseEntity<Object>(respuestaWS, HttpStatus.OK);
	}

	@Override
	public String enviarData(PedidoDto pedido) {

		log.info("Inicio metodo enviarData");
		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(540, TimeUnit.SECONDS)
				.writeTimeout(540, TimeUnit.SECONDS).readTimeout(540, TimeUnit.SECONDS).build();

		Gson gson = new Gson();
		String cadenatToJson = gson.toJson(pedido);
		String res;

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, cadenatToJson);

		Request request = new Request.Builder().url(this.url).post(body).addHeader("Authorization", this.autenticacion)
				.addHeader("content-type", "application/json").addHeader("cache-control", "no-cache").build();
		okhttp3.Response response = null;

		try {
			response = client.newCall(request).execute();
			JSONObject json = new JSONObject(response.body().string());
			res = json.getString("detalle");
		} catch (JSONException ex) {
			log.error("Err:{} ", ex.getLocalizedMessage());
			return "Err";
		} catch (IOException ex) {
			log.error("Error:{} ", ex.getLocalizedMessage());
			return "Error";
		}
		return res;
	}

}
