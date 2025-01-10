package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.AreaEntity;

@Repository
@EnableJpaRepositories
public interface AreaRepository extends JpaRepository<AreaEntity, String>{
	
	List<AreaEntity> findByTipoAndCodigoLike(String area, String codigo, Pageable page);
	
	@Query("SELECT a FROM AreaEntity a WHERE a.tipo = :area AND a.codigo LIKE :codigoFijo " + "AND (LOWER(a.codigo) LIKE LOWER(:codigo) OR LOWER(a.descripcion) LIKE LOWER(:codigo)) ORDER BY SVSGVL")
	List<AreaEntity> buscarPorPagina (String area, String codigoFijo, String codigo, Pageable page);
}
