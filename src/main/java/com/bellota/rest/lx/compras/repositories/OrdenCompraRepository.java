package com.bellota.rest.lx.compras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.OrdenCompraEntity;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity, String>{
	
	@Query (value= "SELECT count(1) FROM AVM", nativeQuery = true)
	Integer obtenerConteo ();
}
