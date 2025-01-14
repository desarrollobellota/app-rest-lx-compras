package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.service.impl.UbicacionServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ubicacion")
@RequiredArgsConstructor
public class UbicacionController {
	
	private final UbicacionServiceImpl service;
	
	@GetMapping("/listar/{busqueda}/{from}/{size}")
	public ResponseEntity<Object> consultarUbicacionesBodega(@PathVariable String busqueda, @PathVariable Integer from, @PathVariable Integer size) {
        return this.service.consultarUbicacionesBodega(busqueda, from, size);
    }
	
	@GetMapping("/coincidencia/{codigo}/{bodega}")
    public ResponseEntity<Object> consultarCoincidenciaAlmacen(@PathVariable String codigo,@PathVariable String bodega) {
        return this.service.consultarCoincidenciaAlmacen(codigo, bodega);
    }
}
