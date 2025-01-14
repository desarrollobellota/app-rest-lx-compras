package com.bellota.rest.lx.compras.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.ItemOrdenCompraDto;
import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.enums.CanalEnum;
import com.bellota.rest.lx.compras.maps.OrdenCompraMapper;
import com.bellota.rest.lx.compras.repositories.OrdenCompraRepository;
import com.bellota.rest.lx.compras.service.IOrdenCompraService;
import com.bellota.rest.lx.compras.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdenCompraServiceImpl implements IOrdenCompraService {

	private final ConectoresServiceImpl conectoresService;

	private final OrdenCompraRepository ordenCompraRepository;

	@Override
	public ResponseEntity<Object> actualizarFechasVencimientoOrdenes(String data) {
		log.info("Inicio metodo actualizarFechasVencimientoOrdenes:{} ", data);
		ResponseDto respuesta = new ResponseDto();
		try {
			Gson gson = new Gson();
            Type listType = new TypeToken<List<ItemOrdenCompraDto>>() {
             }.getType();
			List<ItemOrdenCompraDto> ordenes = gson.fromJson(data, listType);

			String descripcion = ordenes.isEmpty() ? Constantes.NO_INGRESO:"";
			int i = 0;
			while (i < ordenes.size()) {
				log.info("OC:" + ordenes.get(i).getNumeroOrdenCompra() + " Linea:" + ordenes.get(i).getLineaItem());
				if (ordenes.get(i).getFechaVencimiento() != null || ordenes.get(i).getFechaRequerido() != null
						|| ordenes.get(i).getFechaRecepcion() != null) {

					List<String> datos  =this.conectoresService.enviarMensaje(descripcion,
							OrdenCompraMapper.INSTANCE.mapearDocumento(ordenes.get(i)), ordenes.get(i),
							CanalEnum.ORDEN_COMPRA.getCodigo());
					descripcion = descripcion + datos.get(0);
				}
				i++;

			}
			respuesta.setEntidad(Constantes.ORDENCOMPREA);
			respuesta.setTitulo(Constantes.RESPUESTA);
			respuesta.setEstado(String.valueOf(HttpStatus.OK.value()));
			respuesta.setDetalle(descripcion);

			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			respuesta.setEntidad(Constantes.ORDENCOMPREA);
			respuesta.setTitulo(Constantes.RESPUESTA);
			respuesta.setEstado(String.valueOf(HttpStatus.NO_CONTENT.value()));
			respuesta.setDetalle(e.getLocalizedMessage());

			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> obtenerConteo() {
		log.info("Inicio metodo obtenerConteo: ");
		Integer cantidad = this.ordenCompraRepository.obtenerConteo();
	
		if (Objects.nonNull(cantidad) && cantidad>0) {
			log.info("Fin metodo obtenerConteo : {}", cantidad);
			return new ResponseEntity<Object>(String.valueOf(cantidad), HttpStatus.OK);
		} else {
			log.info("Fin metodo obtenerConteo : {}", cantidad);
            return new ResponseEntity<Object>(String.valueOf(cantidad), HttpStatus.OK);
        }
	}

}
