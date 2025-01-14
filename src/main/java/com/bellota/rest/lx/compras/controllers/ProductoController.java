package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.dtos.ProveedorDto;
import com.bellota.rest.lx.compras.service.impl.ProductoServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/producto")
@RequiredArgsConstructor
public class ProductoController {
	
    private final ProductoServiceImpl service;
	
	@GetMapping
    public ResponseEntity<Object> listarTodos() {
        return this.service.listarTodos();
    }
	
	@GetMapping("/coincidencia/{codigo}")
    public ResponseEntity<Object> buscarPorCodigo(@PathVariable String codigo) {
        return this.service.buscarPorCodigo(codigo);
    }
	
	@GetMapping("/listar/{busqueda}/{from}/{size}")
	public ResponseEntity<Object> listarProductos(@PathVariable String busqueda, @PathVariable Integer from, @PathVariable Integer size) {
		return this.service.listarProductos(from, size, busqueda);
	}
	
	@GetMapping("/encontrar/{codigo}")
    public ResponseEntity<Object> encontrarCodigo(@PathVariable String codigo) {
        return this.service.listarProductos(0,1,codigo);
    }
	
	@PostMapping("/precio")
    public ResponseEntity<Object> buscarPrecio(@RequestBody ProveedorDto proveedor) {
        return this.service.buscarPrecio(proveedor);
    }
}
