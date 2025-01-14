package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

import com.bellota.rest.lx.compras.dtos.OrdenCompraDto;

public interface IHistorialService {
	
	public ResponseEntity<Object> obtenerHistorialCompra();
	
	public ResponseEntity<Object> obtenerOrdenesCompra(String parametro);
	
	public ResponseEntity<Object> obtenerObservacionOrdenCompra(String parametro);
	
	public ResponseEntity<Object> obtenerUsuarioOrdenCompra(String parametro);
	
	public ResponseEntity<Object> actualizarOrdenCompra(OrdenCompraDto orden);
	
	public ResponseEntity<Object> consultarHistorialSinEnviarProveedor(final Integer pagina, Integer cantidad);
	
	public ResponseEntity<Object> actualizarEstadoOrdenProveedor(final String numeroOrden);
	
	public ResponseEntity<Object> actualizarEstadoOrdenRequisitor(final String numeroOrden);
	
	public ResponseEntity<Object> consultarSinEnviarRequisitor(final Integer pagina, Integer cantidad);
}
