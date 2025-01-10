package com.bellota.rest.lx.compras.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.HistorialDto;
import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.maps.HistorialMapper;
import com.bellota.rest.lx.compras.repositories.HistorialRepository;
import com.bellota.rest.lx.compras.service.IHistorialService;
import com.bellota.rest.lx.compras.utils.Constantes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistorialServiceImpl implements IHistorialService {

	private final HistorialRepository historialRepositorio;

	@Value("${bd.schema}")
	private String schema;

	@Override
	public ResponseEntity<Object> obtenerHistorialCompra() {
		log.info("Inicio metodo obtenerHistorialCompra :{} ", this.schema);
		ResponseDto respuesta = new ResponseDto();
		List<Object> lista = this.historialRepositorio.obtenerHistorial(this.schema);

		if (!lista.isEmpty()) {
			log.info("Fin metodo obtenerHistorialCompra ");
			List<HistorialDto> historiales = HistorialMapper.INSTANCE.mapearHistorial(lista);
			for (int i = 0; i < historiales.size(); i++) {
				String observaciones = HistorialMapper.INSTANCE.mapearObservacion(
						this.historialRepositorio.obtenerObservacion(historiales.get(i).getNumeroOrdenCompra()));
				historiales.get(i).setObservacionOC(observaciones);
			}
			return new ResponseEntity<Object>(historiales, HttpStatus.OK);
		} else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(HttpStatus.BAD_REQUEST.value());
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerHistorialCompra: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> obtenerOrdenesCompra(String parametro) {
		log.info("Inicio metodo obtenerOrdenesCompra :{} ", this.schema);
		ResponseDto respuesta = new ResponseDto();
		List<Object> lista = this.historialRepositorio.obtenerOrdenCompra(parametro);

		if (!lista.isEmpty()) {
			log.info("Fin metodo obtenerOrdenesCompra ");
			return new ResponseEntity<Object>(HistorialMapper.INSTANCE.mapearOrdenCompra(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(HttpStatus.BAD_REQUEST.value());
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerOrdenesCompra: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

}
