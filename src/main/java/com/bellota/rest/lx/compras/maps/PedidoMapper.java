package com.bellota.rest.lx.compras.maps;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.w3c.dom.Document;

import com.bellota.rest.lx.compras.dtos.PedidoDto;
import com.bellota.rest.lx.compras.utils.Utils;
import com.infor.lx.xmg.bean.CustomerOrder;
import com.infor.lx.xmg.bean.Line;
import com.infor.lx.xmg.bean.SOADoc;

@Mapper
public interface PedidoMapper {
	
	PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);
	
	@Mapping(target = "customerCode",source = "codigoProveedor")
	CustomerOrder pedidoToCustomerOrder(PedidoDto pedido);
	
	default Document mapearPedidoCliente(PedidoDto p) {
		 CustomerOrder ped = new CustomerOrder(SOADoc.Action_Create);

         ped.setCustomerCode(p.getCodigoProveedor());
         ped.setShipToCode(p.getPuntoEnvio());
         ped.setRequestShipDate(p.getFechaRequerida());
         ped.setCustomerPurchaseOrderCode(p.getOrdenCompraCliente());
         ped.setBidContractID(p.getPedido());
         
         ped.setBaseOrderType(p.getTipoPedido());
         ped.setUserOrderTypeCode(p.getTipoPedido());
         ped.setOrderClassCode(p.getCodigoClase());
         //ped.getRoot().appendChild(ped.getDocument().createElement("OrderClassCode")).appendChild(ped.getDocument().createTextNode(p.codigoClase));
         
         ped.setReasonCode(p.getCodigoFinanciero());
         ped.setWarehouseCode(p.getBodega());
         ped.setPrefix(p.getPrefijo());
         
         ped.setDocumentPrefixCode(p.getPrefijoDoc());
         ped.setDocumentCode(p.getPedido());
         
         
         String vendedor = "";
         vendedor = p.getCodigoVendedor().substring(3, p.getCodigoVendedor().length());
         ped.setSalespersonCode(vendedor);
         ped.setShipToAttentionOf(p.getPersonaDestinatario());
         ped.setShipToAddressLine1(p.getDomicilioEnvio1());
         ped.setShipToAddressLine2(p.getDomicilioEnvio2());
         ped.setShipToAddressLine3(p.getDomicilioEnvio3());
         ped.setShipToAddressLine4(p.getTelefonoDestinatario());
         ped.setShipToAddressLine5(p.getCiudadEnvio());
         ped.setShipToAddressLine6(p.getDepartamentoEnvio());
         ped.setShippingZoneCode(p.getZona());

         ped.setAttibute("sourceName", "PedidoSF - " + p.getPedido() + "Fecha:" + Utils.obtenerFechaActual("yyyyMMdd") + " " +Utils.obtenerHoraActual("HHmmss"));

         int i = 0;
         while (i < p.getLineas().size()) {
             Line linea = new Line(ped);
             linea.setItemCode(p.getLineas().get(i).getCodigoProducto());
             linea.setOrderedSellingUnitQty(p.getLineas().get(i).getCantidad());
             linea.setTxCurrSellingUnitNetPrice(p.getLineas().get(i).getPrecioUnitario());
             linea.setWarehouseCode(p.getLineas().get(i).getBodega());

             i++;
         }
         
         return ped.getDocument();
	}
}
