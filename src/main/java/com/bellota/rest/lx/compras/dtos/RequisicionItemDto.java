package com.bellota.rest.lx.compras.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicionItemDto {
	public String codigoProducto;
	public String cantidad;
	public String valor;
	public String fechaRequerida;
	public String tipoProducto;
	public String comentarioItem;
	public String fechaCompras;
	public String idCentroCostos;
}
