package com.bellota.rest.lx.compras.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CentroCostoDto {
	
	private String codigoCentroCosto;
	private String estadoCentroCosto;
	private String departamentoCentroCosto;
	private String descripcionCentroCosto;

}
