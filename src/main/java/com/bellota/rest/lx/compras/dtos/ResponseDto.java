package com.bellota.rest.lx.compras.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
	
	private String Entidad;
	private String Titulo;
	private String estado;
	private String detalle;
}
