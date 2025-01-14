package com.bellota.rest.lx.compras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.FacturaEntity;

@Repository
public interface SihRepository extends JpaRepository<FacturaEntity, String>{

}
