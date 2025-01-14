package com.bellota.rest.lx.compras.maps;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.w3c.dom.Document;

import com.bellota.rest.lx.compras.dtos.ItemOrdenCompraDto;
import com.infor.lx.xmg.bean.POLine;
import com.infor.lx.xmg.bean.PurchaseOrder;
import com.infor.lx.xmg.bean.SOADoc;

@Mapper
public interface OrdenCompraMapper {

	OrdenCompraMapper INSTANCE = Mappers.getMapper(OrdenCompraMapper.class);

	default Document mapearDocumento(ItemOrdenCompraDto orden) {
		PurchaseOrder PO = new PurchaseOrder(SOADoc.Action_Change);
		PO.setAttibute("sourceName", "OC:" + orden.getNumeroOrdenCompra() + "Linea:" + orden.getLineaItem()
				+ "Usuario: " + orden.getUsuarioModifica());

		PO.setPurchaseOrderCode(orden.getNumeroOrdenCompra());
		if (orden.getLineaItem().equals("0")) {
			if (orden.getFechaRequerido() != null && !orden.getFechaRequerido().trim().equals("")) {
				PO.setAckRequestDate(orden.getFechaRequerido());
			}
			if (orden.getFechaRecepcion() != null && !orden.getFechaRecepcion().trim().equals("")) {
				PO.setAckReceivedDate(orden.getFechaRecepcion());
			}
		} else {
			POLine p1 = new POLine(PO);
			p1.setLineCode(orden.getLineaItem());
			if (orden.getFechaVencimiento() != null && !orden.getFechaVencimiento().trim().equals("")) {
				p1.setDeliveryDate("0");
				p1.setDueDate(orden.getFechaVencimiento());
			}
			if (orden.getFechaRequerido() != null && !orden.getFechaRequerido().trim().equals("")) {
				p1.setAckRequestDate(orden.getFechaRequerido());
			}
			if (orden.getFechaRecepcion() != null && !orden.getFechaRecepcion().trim().equals("")) {
				p1.setAckReceivedDate(orden.getFechaRecepcion());
			}
		}

		return PO.getDocument();
	}
}
