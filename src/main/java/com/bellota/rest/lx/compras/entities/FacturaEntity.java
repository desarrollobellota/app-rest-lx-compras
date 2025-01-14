package com.bellota.rest.lx.compras.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "SIH")
public class FacturaEntity {
	
	@Id
    @Column(name = "SIID")
    private String siid;
	
	@Column(name = "SIWHSE")
	private String bodega;
	
	@Column(name = "SIZIP")
	private String ciudadEnvio;
	
	@Column(name = "SFRES")
	private String codigoFinanciero;
	
	@Column(name = "SICUST")
	private String codigoProveedor;
	
	@Column(name = "SISAL")
	private String codigoVendedor;
	
	@Column(name = "SISTE")
	private String departamentoEnvio;
	
	@Column(name = "SICPO")
	private String ordenCompraCliente;
	
	@Column(name = "IHDPFX")
	private String prefijo;
	
	@Column(name = "SISTN")
	private String puntoEnvio;
	
	@Column(name = "IHDOCN")
	private String numero;
	
}
