package com.bellota.rest.lx.compras.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bellota.rest.lx.compras.dtos.FacturaDto;
import com.bellota.rest.lx.compras.dtos.PedidoDto;

public interface IFacturaService {
	
	public ResponseEntity<Object> procesarFacturas(List<FacturaDto> facturas);
	
	public String enviarData(PedidoDto pedido);
}
