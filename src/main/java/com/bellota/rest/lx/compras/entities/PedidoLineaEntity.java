package com.bellota.rest.lx.compras.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SIL")
public class PedidoLineaEntity {
	
	@Id
	@Column(name = "ILWHS")
	private String bodega;
	
	@Column(name = "ILQTY")
	private String cantidad;
	
	@Column(name = "ILPROD")
	private String codigoProducto;
	
	@Column(name = "ILNET")
	private String precioUnitario;
}
