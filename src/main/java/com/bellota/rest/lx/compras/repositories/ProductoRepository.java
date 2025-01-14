package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, String>{
	
	@Query (value = "SELECT PCCOM,PCDESC,PCCTYP,PCCUM,PCDSC2 FROM HPC WHERE PCCOM NOT LIKE 'NI%'", nativeQuery = true)
	List<Object> buscarBienesNoInventariables (Pageable page);
	
	@Query (value = "SELECT * FROM IIM where LOWER(IPROD) LIKE :codigo OR LOWER(IDESC) LIKE :descripcion ORDER BY IPROD", nativeQuery = true)
	List<ProductoEntity> buscarProductoPorCodigo(Pageable page,String codigo, String descripcion);
	
	@Query (value = "SELECT * FROM HPC WHERE (LOWER(PCCOM) LIKE :codigo) OR (LOWER(PCDESC) LIKE :descripcion) AND PCCOM NOT LIKE 'NI%'", nativeQuery = true)
	List<Object> buscarBienesPorCodigo (Pageable page, String codigo, String descripcion);
	
	@Query (value = "SELECT CASE WHEN HQQR8 <= :cantidad AND HQQR8>0 THEN HQPR8 WHEN HQQR7 <= :cantidad AND HQQR7>0 THEN HQPR7 WHEN HQQR6 <= :cantidad AND HQQR6>0 THEN HQPR6 WHEN HQQR5 <= :cantidad AND HQQR5>0 THEN HQPR5 WHEN HQQR4 <= :cantidad AND HQQR4>0 THEN HQPR4 WHEN HQQR3 <= :cantidad AND HQQR3>0 THEN HQPR3 WHEN HQQR2 <= :cantidad AND HQQR2>0 THEN HQPR2 WHEN HQQR1 <= :cantidad AND HQQR1>0 THEN HQPR1 ELSE 0 END AS PRICE, HQCURR FROM HQT WHERE UPPER(HQPROD)=:codigoProducto AND HQVEND=:codigoProveedor AND :fechaActual BETWEEN HQEFF AND HQDIS ORDER BY HQEFF DESC", nativeQuery = true)
	List<Object> buscarPrecio ( float cantidad, String codigoProducto, Integer codigoProveedor, String fechaActual);
}
