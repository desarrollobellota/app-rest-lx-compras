package com.bellota.rest.lx.compras.maps;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.w3c.dom.Document;

import com.bellota.rest.lx.compras.dtos.RequisicionDto;
import com.infor.lx.xmg.bean.POLine;
import com.infor.lx.xmg.bean.PurchaseOrder;
import com.infor.lx.xmg.bean.Requisition;
import com.infor.lx.xmg.bean.RequisitionLine;
import com.infor.lx.xmg.bean.SOADoc;

@Mapper
public interface RequisicionMapper {
	
	RequisicionMapper INSTANCE = Mappers.getMapper(RequisicionMapper.class);
	
	default Document mapearRequisicion(RequisicionDto requisicion) {
		 Requisition req = new Requisition(SOADoc.Action_Create);

         req.setVendorCode(requisicion.getCodProveedor());
         req.setShipToType("3");
         req.setShipToCode(requisicion.getPuntoEnvio());
         req.setWarehouseCode(requisicion.getCodAlmacen());
         req.setComment(requisicion.getUsuario() + "-" + requisicion.getUsuarioAprueba());
         req.setAttibute("sourceName", "Requisicion - " + requisicion.getIdRequisicion());
         req.setBuyerCode(requisicion.getComprador());

         int i = 0;
         while (i < requisicion.getItems().size()) {
             String tipoProd = "0";
             RequisitionLine rl = new RequisitionLine(req);
             rl.setItemCode(requisicion.getItems().get(i).getCodigoProducto());
             rl.setQuantityOrdered(requisicion.getItems().get(i).getCantidad());
             rl.setDueDate(requisicion.getItems().get(i).getFechaRequerida());
             rl.setUnitCost(requisicion.getItems().get(i).getValor());
             rl.setDeliveryDate(requisicion.getItems().get(i).getFechaRequerida());
             rl.setMRPRescheduleDate(requisicion.getItems().get(i).getFechaCompras());
             rl.setProfitCenterCode(requisicion.getItems().get(i).getIdCentroCostos());

             if (requisicion.getItems().get(i).getTipoProducto().equals("Producto")) {
                 tipoProd = "0";
             } else {
                 tipoProd = "1";
             }
             rl.setNonInventoryItem(tipoProd);
             rl.setComment(requisicion.getItems().get(i).getComentarioItem());

             i++;
         }
         
         return req.getDocument();
	}
	
	default Document mapearRequisicionOc(RequisicionDto requisicion) {
		PurchaseOrder PO = new PurchaseOrder(SOADoc.Action_Create);

        PO.setVendorCode(requisicion.codProveedor);
        PO.setShipToType("3");
        PO.setShipToCode(requisicion.puntoEnvio);
        PO.setWarehouseCode(requisicion.codAlmacen);
        PO.setComment(requisicion.getUsuario() + "-" + requisicion.getUsuarioAprueba());
        PO.setAttibute("sourceName", "OrdenCompra - " + requisicion.idRequisicion);
        PO.setBuyerCode(requisicion.comprador);
        PO.setRevisionCode(""+requisicion.idRequisicion);


        int i = 0;
        while (i < requisicion.getItems().size()) {
            String tipoProd = "0";
            POLine p1=new POLine(PO);
            p1.setItemCode(requisicion.getItems().get(i).codigoProducto);
            p1.setQuantityOrdered(requisicion.getItems().get(i).cantidad);
            p1.setDueDate(requisicion.getItems().get(i).fechaRequerida);
            p1.setUnitCost(requisicion.getItems().get(i).valor);
            p1.setDeliveryDate(requisicion.getItems().get(i).fechaRequerida);
            p1.setMRPRescheduleDate(requisicion.getItems().get(i).fechaCompras);
            p1.setProfitCenterCode(requisicion.getItems().get(i).idCentroCostos);

            if (requisicion.getItems().get(i).getTipoProducto().equals("Producto")) {
                tipoProd = "0";
            } else {
                tipoProd = "1";
            }
            p1.setNonInventoryItem(tipoProd);

            i++;
        }
        
        return PO.getDocument();
	}
}
