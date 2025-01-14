package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

public interface IOrdenCompraService {
	
	public ResponseEntity<Object> actualizarFechasVencimientoOrdenes(String data);
	
	public ResponseEntity<Object> obtenerConteo();
}
