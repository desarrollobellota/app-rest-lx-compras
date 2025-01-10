package com.bellota.rest.lx.compras.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
   
}
