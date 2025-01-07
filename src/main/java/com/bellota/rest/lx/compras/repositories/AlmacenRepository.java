package com.bellota.rest.lx.compras.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.AlmacenEntity;

@Repository
public interface AlmacenRepository extends JpaRepository<AlmacenEntity, String>{
	
	List<AlmacenEntity> findByCodigoLikeIgnoreCaseOrDescripcionLikeIgnoreCase(String codigo, String descripcion);
	
	Optional<AlmacenEntity> findByCodigo(String codigo);
	
	List<AlmacenEntity> findByDescripcionLikeIgnoreCase(String descripcion, Pageable page);
}
