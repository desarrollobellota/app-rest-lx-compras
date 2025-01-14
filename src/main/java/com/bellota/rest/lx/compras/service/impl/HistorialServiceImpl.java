package com.bellota.rest.lx.compras.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.HistorialDto;
import com.bellota.rest.lx.compras.dtos.OrdenCompraDto;
import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.entities.NotaEntity;
import com.bellota.rest.lx.compras.maps.HistorialMapper;
import com.bellota.rest.lx.compras.repositories.HistorialRepository;
import com.bellota.rest.lx.compras.repositories.NotaRepository;
import com.bellota.rest.lx.compras.service.IHistorialService;
import com.bellota.rest.lx.compras.utils.Constantes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistorialServiceImpl implements IHistorialService {

	private final HistorialRepository historialRepositorio;
	
	private final NotaRepository notaRepositorio;

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
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerHistorialCompra: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> obtenerOrdenesCompra(String parametro) {
		log.info("Inicio metodo obtenerOrdenesCompra :{} ", parametro);
		ResponseDto respuesta = new ResponseDto();
		List<Object> lista = this.historialRepositorio.obtenerOrdenCompra(parametro);

		if (!lista.isEmpty()) {
			log.info("Fin metodo obtenerOrdenesCompra ");
			return new ResponseEntity<Object>(HistorialMapper.INSTANCE.mapearOrdenCompra(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerOrdenesCompra: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> obtenerObservacionOrdenCompra(String parametro) {
		log.info("Inicio metodo obtenerObservacionOrdenCompra :{} ", parametro);
		ResponseDto respuesta = new ResponseDto();
		String observaciones = HistorialMapper.INSTANCE.mapearObservacion(
				this.historialRepositorio.obtenerObservacion(parametro));

		if (Objects.nonNull(observaciones) && !observaciones.isEmpty()) {
			log.info("Fin metodo obtenerObservacionOrdenCompra ");
			return new ResponseEntity<Object>(observaciones, HttpStatus.OK);
		} else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerOrdenesCompra: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> obtenerUsuarioOrdenCompra(String parametro) {
		log.info("Inicio metodo obtenerUsuarioOrdenCompra :{} ", parametro);
		ResponseDto respuesta = new ResponseDto();
		Optional<NotaEntity> nota = this.notaRepositorio.findByIdAndCodigo(parametro, "0");

		if (nota.isPresent()) {
			log.info("Fin metodo obtenerUsuarioOrdenCompra ");
			return new ResponseEntity<Object>(nota.get().getDescripcion(), HttpStatus.OK);
		} else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo obtenerUsuarioOrdenCompra: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> actualizarOrdenCompra(final OrdenCompraDto orden) {
		log.info("Inicio metodo actualizarOrdenCompra :{} ", orden.getNumeroOrdenCompra());
		ResponseDto respuesta = new ResponseDto();
		List<Object> lista = this.historialRepositorio.buscarOrdenCompra(orden.getNumeroOrdenCompra());
		
		if(!lista.isEmpty()) {
			this.historialRepositorio.actualizarOrdenCompra(obtenerUsuarioAprobador(orden), orden.getFechaAprobacion(), orden.getNumeroOrdenCompra());
		}else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_SE_PUDO_APROBAR_ORDEN_COMPRA + orden.getNumeroOrdenCompra());
			respuesta.setEstado(String.valueOf(HttpStatus.NO_CONTENT.value()));
			respuesta.setDetalle(Constantes.ESTA_ORDEN_COMPRA_YA_SE_ENCUENTRA_APROBADO);
			log.info("Fin metodo actualizarOrdenCompra: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.NO_CONTENT);
		}
		
		respuesta.setEntidad(HistorialServiceImpl.class.getName());
		respuesta.setTitulo(Constantes.ORDEN_COMPRA + orden.getNumeroOrdenCompra() +  Constantes.APROBADA);
		respuesta.setEstado(String.valueOf(HttpStatus.OK.value()));
		respuesta.setDetalle(Constantes.ORDEN_COMPRA_APROBADA_EXITOSAMENTE);
		log.info("Fin metodo actualizarOrdenCompra: :{} ", HttpStatus.OK.value());
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
	
	private String obtenerUsuarioAprobador(final OrdenCompraDto orden) {
		log.info("Inicio metodo obtenerUsuarioAprobador :{} ", orden.getNumeroOrdenCompra());
		if(Objects.nonNull(orden.getUsuarioAprobador()) && orden.getUsuarioAprobador().length()>10) {
			log.info("Fin metodo obtenerUsuarioAprobador :{} ", orden.getNumeroOrdenCompra());
			return orden.getUsuarioAprobador().substring(0,10);
		}else {
			log.info("Fin metodo obtenerUsuarioAprobador :{} ", orden.getNumeroOrdenCompra());
			return orden.getUsuarioAprobador();
		}
	}

	@Override
	public ResponseEntity<Object> consultarHistorialSinEnviarProveedor(Integer pagina, Integer cantidad) {
		log.info("Inicio metodo consultarHistorialSinEnviarProveedor:{},{} ", pagina, cantidad);
		ResponseDto respuesta = new ResponseDto();
		Pageable page = PageRequest.of(pagina, cantidad);
		List<Object> lista = this.historialRepositorio.consultarHistorialProveedor(page);
		
		if (!lista.isEmpty()) {
			log.info("Fin metodo consultarHistorialSinEnviarProveedor:{},{} ", pagina, cantidad);
			return new ResponseEntity<Object>(HistorialMapper.INSTANCE.mapearOrdenCompraProveedores(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo consultarHistorialSinEnviarProveedor: :{},{},{} ", pagina, cantidad,  HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public ResponseEntity<Object> actualizarEstadoOrdenProveedor(String numeroOrden) {
		log.info("Inicio metodo actualizarEstadoOrdenProveedor :{} ", (numeroOrden));
		ResponseDto respuesta = new ResponseDto();
		List<Object> lista = this.historialRepositorio.obtenerEstadoProveedor( numeroOrden);
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			this.historialRepositorio.actualizarEstadoOrdenProveedor(numeroOrden);
		}else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_SE_PUDO_ACTUALIZAR_ORDEN_COMPRA + numeroOrden);
			respuesta.setEstado(String.valueOf(HttpStatus.NO_CONTENT.value()));
			respuesta.setDetalle(Constantes.NO_SE_PUDO_ACTUALIZAR);
			log.info("Fin metodo actualizarEstadoOrden: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.NO_CONTENT);
		}
		respuesta.setEntidad(HistorialServiceImpl.class.getName());
		respuesta.setTitulo(Constantes.ORDEN_COMPRA + numeroOrden +  Constantes.ACTUALIZADA);
		respuesta.setEstado(String.valueOf(HttpStatus.OK.value()));
		respuesta.setDetalle(Constantes.ORDEN_COMPRA_APROBADA_EXITOSAMENTE);
		log.info("Fin metodo actualizarEstadoOrdenProveedor: :{} ", HttpStatus.OK.value());
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		
	}
 
	@Override
	public ResponseEntity<Object> consultarSinEnviarRequisitor(Integer pagina, Integer cantidad) {
		log.info("Inicio metodo consultarSinEnviarRequisitor:{},{} ", pagina, cantidad);
		ResponseDto respuesta = new ResponseDto();
		Pageable page = PageRequest.of(pagina, cantidad);
		List<Object> lista = this.historialRepositorio.consultarHistorialRequisitores(page);
		
		if (!lista.isEmpty()) {
			log.info("Fin metodo consultarSinEnviarRequisitor:{},{} ", pagina, cantidad);
			return new ResponseEntity<Object>(HistorialMapper.INSTANCE.mapearOrdenCompraRequisitores(lista), HttpStatus.OK);
		} else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo consultarSinEnviarRequisitor: :{},{},{} ", pagina, cantidad,  HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> actualizarEstadoOrdenRequisitor(String numeroOrden) {
		log.info("Inicio metodo actualizarEstadoOrdenRequisitor :{} ", (numeroOrden));
		ResponseDto respuesta = new ResponseDto();
		List<Object> lista = this.historialRepositorio.obtenerEstadoProveedor( numeroOrden);
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			this.historialRepositorio.actualizarEstadoOrdenRequisitor(numeroOrden);
		}else {
			respuesta.setEntidad(HistorialServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_SE_PUDO_ACTUALIZAR_ORDEN_COMPRA + numeroOrden);
			respuesta.setEstado(String.valueOf(HttpStatus.NO_CONTENT.value()));
			respuesta.setDetalle(Constantes.NO_SE_PUDO_ACTUALIZAR);
			log.info("Fin metodo actualizarEstadoOrdenRequisitor: :{} ", HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.NO_CONTENT);
		}
		respuesta.setEntidad(HistorialServiceImpl.class.getName());
		respuesta.setTitulo(Constantes.ORDEN_COMPRA + numeroOrden +  Constantes.ACTUALIZADA);
		respuesta.setEstado(String.valueOf(HttpStatus.OK.value()));
		respuesta.setDetalle(Constantes.ORDEN_COMPRA_APROBADA_EXITOSAMENTE);
		log.info("Fin metodo actualizarEstadoOrdenRequisitor: :{} ", HttpStatus.OK.value());
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
}
