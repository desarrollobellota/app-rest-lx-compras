package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.service.IHistorialService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/historialoc")
@RequiredArgsConstructor
public class HistorialController {
	
	private final IHistorialService service;
	
	@GetMapping ("/listar")
	public ResponseEntity<Object> ObtenerHistorialCompra (){
		return this.service.obtenerHistorialCompra();
	}
	
	@GetMapping("/itemsoc/{busqueda}")
	public ResponseEntity<Object> obtenerPorCodigo(@PathVariable String busqueda) {
		return this.service.obtenerOrdenesCompra(busqueda);
	}

}
