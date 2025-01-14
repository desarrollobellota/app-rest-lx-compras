package com.bellota.rest.lx.compras.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.maps.UbicacionMapper;
import com.bellota.rest.lx.compras.repositories.UbicacionRepository;
import com.bellota.rest.lx.compras.service.IUbicacionService;
import com.bellota.rest.lx.compras.utils.Constantes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UbicacionServiceImpl implements IUbicacionService {
	
	private final UbicacionRepository ubicacionRepository;
	
	@Override
	public ResponseEntity<Object> consultarUbicacionesBodega(String parametro, Integer pagina, Integer cantidad) {
		log.info("Inicio metodo consultarUbicacionesBodega:{},{},{} ", pagina, cantidad, parametro);
		ResponseDto respuesta = new ResponseDto();
		List<Object> lista = this.ubicacionRepository.obtenerUbicacionesSelecionada(parametro.toLowerCase());
		
		if (!lista.isEmpty()) {
			log.info("Fin metodo consultarUbicacionesBodega:{},{},{} ", pagina, cantidad, parametro);
			return new ResponseEntity<Object>(UbicacionMapper.INSTANCE.mapearUbicaciones(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(UbicacionServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo consultarUbicacionesBodega: :{},{},{} ", pagina, cantidad, parametro, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> consultarCoincidenciaAlmacen(String codigo, String bodega) {
		log.info("Inicio metodo consultarCoincidenciaAlmacen:{},{} ", codigo, bodega);
		ResponseDto respuesta = new ResponseDto();
		String codigoFinal ="%".concat(codigo).concat("%");
		List<Object> lista = this.ubicacionRepository.consultarCoincidenciaAlmacen(codigoFinal, bodega);
		
		if (!lista.isEmpty()) {
			log.info("Fin metodo consultarCoincidenciaAlmacen:{},{},{} ", codigo, bodega, lista.size());
			return new ResponseEntity<Object>(UbicacionMapper.INSTANCE.mapearUbicaciones(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(UbicacionServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo consultarCoincidenciaAlmacen: :{},{},{},{} ", codigo, bodega, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

}
