package com.bellota.rest.lx.compras.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlmacenDto {
	
	private String codigo;  
    private String descripcion;
    private String pais;
    private String municipio;
    private String telefono;
    private String direccion1;
    private String direccion2;
    private String direccion3;
}
