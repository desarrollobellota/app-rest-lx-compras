package com.bellota.rest.lx.compras.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bellota.rest.lx.compras.dtos.RequisicionDto;

public interface IRequisicionService {
	
	public ResponseEntity<Object> guardarRequisicion(RequisicionDto requisicion);
	
	public ResponseEntity<Object> guardarOrdenCompra(RequisicionDto requisicion);
	
	public ResponseEntity<Object> consultarTasaCambio(String fecha, String monedaOrigen);
	
	public Boolean validarRequisicion(Integer idRequisicion, List<String> parametros);
}
