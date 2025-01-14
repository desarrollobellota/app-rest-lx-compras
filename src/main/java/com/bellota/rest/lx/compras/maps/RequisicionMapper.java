package com.bellota.rest.lx.compras.maps;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.w3c.dom.Document;

import com.bellota.rest.lx.compras.dtos.RequisicionDto;
import com.bellota.rest.lx.compras.dtos.RequisicionItemDto;
import com.infor.lx.xmg.bean.POLine;
import com.infor.lx.xmg.bean.PurchaseOrder;
import com.infor.lx.xmg.bean.Requisition;
import com.infor.lx.xmg.bean.RequisitionLine;
import com.infor.lx.xmg.bean.SOADoc;

@Mapper
public interface RequisicionMapper {
	
	RequisicionMapper INSTANCE = Mappers.getMapper(RequisicionMapper.class);
	
	@Named("requisicionToRequisition")
	@Mapping(target = "vendorCode",source = "codProveedor")
	@Mapping(target = "shipToType",  expression = "java(\"3\")")
	@Mapping(target = "shipToCode",source = "puntoEnvio")
	@Mapping(target = "warehouseCode",source = "codAlmacen")
	@Mapping(target = "comment",expression = "java(requisicion.getUsuario() + \"-\" + requisicion.getUsuarioAprueba())")
	@Mapping(target = "buyerCode",source = "comprador")
	Requisition requisicionToRequisition(RequisicionDto requisicion);
	
	@Named("requisicionToRequisitionLine")
	@Mapping(target = "itemCode",source = "requisicion.codigoProducto")
	@Mapping(target = "quantityOrdered",source = "requisicion.cantidad")
	@Mapping(target = "dueDate",source = "requisicion.fechaRequerida")
	@Mapping(target = "unitCost",source = "requisicion.valor")
	@Mapping(target = "deliveryDate",source = "requisicion.fechaRequerida")
	@Mapping(target = "MRPRescheduleDate",source = "requisicion.fechaCompras")
	@Mapping(target = "profitCenterCode",source = "requisicion.idCentroCostos")
	@Mapping(target = "nonInventoryItem",source = "requisicion.tipoProducto", qualifiedByName = "validarProducto")
	@Mapping(target = "comment",source = "requisicion.comentarioItem")
	@Mapping(target = "parent",source = "req")
	RequisitionLine requisicionToRequisitionLine(RequisicionItemDto requisicion, Requisition req);
	
	
	@Named("requisicionToPurchaseOrder")
	@Mapping(target = "vendorCode",source = "codProveedor")
	@Mapping(target = "shipToType",  expression = "java(\"3\")")
	@Mapping(target = "shipToCode",source = "puntoEnvio")
	@Mapping(target = "warehouseCode",source = "codAlmacen")
	@Mapping(target = "comment",expression = "java(requisicion.getUsuario() + \"-\" + requisicion.getUsuarioAprueba())")
	@Mapping(target = "buyerCode",source = "comprador")
	@Mapping(target = "revisionCode",source = "idRequisicion")
	PurchaseOrder requisicionToPurchaseOrder(RequisicionDto requisicion);
	
	@Named("requisicionToPOLine")
	@Mapping(target = "itemCode",source = "requisicion.codigoProducto")
	@Mapping(target = "quantityOrdered",source = "requisicion.cantidad")
	@Mapping(target = "dueDate",source = "requisicion.fechaRequerida")
	@Mapping(target = "unitCost",source = "requisicion.valor")
	@Mapping(target = "deliveryDate",source = "requisicion.fechaRequerida")
	@Mapping(target = "MRPRescheduleDate",source = "requisicion.fechaCompras")
	@Mapping(target = "profitCenterCode",source = "requisicion.idCentroCostos")
	@Mapping(target = "nonInventoryItem",source = "requisicion.tipoProducto", qualifiedByName = "validarProducto")
	@Mapping(target = "parent",source = "po")
	POLine requisicionToPOLine(RequisicionItemDto requisicion, PurchaseOrder po);
	
	@IterableMapping(qualifiedByName =  "requisicionToRequisitionLine")
	RequisitionLine listRequisicionToRequisicionLine(List<RequisicionItemDto> lista, Requisition req);
	
	@IterableMapping(qualifiedByName =  "requisicionToPOLine")
	RequisitionLine listRequisicionToPOLine(List<RequisicionItemDto> lista, PurchaseOrder po);
	
	@Named("validarProducto")
	default String validarProducto(String tipoProducto) {
		if (tipoProducto.equals("Producto")) {
            return "0";
        } else {
            return "1";
        }
	}
	
	default Document mapearRequisicion(RequisicionDto requisicion) {
		 Requisition req = new Requisition(SOADoc.Action_Create);
		 
		 req =requisicionToRequisition(requisicion);
         req.setAttibute("sourceName", "Requisicion - " + requisicion.getIdRequisicion());
         listRequisicionToRequisicionLine(requisicion.getItems(), req);
         
         return req.getDocument();
	}
	
	default Document mapearRequisicionOc(RequisicionDto requisicion) {
		PurchaseOrder po = new PurchaseOrder(SOADoc.Action_Create);
		
		po =requisicionToPurchaseOrder(requisicion);
		po.setAttibute("sourceName", "OrdenCompra - " + requisicion.idRequisicion);
		
		listRequisicionToPOLine(requisicion.getItems(), po);
        
        return po.getDocument();
	}
}
