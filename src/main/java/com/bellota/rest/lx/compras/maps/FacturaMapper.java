package com.bellota.rest.lx.compras.maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bellota.rest.lx.compras.dtos.FacturaDto;
import com.bellota.rest.lx.compras.dtos.PedidoDto;
import com.bellota.rest.lx.compras.dtos.PedidoLineaDto;

@Mapper
public interface FacturaMapper {
	
	FacturaMapper INSTANCE = Mappers.getMapper(FacturaMapper.class);
	
	default PedidoDto mapearCabecera(Object objeto, FacturaDto factura){
		PedidoDto pedido = PedidoDto.builder().build();
		if(Objects.nonNull(objeto) ) {
			Object[] valores = (Object[]) objeto;
			pedido.setBodega(String.valueOf(valores[0]));
			pedido.setCiudadEnvio(String.valueOf(valores[1]));
			pedido.setCodigoClase("8");
			pedido.setCodigoFinanciero(String.valueOf(valores[2]));
			pedido.setCodigoProveedor(String.valueOf(valores[3]));
			pedido.setCodigoVendedor(String.valueOf(valores[4]));
			pedido.setDepartamentoEnvio(String.valueOf(valores[5]));
			pedido.setNotas("Refacturacion Factura " + factura);
			pedido.setOrdenCompraCliente(String.valueOf(valores[6]));
			pedido.setPrefijo(String.valueOf(valores[7]));
			pedido.setPuntoEnvio(String.valueOf(valores[8]));
			pedido.setTipoPedido("7");
		}
		
		return pedido;
	}
	
	default List<PedidoLineaDto> mapearLinea(List<Object> lista){
		List<PedidoLineaDto> lineas = new ArrayList<PedidoLineaDto>();
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				PedidoLineaDto linea = new PedidoLineaDto();
				Object[] valores = (Object[]) item;
				linea.setBodega(String.valueOf(valores[0]));
				linea.setCantidad(String.valueOf(valores[1]));
				linea.setCodigoProducto(String.valueOf(valores[2]));
				linea.setPrecioUnitario(String.valueOf(valores[3]));
				lineas.add(linea);
			}
		}
		
		return lineas;
	}
	
	default List<PedidoLineaDto> mapearLineaNegativa(PedidoDto pedido){
		List<PedidoLineaDto> lineas = new ArrayList<PedidoLineaDto>();
		if(Objects.nonNull(pedido) && Objects.nonNull(pedido.getLineas()) && !pedido.getLineas().isEmpty()) {
			for(PedidoLineaDto item: pedido.getLineas()) {
				if(Objects.nonNull(item.getCantidad())) {
					PedidoLineaDto linea = new PedidoLineaDto();
					float cant =  Float.valueOf(item.getCantidad());
					linea.setBodega(item.getBodega());
					linea.setCantidad(String.valueOf(cant * -1));
					linea.setCodigoProducto(item.getCodigoProducto());
					linea.setPrecioUnitario(item.getPrecioUnitario());
					lineas.add(linea);
				}
				
			}
		}
		
		return lineas;
	}
}
