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
@Table(name = "IIM")
public class ProductoEntity {
	
	@Id
	@Column (name = "IPROD")
	private String codigo;
	
	@Column (name = "IDESC")
	private String descripcion;
	
	@Column (name = "ICLAS")
	private String clase;
	
	@Column (name = "IUMS")
	private String unidadMedida;
	
	@Column (name = "IREF01")
	private String linea;
	
	@Column (name = "IITYP")
	private String clasificacion;
	
}
