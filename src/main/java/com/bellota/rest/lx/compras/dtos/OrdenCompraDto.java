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
public class OrdenCompraDto {
	
	public String idOrdenCompra;
    public String numeroOrdenCompra;
    public int estadoOrdenCompra;
    public String idProveedor;
    public String nombreProveedor;
    public String usuarioRequisitor;
    public String fechaAprobacion;
    public String totalAprobado;
    public String usuarioAprobador; 
    public String numeroRequisicion;
}
