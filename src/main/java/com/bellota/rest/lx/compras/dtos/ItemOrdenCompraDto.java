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
public class ItemOrdenCompraDto {
	
	public String idItem;
    public String lineaItem;
    public String codigoItem;
    public String codigoProveedor;
    public String cantidadOC;
    public String cantidadRQ;
    public String fechaVencimiento;
    public String valorUnitario;
    public String estadoItem;
    public String bodegaItem;
    public String monedaItem;
    public String ivaItem;
    public String descripcionItem;
    public String numeroOrdenCompra;
    public String fechaRequerido;
    public String fechaRecepcion;
    public String usuarioModifica;
    public String observaciones;
}
