package com.bellota.rest.lx.compras.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.entities.AlmacenEntity;
import com.bellota.rest.lx.compras.maps.AlmacenMapper;
import com.bellota.rest.lx.compras.repositories.AlmacenRepository;
import com.bellota.rest.lx.compras.service.IAlmacenService;
import com.bellota.rest.lx.compras.utils.Constantes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlmacenServiceImpl implements IAlmacenService {

	private final AlmacenRepository almacenRepository;

	@Override
	public ResponseEntity<Object> obtenerAlmacenes() {
		log.info("Inicio metodo obtenerAlmacenes ");
		Pageable page = PageRequest.of(0, 10);
		Page<AlmacenEntity> pages = this.almacenRepository.findAll(page);
		log.info("Fin metodo obtenerAlmacenes ");
		return new ResponseEntity<Object>(AlmacenMapper.INSTANCE.listEntityToDto(pages.getContent()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> obtenerAlmacenCoincidencia(String codigo) {
		log.info("Inicio metodo obtenerAlmacenCoincidencia: {} ", codigo);
		ResponseDto respuesta = new ResponseDto();
		String codigoFinal = "%".concat(codigo).concat("%");
		log.info("CODIGO FINAL: {} " , codigoFinal);
		List<AlmacenEntity> lista = this.almacenRepository.findByCodigoLikeIgnoreCaseOrDescripcionLikeIgnoreCase(codigoFinal,
				codigoFinal);
		if (!lista.isEmpty()) {
			log.info("Fin metodo obtenerAlmacenCoincidencia: {} ", codigo);
			return new ResponseEntity<Object>(AlmacenMapper.INSTANCE.listEntityToDto(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(AlmacenServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerAlmacenCoincidencia: {},{} ", codigo, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}

	}
	
	@Override
	public ResponseEntity<Object> obtenerAlmacenPorCodigo(String codigo) {
		log.info("Inicio metodo obtenerAlmacenPorCodigo: {} ", codigo);
		ResponseDto respuesta = new ResponseDto();
		Optional<AlmacenEntity> optEntity = this.almacenRepository.findByCodigo(codigo);
		if (optEntity.isPresent() ) {
			log.info("Fin metodo obtenerAlmacenPorCodigo: {} ", codigo);
			return new ResponseEntity<Object>(AlmacenMapper.INSTANCE.entityToDto(optEntity.get()), HttpStatus.OK);
		} else {
			respuesta.setEntidad(AlmacenServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerAlmacenPorCodigo: {},{} ", codigo, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> obtenerAlmacenPorDescripcion(final Integer pagina,final Integer cantidad ,final String descripcion) {
		log.info("Inicio metodo obtenerAlmacenPorDescripcion:{},{},{} ", pagina, cantidad, descripcion);
		Pageable page = PageRequest.of(pagina, cantidad);
		String descripcionFinal = "%".concat(descripcion).concat("%");
		List<AlmacenEntity> lista = this.almacenRepository.findByDescripcionLikeIgnoreCase(descripcionFinal,page);
		log.info("Fin metodo obtenerAlmacenPorDescripcion :{},{},{} ", pagina, cantidad, descripcion);
		return new ResponseEntity<Object>(AlmacenMapper.INSTANCE.listEntityToDto(lista), HttpStatus.OK);
	}

}
