package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.FacturaEntity;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, String>{

	@Query(value = "SELECT SIWHSE,SIZIP,SFRES,SICUST,SISAL,SISTE,SICPO,IHDPFX,SISTN FROM SIH WHERE IHDPFX=:prefijo AND IHDOCN=:numero limit 1", nativeQuery = true)
	Object obtenerCabecera(String prefijo, String numero);
	
	@Query(value = "SELECT ILWHS,ILQTY,ILPROD,ILNET FROM SIL WHERE ILDPFX=:prefijo AND ILDOCN=:numero", nativeQuery = true)
	List<Object> obtenerLineas(String prefijo, String numero);
}
