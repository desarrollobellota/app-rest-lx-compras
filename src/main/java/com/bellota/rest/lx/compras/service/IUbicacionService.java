package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

public interface IUbicacionService {
	
    public ResponseEntity<Object> consultarUbicacionesBodega(String parametro,Integer pagina, Integer cantidad);
	
	public ResponseEntity<Object> consultarCoincidenciaAlmacen(String codigo,String bodega);
}
