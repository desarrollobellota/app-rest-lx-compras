package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bellota.rest.lx.compras.entities.HistorialEntity;


@Repository
public interface HistorialRepository extends JpaRepository<HistorialEntity, String>{
	  
	
	@Query(value = "SELECT DISTINCT HPH.PHORD, HPH.PHCUR, CASE WHEN HPX.PXREQN IS NULL THEN 0 ELSE HPX.PXREQN END AS PXREQN, "
	        + "  COTLXUSRF.HISTORIALOC.HISID, TIPOCAMBIO, VALORANTERIOR, NUEVOVALOR, FECHACAMBIO, "
	        + " HORACAMBIO, TRIM(ZXU.XUNAME) AS USUARIOCAMBIO "
	        + " FROM HPH "
	        + " LEFT JOIN HPX ON HPH.PHORD = HPX.PXORDN "
	        + " LEFT JOIN  COTLXUSRF.HISTORIALOC ON HPH.PHORD =  COTLXUSRF.HISTORIALOC.PHORD "
	        + " LEFT JOIN ZXU ON  COTLXUSRF.HISTORIALOC.USUARIOCAMBIO = ZXU.XUUSER "
	        + " WHERE HPH.PHID = 'PH' AND (HPH.PHAPID != '' OR HPH.PHAPID = 'APROBAR') "
	        + " AND HISID = (SELECT MAX(HISID) FROM  COTLXUSRF.HISTORIALOC WHERE COTLXUSRF.HISTORIALOC.PHORD = HPH.PHORD) "
	        + " ORDER BY HPH.PHORD, FECHACAMBIO", nativeQuery = true)
	List<Object> obtenerHistorial(String param);
	
	@Query(value = "SELECT DET.NTNSEQ,DET.NTTEXT FROM ZNK AS ENC INNER JOIN ZNT AS DET ON NKTOKN=NTTOKN WHERE NKPORD= :param AND NKTYPE='HPH' ORDER BY NTNSEQ", nativeQuery = true)
	List<Object> obtenerObservacion(String param);
	
	@Query(value = "SELECT H.PID,H.PLINE,H.PPROD,H.PVEND,H.PQORD,H.PQREC,H.PDDTE,H.PSTAT,H.PWHSE,H.POCUR,H.POITXC,H.PODESC,H.PECST,N.SNDESC FROM HPO H LEFT JOIN COTLXUSRF.ESN_NOTAS N ON H.PORD=N.SNCUST AND H.PLINE=N.SNSHIP WHERE PORD= :param AND PID='PO' ORDER BY PLINE", nativeQuery = true)
	List<Object> obtenerOrdenCompra(String param);
	
	@Query(value = "SELECT PHAPID FROM HPH WHERE PHORD=:param AND (HPH.PHAPID!='' OR HPH.PHAPID='APROBAR')", nativeQuery = true)
	List<Object> buscarOrdenCompra(String param);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE HPH SET PHLAID=:usuarioAprobador,PHAPDT=:fechaAprobacion,PHAPID='' WHERE PHORD=:numeroOrdenCompra", nativeQuery = true)
	int actualizarOrdenCompra(String usuarioAprobador,String fechaAprobacion, String numeroOrdenCompra );
	
	@Query(value= "SELECT PHORD,PHVEND,VNDNAM FROM HPH INNER JOIN AVM ON PHVEND=VENDOR WHERE PHID='PH' AND PHPRT=0 ORDER BY HPH.PHORD DESC", nativeQuery = true)
	List<Object> consultarHistorialProveedor (Pageable page);
	
	@Query(value = "SELECT PHAPID FROM HPH WHERE PHORD=:param", nativeQuery = true)
	List<Object> obtenerEstadoProveedor(String param);
   
	@Transactional
	@Modifying
	@Query (value = "UPDATE HPH SET PHPRT=1 WHERE PHORD=:numeroOrden", nativeQuery = true)
	void actualizarEstadoOrdenProveedor (String numeroOrden);
	
	@Transactional
	@Modifying
	@Query (value = "UPDATE HPH SET PHBPRT=1 WHERE PHORD=:numeroOrden", nativeQuery = true)
	void actualizarEstadoOrdenRequisitor (String numeroOrden);
	
	@Query(value= "SELECT DISTINCT PHORD,PXREQN FROM HPH INNER JOIN HPX ON PHORD=PXORDN WHERE PHID='PH' AND PHBPRT=0 AND PHAPID='' ORDER BY PHORD DESC", nativeQuery = true)
	List<Object> consultarHistorialRequisitores (Pageable page);
	
	
	@Query(value= "SELECT PHORD FROM HPH WHERE PHREVN=:idRequision AND PHID IN (:listaHid)", nativeQuery = true)
	List<Object> validarRequision (Integer idRequision, List<String> listaHid);
	
	@Transactional
	@Modifying
	@Query (value = "UPDATE HPH SET PHREVN=? WHERE PHORD=?", nativeQuery = true)
	void actualizarRequisicion (Integer idRequisicion,Integer numeroOrden);
	
}
