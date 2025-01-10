package com.bellota.rest.lx.compras.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class HistorialDto {
	
	private String idHistorial;
	private String numeroOrdenCompra;
	private String numeroRequisicion;
	private String tipoCambio;
	private String valorAnterior;
	private String nuevoValor;
	private String fechaCambio;
	private String horaCambio;
	private String usuarioCambio;
	private String moneda;
	private String observacionOC;

}
