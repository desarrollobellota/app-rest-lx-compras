package com.bellota.rest.lx.compras.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

	private String codProducto;
	private String descripcionProducto;
	private String clase;
	private String unidadMedida;
	private String linea;
	private String tipo;
	private float valorUnidad;
	private String moneda;
	private String clasificacion;
	private float cantidad;

}
