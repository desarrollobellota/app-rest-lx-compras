package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.service.ICentroCostoService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/centrocosto")
@RequiredArgsConstructor
public class CentroCostoController {
	
	private final ICentroCostoService service;

	@GetMapping
	public ResponseEntity<Object> obtenerCentroCosto() {
		return this.service.obtenerCentroCosto();
	}
	
	@GetMapping("/coincidencia/{codigo}")
	public ResponseEntity<Object> obtenerPorCodigo(@PathVariable String codigo) {
		return this.service.obtenerPorCodigo(codigo);
	}
	
	@GetMapping("/listar/{busqueda}/{from}/{size}")
	public ResponseEntity<Object> obtenerPorArea(@PathVariable String busqueda, @PathVariable Integer from, @PathVariable Integer size) {
		return this.service.obtenerPorArea(from, size, busqueda);
	}
}
