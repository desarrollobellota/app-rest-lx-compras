package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.service.impl.OrdenCompraServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordencompra")
@RequiredArgsConstructor
public class OrdenCompraController {

	private final OrdenCompraServiceImpl service;

	@PostMapping("/actualizafechas")
	public ResponseEntity<Object> actualizarFechas(
			@RequestBody String data) {
		return this.service.actualizarFechasVencimientoOrdenes(data);
	}

	@GetMapping("/count")
	public ResponseEntity<Object> countProveedores() {
		return this.service.obtenerConteo();
	}

}
