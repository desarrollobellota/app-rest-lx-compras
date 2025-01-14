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
@Table(name = "ESN_NOTAS", schema = "COTLXUSRF")
public class NotaEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SNCUST")
    private String id;
	
	@Column(name = "SNSHIP")
    private String codigo;
	
	@Column(name = "SNDESC")
    private String descripcion;
	
}
