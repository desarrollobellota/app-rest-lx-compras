package com.bellota.rest.lx.compras.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellota.rest.lx.compras.dtos.OrdenCompraDto;
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
	
	
	@GetMapping("/observacionesoc/{busqueda}")
	public ResponseEntity<Object> obtenerObservacionOrdenCompra (@PathVariable String busqueda){
		return this.service.obtenerObservacionOrdenCompra (busqueda);
	}
	
	@GetMapping("/observacionusuariooc/{busqueda}")
	public ResponseEntity<Object> obtenerUsuarioOrdenCompra (@PathVariable String busqueda){
		return this.service.obtenerUsuarioOrdenCompra (busqueda);
	}
	
    @PostMapping("/aprobaroc")
    public ResponseEntity<Object> actualizarOrdenCompra(
            @RequestBody OrdenCompraDto orden) {
        return this.service.actualizarOrdenCompra(orden);
    }
    

	@GetMapping("/listarSinEnviar/{from}/{size}")
	public ResponseEntity<Object> consultarHistorialSinEnviarProveedor(@PathVariable Integer from,@PathVariable Integer size) {
		return this.service.consultarHistorialSinEnviarProveedor(from, size);
	}
	
	@PostMapping("/enviadoProv/{numeroOrdenCOmpra}")
	public ResponseEntity<Object> actualizarEstadoOrden(@PathVariable String numeroOrdenCOmpra) {
		return this.service.actualizarEstadoOrdenProveedor(numeroOrdenCOmpra);
	}
	
	@GetMapping("/listarSinEnviarReq/{from}/{size}")
	public ResponseEntity<Object> consultarSinEnviarRequisitor(@PathVariable Integer from,@PathVariable Integer size) {
		return this.service.consultarSinEnviarRequisitor(from, size);
	}
	
	@PostMapping("/enviadoReq/{numeroOrdenCOmpra}")
	public ResponseEntity<Object> actualizarEstadoOrdenRequisitor(@PathVariable String numeroOrdenCOmpra) {
		return this.service.actualizarEstadoOrdenRequisitor(numeroOrdenCOmpra);
	}

}
