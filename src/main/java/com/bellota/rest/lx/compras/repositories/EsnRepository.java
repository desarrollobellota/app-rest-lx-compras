package com.bellota.rest.lx.compras.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.EsnEntity;

@Repository
public interface EsnRepository extends JpaRepository<EsnEntity, String>{

	@Transactional
	@Query(value = "INSERT INTO ESN(SNID,SNTYPE,SNCUST,SNSEQ,SNDESC,SNPRT,SNPIC,SNINV,SNSTMT,SNSHIP,SNENDT,SNENTM) VALUES('SN','O', :descripcion , :c, :data  ,'N','N','N','N',0, :fechaActual , :horaActual)", nativeQuery = true)
	void guardarDato(String descripcion, Integer c, String fechaActual, String horaActual, String data);
}
