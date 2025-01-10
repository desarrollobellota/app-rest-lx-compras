package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.service.impl.AreaServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/area")
@RequiredArgsConstructor
public class AreaController {
	
	private final AreaServiceImpl service;
	
	@GetMapping
	public ResponseEntity<Object> ObtenerAreas() {
		return this.service.obtenerAreas();
	}
	
	@GetMapping("/coincidencia/{codigo}")
	public ResponseEntity<Object> obtenerPorCodigo(@PathVariable String codigo) {
		return this.service.filtrarPorParametro(0, 1,codigo);
	}
	
	@GetMapping("/listar/{busqueda}/{from}/{size}")
	public ResponseEntity<Object> filtrarPorParametro(@PathVariable String busqueda, @PathVariable Integer from, @PathVariable Integer size) {
		return this.service.filtrarPorParametro(from, size, busqueda);
	}

}
