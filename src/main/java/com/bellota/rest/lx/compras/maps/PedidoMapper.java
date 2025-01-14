package com.bellota.rest.lx.compras.maps;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.w3c.dom.Document;

import com.bellota.rest.lx.compras.dtos.PedidoDto;
import com.bellota.rest.lx.compras.dtos.PedidoLineaDto;
import com.bellota.rest.lx.compras.utils.Utils;
import com.infor.lx.xmg.bean.CustomerOrder;
import com.infor.lx.xmg.bean.Line;
import com.infor.lx.xmg.bean.SOADoc;

@Mapper
public interface PedidoMapper {
	
	PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);
	
	@Mapping(target = "customerCode",source = "codigoProveedor")
	@Mapping(target = "shipToCode",source = "puntoEnvio")
	@Mapping(target = "requestShipDate",source = "fechaRequerida")
	@Mapping(target = "customerPurchaseOrderCode",source = "ordenCompraCliente")
	@Mapping(target = "bidContractID",source = "pedido")
	@Mapping(target = "baseOrderType",source = "tipoPedido")
	@Mapping(target = "userOrderTypeCode",source = "tipoPedido")
	@Mapping(target = "orderClassCode",source = "codigoClase")
	@Mapping(target = "reasonCode",source = "codigoFinanciero")
	@Mapping(target = "warehouseCode",source = "bodega")
	@Mapping(target = "prefix",source = "prefijo")
	@Mapping(target = "documentPrefixCode",source = "prefijoDoc")
	@Mapping(target = "documentCode",source = "pedido")
	@Mapping(target = "salespersonCode",source = "codigoVendedor" , qualifiedByName = "validarCodigoVendedor")
	@Mapping(target = "shipToAttentionOf",source = "personaDestinatario")
	@Mapping(target = "shipToAddressLine1",source = "domicilioEnvio1")
	@Mapping(target = "shipToAddressLine2",source = "domicilioEnvio2")
	@Mapping(target = "shipToAddressLine3",source = "domicilioEnvio3")
	@Mapping(target = "shipToAddressLine4",source = "telefonoDestinatario")
	@Mapping(target = "shipToAddressLine5",source = "ciudadEnvio")
	@Mapping(target = "shipToAddressLine6",source = "departamentoEnvio")
	@Mapping(target = "shippingZoneCode",source = "zona")
	CustomerOrder pedidoToCustomerOrder(PedidoDto parametro);
	
	@Named("validarCodigoVendedor")
	default String validarCodigoVendedor(String codigoVendedor) {
		return codigoVendedor.substring(3, codigoVendedor.length());
	}
	
	
	default Document mapearPedidoCliente(PedidoDto p) {
		 CustomerOrder ped = new CustomerOrder(SOADoc.Action_Create);
		 ped = pedidoToCustomerOrder(p);
         ped.setAttibute("sourceName", "PedidoSF - " + p.getPedido() + "Fecha:" + Utils.obtenerFechaActual("yyyyMMdd") + " " +Utils.obtenerHoraActual("HHmmss"));
      
         listRequisicionToPOLine(p.getLineas(), ped);
         
         return ped.getDocument();
	}
	
	@Mapping(target = "itemCode",source = "pedido.codigoProducto")
	@Mapping(target = "orderedSellingUnitQty",source = "pedido.cantidad")
	@Mapping(target = "txCurrSellingUnitNetPrice",source = "pedido.precioUnitario")
	@Mapping(target = "warehouseCode",source = "pedido.bodega")
	@Mapping(target = "parent",source = "data")
	Line pedidoToCustomerOrder(PedidoLineaDto pedido, CustomerOrder data);
	
	@IterableMapping(qualifiedByName =  "requisicionToPOLine")
	Line listRequisicionToPOLine(List<PedidoLineaDto> lista, CustomerOrder data);
	
}
