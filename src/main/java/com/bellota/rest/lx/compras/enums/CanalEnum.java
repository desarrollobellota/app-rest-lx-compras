package com.bellota.rest.lx.compras.enums;

import lombok.Getter;

@Getter
public enum CanalEnum {

	ORDEN_COMPRA("OC", "ORDEN COMPRA"),
	PEDIDO("PD", "PEDIDO"),
	REQUISCION("RQS", "REQUISICION");
	
	private String codigo;
	private String mensaje;
	
    private CanalEnum(String codigo,String mensaje) {
		
	}
}
