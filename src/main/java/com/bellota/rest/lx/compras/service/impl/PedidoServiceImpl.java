package com.bellota.rest.lx.compras.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.PedidoDto;
import com.bellota.rest.lx.compras.entities.PedidoEntity;
import com.bellota.rest.lx.compras.enums.CanalEnum;
import com.bellota.rest.lx.compras.maps.PedidoMapper;
import com.bellota.rest.lx.compras.repositories.PedidoRepository;
import com.bellota.rest.lx.compras.service.IPedidoService;
import com.bellota.rest.lx.compras.utils.Constantes;
import com.bellota.rest.lx.compras.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoServiceImpl implements IPedidoService {

	private final ConectoresServiceImpl conectoresService;
	private final Utils utils;
	private final PedidoRepository pedidoRepository;

	@Override
	public ResponseEntity<Object> crearPedido(PedidoDto pedido) {
		log.info("Inicio metodo crearPedido");
		String descripcion = "";
		if (!validarPedido(pedido.getPedido())) {
			List<String> datos = conectoresService.enviarMensaje(descripcion,
					PedidoMapper.INSTANCE.mapearPedidoCliente(pedido), CanalEnum.PEDIDO.getCodigo());
			String estado = datos.get(1);
			descripcion = datos.get(0);
			Integer contador = 1;
			if (estado != null && estado.equals(Constantes.PASS)) {
				this.utils.guardarDato(descripcion, contador, pedido.getPersonaDestinatario());
				this.utils.guardarDato(descripcion, contador, pedido.getDomicilioEnvio1());
				this.utils.guardarDato(descripcion, contador, pedido.getDomicilioEnvio2());
				this.utils.guardarDato(descripcion, contador, pedido.getDomicilioEnvio3());
				this.utils.guardarDato(descripcion, contador, pedido.getTelefonoDestinatario());
				this.utils.guardarDato(descripcion, contador, pedido.getCiudadEnvio());
				this.utils.guardarDato(descripcion, contador, pedido.getDepartamentoEnvio());
				this.utils.procesarNota(pedido.getNotas(), descripcion, contador);
				return new ResponseEntity<Object>(descripcion, HttpStatus.OK);

			}  else {
				return new ResponseEntity<Object>(datos.get(0), HttpStatus.NO_CONTENT);
			}
		} else {
			return new ResponseEntity<Object>(Constantes.ESTE_PEDIDO_YA_ESTA_LX, HttpStatus.NO_CONTENT);
		}

	}

	@Override
	public Boolean validarPedido(String pedido) {
		log.info("Inicio metodo validarPedido: {} ", pedido);
		String nombre = Constantes.PEDIDOSF.concat(pedido).concat("%");
		List<PedidoEntity> lista = this.pedidoRepository.findByNounIdAndNombreLikeAndEstadoNot(Constantes.CUSTOMERORDEN,
				nombre, Constantes.FAIL);
		if (Objects.nonNull(lista) && !lista.isEmpty()) {
			log.info("Fin metodo validarPedido: {} ", pedido);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	

}
