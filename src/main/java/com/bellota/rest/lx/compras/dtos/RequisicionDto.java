package com.bellota.rest.lx.compras.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisicionDto {
	public int idRequisicion;
    public String codProveedor;
    public String puntoEnvio;
    public String codAlmacen;
    public List<RequisicionItemDto> items;
    public String comentario;
    public String usuario;
    public String moneda;
    public String usuarioAprueba;
    public String fechaCompras;
    public String comprador;
}
