package com.bellota.rest.lx.compras.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.TasaCambioEntity;

@Repository
public interface TasaCambioRepository extends JpaRepository<TasaCambioEntity, BigDecimal>{
	
	List<TasaCambioEntity> findByFrcrIgnoreCaseAndTocrAndNvdt(String frcr,String tocr,String nvdt);
}
