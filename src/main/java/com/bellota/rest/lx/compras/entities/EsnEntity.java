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
@Table(name = "ESN")
public class EsnEntity implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "SNID")
    private String id;
    
    @Column(name = "SNTYPE")
    private String tipo;
    
    @Column(name = "SNCUST")
    private String cust;
    
    @Column(name = "SNSHIP")
    private String ship;
    
    @Column(name = "SNSEQ")
    private String seq;
    
    @Column(name = "SNDESC")
    private String decripcion;
    
    @Column(name = "SNPIC")
    private String pic;
    
    @Column(name = "SNINV")
    private String inv;
    
    @Column(name = "SNSTMT")
    private String stmt;
    
    @Column(name = "SNDOCR")
    private String docr;
    
    @Column(name = "SNENDT")
    private String endt;
    
    @Column(name = "SNENTM")
    private String entm;
    
    @Column(name = "SNENUS")
    private String enus;
    
    @Column(name = "SNMNDT")
    private String mndt;
    
    @Column(name = "SNMNTM")
    private String mntm;
    
    @Column(name = "SNMNUS")
    private String mnus;

}
