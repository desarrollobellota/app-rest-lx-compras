package com.bellota.rest.lx.compras.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.RequisicionDto;
import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.entities.TasaCambioEntity;
import com.bellota.rest.lx.compras.enums.CanalEnum;
import com.bellota.rest.lx.compras.maps.RequisicionMapper;
import com.bellota.rest.lx.compras.repositories.HistorialRepository;
import com.bellota.rest.lx.compras.repositories.TasaCambioRepository;
import com.bellota.rest.lx.compras.service.IRequisicionService;
import com.bellota.rest.lx.compras.utils.Constantes;
import com.bellota.rest.lx.compras.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequisicionServiceImpl implements IRequisicionService {

	private final HistorialRepository historialRepositorio;
	
	private final ConectoresServiceImpl conectoresService;
	
	private final Utils utils;
	
	private final HistorialRepository historialRepository;
	
	private final TasaCambioRepository tasaCambioRepository;
	
	@Override
	public ResponseEntity<Object> guardarRequisicion(RequisicionDto requisicion) {
		log.info("Inicio metodo guardarRequisicion: {} ", requisicion);
		String descripcion ="";
		List<String> parametros = new ArrayList<String>(0);
		parametros.add(Constantes.RH);
		parametros.add(Constantes.RZ);
		
		if(!validarRequisicion(requisicion.getIdRequisicion(), parametros)) {
			List<String> respuesta = this.conectoresService.enviarMensaje(descripcion, RequisicionMapper.INSTANCE.mapearRequisicion(requisicion), CanalEnum.REQUISCION.getCodigo());
			String estado = respuesta.get(1);
			descripcion = respuesta.get(0);
			if (estado != null && estado.equals(Constantes.PASS)) {
                this.historialRepository.actualizarRequisicion(requisicion.getIdRequisicion(), Integer.parseInt(descripcion));
				procesarComentarioItems(requisicion, descripcion);
				this.utils.procesarNota(requisicion.getComentario(), descripcion,1);
				return new ResponseEntity<Object>(descripcion, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(descripcion, HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<Object>(Constantes.REQUISICION_YA_EXISTE_EN_LX, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> guardarOrdenCompra(RequisicionDto requisicion) {
		log.info("Inicio metodo guardarOrdenCompra: {} ", requisicion);
		String descripcion ="";
		List<String> parametros = new ArrayList<String>(0);
		parametros.add(Constantes.PH);
		parametros.add(Constantes.PZ);
		
		if(!validarRequisicion(requisicion.getIdRequisicion(), parametros)) {
			List<String> respuesta = this.conectoresService.enviarMensaje(descripcion, RequisicionMapper.INSTANCE.mapearRequisicion(requisicion), CanalEnum.REQUISCION.getCodigo());
			String estado = respuesta.get(1);
			descripcion = respuesta.get(0);
			if (estado != null && estado.equals(Constantes.PASS)) {
                this.historialRepository.actualizarRequisicion(requisicion.getIdRequisicion(), Integer.parseInt(descripcion));
				procesarComentarioItems(requisicion, descripcion);
				this.utils.procesarNota(requisicion.getComentario(), descripcion,1);
				return new ResponseEntity<Object>(descripcion, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(descripcion, HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<Object>(Constantes.REQUISICION_YA_ORDEN_COMPRA_EN_LX, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> consultarTasaCambio(String fecha, String monedaOrigen) {
		log.info("Inicio metodo consultarTasaCambio:{},{} ", fecha, monedaOrigen);
		ResponseDto respuesta = new ResponseDto();
		List<TasaCambioEntity> lista = this.tasaCambioRepository.findByFrcrIgnoreCaseAndTocrAndNvdt(monedaOrigen,Constantes.COP, fecha );
		if (!lista.isEmpty()) {
			log.info("Fin metodo consultarTasaCambio:{},{},{} ", fecha, monedaOrigen,lista.get(0).getTasa());
			return new ResponseEntity<Object>(lista.get(0).getTasa(), HttpStatus.OK);
		} else {
			respuesta.setEntidad(AreaServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo consultarTasaCambio:{},{},{} ", fecha, monedaOrigen,HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Boolean validarRequisicion(Integer idRequisicion, List<String> parametros) {
		log.info("Inicio metodo validarRequisicion: {} ", idRequisicion);
		List<Object> lista = this.historialRepositorio.validarRequision(idRequisicion,parametros );
		if (Objects.nonNull(lista) && !lista.isEmpty()) {
			log.info("Fin metodo validarRequisicion: {} ", idRequisicion);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	private void procesarComentarioItems(RequisicionDto requisicion, String descripcion) {
		int k = 0;
        while (k < requisicion.getItems().size()) {
            this.utils.procesarNota(requisicion.getItems().get(k).getComentarioItem(), descripcion,1);
            k++;
        }
	}

}
