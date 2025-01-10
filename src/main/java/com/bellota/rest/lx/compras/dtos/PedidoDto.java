package com.bellota.rest.lx.compras.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
	
	    private String puntoEnvio;
	    private String codigoProveedor;
	    private String fechaRequerida;
	    private String ordenCompraCliente;
	    private String pedido;
	    private String fechaUsuario;
	    private String tipoPedido;
	    private String codigoFinanciero;
	    private String codigoClase;
	    private String bodega;
	    private String notas;
	    private String prefijo;
	    private String codigoVendedor;
	    private String personaDestinatario;
	    private String enviarA;
	    private String domicilioEnvio1;
	    private String domicilioEnvio2;
	    private String domicilioEnvio3;
	    private String telefonoDestinatario;
	    private String ciudadEnvio;
	    private String departamentoEnvio;
	    private String zona;
	    private String prefijoDoc;

}
