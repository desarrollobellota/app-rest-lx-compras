package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.CentroCostoEntity;

@Repository
@EnableJpaRepositories
public interface CentroCostoRepository extends JpaRepository<CentroCostoEntity, String>{
	
	List<CentroCostoEntity> findByCodigo(String codigo, Pageable pageable);
	
	@Query(value = "SELECT PCLID,PCNTR,PAD1,PMANGR FROM RCL WHERE ((LOWER(PCNTR) LIKE :codigo) OR (LOWER(PMANGR) LIKE :codigo) OR (LOWER(PDPT) LIKE :codigo)) AND PCLID='CL'" , nativeQuery = true)
	List<CentroCostoEntity> buscarPorCodigo(String codigo, Pageable pageable);
	
	@Query(value ="SELECT PCLID,PCNTR,PAD1,PMANGR  FROM RCL WHERE PCLID='CL' AND ((LOWER(PCNTR) LIKE :area) OR (LOWER(PMANGR) LIKE :area) OR (LOWER(PAD1) LIKE :area)) AND (LOWER(PMANGR)=:area OR (PCNTR IN (SELECT PCNTR FROM RCL WHERE LOWER(PMANGR)='general' AND LOWER(PAD2) LIKE :area))) ORDER BY PCNTR", nativeQuery = true)
	List<CentroCostoEntity> buscarPorArea(String area, Pageable pageable);
}
