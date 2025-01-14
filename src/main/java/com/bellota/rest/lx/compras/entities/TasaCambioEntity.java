package com.bellota.rest.lx.compras.entities;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "GCC")
public class TasaCambioEntity  implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "CCDATE")
    private String id;
    
    @Column(name = "CCNVFC")
    private BigDecimal tasa;
    
    @Column(name = "CCFRCR")
    private String frcr;
    
    @Column(name = "CCTOCR")
    private String tocr;
    
    @Column(name = "CCNVDT")
    private String nvdt;

}
