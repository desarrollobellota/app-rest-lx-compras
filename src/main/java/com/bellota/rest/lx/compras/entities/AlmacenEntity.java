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
@Table(name = "IWM")
public class AlmacenEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
    @Column(name = "LID")
    private String lid;
    
    @Id
	@Column(name = "LWHS")
    private String codigo;
	
	@Column(name = "LDESC")
    private String descripcion;
	
	@Column(name = "LCOUN")
    private String pais;
	
	@Column(name = "LPOAS")
    private String municipio;
	
	@Column(name = "LPHON")
    private String telefono;
	
	@Column(name = "LADD1")
    private String direccion1;
	
	@Column(name = "LADD2")
    private String direccion2;
	
	@Column(name = "LADD3")
    private String direccion3;
}
