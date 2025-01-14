package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.UbicacionEntity;

@Repository
public interface UbicacionRepository extends JpaRepository<UbicacionEntity, String>{
	
	@Query (value= "SELECT WLOC, WDESC FROM ILM WHERE LOWER(WWHS)=:parametro ORDER BY WLOC,WDESC", nativeQuery = true)
	List<Object> obtenerUbicacionesSelecionada(String parametro);
	
	@Query (value= "SELECT WLOC, WDESC FROM ILM WHERE (LOWER(WLOC) LIKE :codigo) AND LOWER(WWHS)=:bodega ORDER BY WLOC,WDESC", nativeQuery = true)
	List<Object> consultarCoincidenciaAlmacen(String codigo, String bodega);
}
