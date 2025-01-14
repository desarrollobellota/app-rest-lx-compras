package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, String>{
	
	List<PedidoEntity> findByNounIdAndNombreLikeAndEstadoNot(String id, String nombre, String estado);

}
