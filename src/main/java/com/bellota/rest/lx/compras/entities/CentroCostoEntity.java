package com.bellota.rest.lx.compras.entities;

import java.io.Serializable;

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
@Table(name = "RCL")
public class CentroCostoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PCNTR")
    private String codigo;
	
	@Column(name = "PCLID")
    private String estado;
	
	@Column(name = "PMANGR")
    private String departamento;
	
	@Column(name = "PAD1")
    private String descripcion;

}
