package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.service.IAlmacenService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/almacen")
@RequiredArgsConstructor
public class AlmacenController {
	
	private final IAlmacenService service;

	@GetMapping
	public ResponseEntity<Object> obtenerAlmacenes() {
		return this.service.obtenerAlmacenes();
	}
	
	@GetMapping("/coincidencia/{codigo}")
	public ResponseEntity<Object> obtenerAlmacenCoincidencia(@PathVariable String codigo) {
		return this.service.obtenerAlmacenCoincidencia(codigo);
	}
	
	@GetMapping("/encontrar/{codigo}")
	public ResponseEntity<Object> obtenerAlmacenPorCodigo(@PathVariable String codigo) {
		return this.service.obtenerAlmacenPorCodigo(codigo);
	}
	
	@GetMapping("/listar/{busqueda}/{from}/{size}")
	public ResponseEntity<Object> obtenerAlmacenPorDescripcion(@PathVariable String busqueda, @PathVariable Integer from, @PathVariable Integer size) {
		return this.service.obtenerAlmacenPorDescripcion(from, size, busqueda);
	}
}
