package com.bellota.rest.lx.compras.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.dtos.FacturaDto;
import com.bellota.rest.lx.compras.service.impl.FacturaServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/factura")
@RequiredArgsConstructor
public class FacturaController {
	
    private final FacturaServiceImpl service;
	
	@PostMapping("/refacturar")
    public ResponseEntity<Object> procesarFacturas(@RequestBody List<FacturaDto> facturas) {
        return this.service.procesarFacturas(facturas);
    }
}
