package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.dtos.PedidoDto;
import com.bellota.rest.lx.compras.service.impl.PedidoServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/pedido")
@RequiredArgsConstructor
public class PedidoController {
	
	private final PedidoServiceImpl service;
	
	@PostMapping("/guardar")
    public ResponseEntity<Object> guardarPedido(@RequestBody PedidoDto pedido) {
        return this.service.crearPedido(pedido);
    }

}
