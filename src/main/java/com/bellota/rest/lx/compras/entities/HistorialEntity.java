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
@Table(name = "HPH")
public class HistorialEntity {
	
	@Id
	@Column(name = "PHORD")
    private String numeroOc;
	
	@Column(name = "TIPOCAMBIO")
    private String tipoCambio;
	
	@Column(name = "VALORANTERIOR")
    private String valorAnterior;
	
	@Column(name = "NUEVOVALOR")
    private String nuevoValor;
	
	@Column(name = "FECHACAMBIO")
    private String fechaCambio;
	
	@Column(name = "HORACAMBIO")
    private String horaCambio;
	
	@Column(name = "USUARIOCAMBIO")
    private String usuarioCambio;
	
	@Column(name = "PHCUR")
    private String moneda;
	
	@Column(name = "PHREVN")
    private String revn;

}
