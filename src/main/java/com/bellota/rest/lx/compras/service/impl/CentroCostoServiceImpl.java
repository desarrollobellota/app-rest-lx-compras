package com.bellota.rest.lx.compras.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.entities.CentroCostoEntity;
import com.bellota.rest.lx.compras.maps.CentroCostoMapper;
import com.bellota.rest.lx.compras.repositories.CentroCostoRepository;
import com.bellota.rest.lx.compras.service.ICentroCostoService;
import com.bellota.rest.lx.compras.utils.Constantes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CentroCostoServiceImpl implements ICentroCostoService {

	private final CentroCostoRepository centroCostoRepositorio;
	
	@Override
	public ResponseEntity<Object> obtenerCentroCosto() {
		log.info("Inicio metodo obtenerCentroCosto ");
		Pageable page = PageRequest.of(0, 10);
		List<CentroCostoEntity> lista = this.centroCostoRepositorio.findByCodigo(Constantes.CL,page);
		log.info("Fin metodo obtenerCentroCosto ");
		return new ResponseEntity<Object>(CentroCostoMapper.INSTANCE.listEntityToDto(lista), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> obtenerPorCodigo(String codigo) {
		log.info("Inicio metodo obtenerPorCodigo:{}",codigo);
		ResponseDto respuesta = new ResponseDto();
		Pageable page = PageRequest.of(0, 1);
		String codigoFinal = "%".concat(codigo).concat("%");
		List<CentroCostoEntity> lista = this.centroCostoRepositorio.buscarPorCodigo(codigoFinal, page);
		
		if (!lista.isEmpty()) {
			log.info("Fin metodo obtenerPorCodigo:{} ",codigo);
			return new ResponseEntity<Object>(CentroCostoMapper.INSTANCE.entityToDto(lista.get(0)), HttpStatus.OK);
		} else {
			respuesta.setEntidad(AreaServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(HttpStatus.BAD_REQUEST.value());
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerPorCodigo: :{} ", codigo, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> obtenerPorArea(Integer pagina, Integer cantidad, String area) {
		log.info("Inicio metodo filtrarPorParametro:{},{},{} ", pagina, cantidad, area);
		ResponseDto respuesta = new ResponseDto();
		Pageable page = PageRequest.of(pagina, cantidad);
		String codigoFinal = "%".concat(area).concat("%");
		List<CentroCostoEntity> lista = this.centroCostoRepositorio.buscarPorArea(codigoFinal, page);
		
		if (!lista.isEmpty()) {
			log.info("Fin metodo filtrarPorParametro:{},{},{} ", pagina, cantidad, area);
			return new ResponseEntity<Object>(CentroCostoMapper.INSTANCE.listEntityToDto(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(AreaServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(HttpStatus.BAD_REQUEST.value());
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo filtrarPorParametro: :{},{},{} ", pagina, cantidad, area, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

}
