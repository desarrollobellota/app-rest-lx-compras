package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

public interface IHistorialService {
	
	public ResponseEntity<Object> obtenerHistorialCompra();
	
	public ResponseEntity<Object> obtenerOrdenesCompra(String parametro);
}
