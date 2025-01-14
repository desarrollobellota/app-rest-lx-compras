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
@Table(name = "LXC_INBOX_ENTRY")
public class PedidoEntity {
	
	@Id
	@Column (name = "C_ID")
	private String id;
	
	@Column (name = "C_NOUN_ID")
	private String nounId;
	
	@Column (name = "C_SOURCE_NAME")
	private String nombre;
	
	@Column (name = "C_PASS_FAIL")
	private String estado;

}
