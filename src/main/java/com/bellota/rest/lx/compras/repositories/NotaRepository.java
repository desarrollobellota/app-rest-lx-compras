package com.bellota.rest.lx.compras.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.NotaEntity;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, String>{
	
	Optional<NotaEntity> findByIdAndCodigo(String id, String codigo);
}
