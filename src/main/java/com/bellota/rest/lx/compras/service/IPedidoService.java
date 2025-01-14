package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

import com.bellota.rest.lx.compras.dtos.PedidoDto;

public interface IPedidoService {

	public ResponseEntity<Object> crearPedido(PedidoDto pedido);
	
	public Boolean validarPedido(String pedido);
}
