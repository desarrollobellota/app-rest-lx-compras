package com.bellota.rest.lx.compras.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.entities.AreaEntity;
import com.bellota.rest.lx.compras.maps.AreaMapper;
import com.bellota.rest.lx.compras.repositories.AreaRepository;
import com.bellota.rest.lx.compras.service.IAreaService;
import com.bellota.rest.lx.compras.utils.Constantes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AreaServiceImpl implements IAreaService {

	private final AreaRepository areaRepositorio;
	
	@Override
	public ResponseEntity<Object> obtenerAreas() {
		log.info("Inicio metodo obtenerAreas ");
		Pageable page = PageRequest.of(0, 10);
		List<AreaEntity> lista = this.areaRepositorio.findByTipoAndCodigoLike(Constantes.AREA,Constantes.F,page);
		log.info("Fin metodo obtenerAreas ");
		return new ResponseEntity<Object>(AreaMapper.INSTANCE.listEntityToDto(lista), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> filtrarPorParametro(Integer pagina, Integer cantidad, String parametro) {
		log.info("Inicio metodo filtrarPorParametro:{},{},{} ", pagina, cantidad, parametro);
		ResponseDto respuesta = new ResponseDto();
		Pageable page = PageRequest.of(pagina, cantidad);
		String codigoFinal = "%".concat(parametro).concat("%");
		List<AreaEntity> lista = this.areaRepositorio.buscarPorPagina(Constantes.AREA,Constantes.F, codigoFinal, page);
		
		if (!lista.isEmpty()) {
			log.info("Fin metodo filtrarPorParametro:{},{},{} ", pagina, cantidad, parametro);
			return new ResponseEntity<Object>(AreaMapper.INSTANCE.entityToDto(lista.get(0)), HttpStatus.OK);
		} else {
			respuesta.setEntidad(AreaServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(HttpStatus.BAD_REQUEST.value());
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo filtrarPorParametro: :{},{},{} ", pagina, cantidad, parametro, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
	}

}
