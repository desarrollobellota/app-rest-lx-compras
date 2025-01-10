package com.bellota.rest.lx.compras.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoLineaDto {
	
	private String numeroLinea;
	private String codigoProducto;
	private String cantidad;
	private String precioUnitario;
	private String nota;
	private String bodega;
	private String prefijo;

}
