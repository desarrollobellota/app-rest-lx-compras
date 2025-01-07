package com.bellota.rest.lx.compras.service;


import org.springframework.http.ResponseEntity;

public interface IAlmacenService {
	
	public ResponseEntity<Object> obtenerAlmacenes();
	
	public ResponseEntity<Object> obtenerAlmacenCoincidencia(final String codigo);
	
	public ResponseEntity<Object> obtenerAlmacenPorCodigo(String codigo);
	
	public ResponseEntity<Object> obtenerAlmacenPorDescripcion(final Integer pagina,final Integer cantidad ,String descripcion);
	
}
