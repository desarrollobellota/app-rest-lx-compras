package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

public interface IAreaService {
	
	public ResponseEntity<Object> obtenerAreas();
	
	public ResponseEntity<Object> obtenerPorCodigo(final String codigo);
	
	public ResponseEntity<Object> filtrarPorParametro(final Integer pagina,final Integer cantidad ,String parametro);
}
