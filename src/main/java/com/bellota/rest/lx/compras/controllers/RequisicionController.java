package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.dtos.RequisicionDto;
import com.bellota.rest.lx.compras.service.impl.RequisicionServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/requisicion")
@RequiredArgsConstructor
public class RequisicionController {
	
    private final RequisicionServiceImpl service;
	
	@PostMapping("/guardar")
    public ResponseEntity<Object> guardarRequisicion(@RequestBody RequisicionDto requisicion) {
        return this.service.guardarRequisicion(requisicion);
    }
	
	@PostMapping("/crearOC")	
    public ResponseEntity<Object> guardarOrdenCompra(@RequestBody RequisicionDto requisicion) {
        return this.service.guardarOrdenCompra(requisicion);
    }
	
	@GetMapping("/consultarTasaCambio/{fecha}/{monedaOrigen}")
    public ResponseEntity<Object> consultarTasaCambio(@PathVariable String fecha, @PathVariable String monedaOrigen) {
        return this.service.consultarTasaCambio(fecha, monedaOrigen);
    }
}
