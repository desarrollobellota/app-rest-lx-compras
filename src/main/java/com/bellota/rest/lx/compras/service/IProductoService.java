package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

import com.bellota.rest.lx.compras.dtos.ProveedorDto;


public interface IProductoService {
	
	public ResponseEntity<Object> listarTodos();
	
	public ResponseEntity<Object> buscarPorCodigo(final String codigo);
	
	public ResponseEntity<Object> listarProductos(final Integer pagina,final Integer cantidad ,String codigo);
	
	public ResponseEntity<Object> buscarPrecio(ProveedorDto proveedor);
}
