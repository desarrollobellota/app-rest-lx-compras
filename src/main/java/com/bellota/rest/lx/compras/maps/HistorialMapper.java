package com.bellota.rest.lx.compras.maps;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bellota.rest.lx.compras.dtos.HistorialDto;
import com.bellota.rest.lx.compras.dtos.ItemOrdenCompraDto;
import com.bellota.rest.lx.compras.dtos.OrdenCompraDto;
import com.bellota.rest.lx.compras.entities.HistorialEntity;

@Mapper
public interface HistorialMapper {
	
	HistorialMapper INSTANCE = Mappers.getMapper(HistorialMapper.class);
	
	HistorialDto entityToDto(HistorialEntity entity);
	
	@InheritInverseConfiguration
	HistorialEntity dtoToEntity(HistorialDto dto);

	List<HistorialDto> listEntityToDto(List<HistorialEntity> entities);

	
	default List<HistorialDto> mapearHistorial(List<Object> lista){
		List<HistorialDto> historiales = new ArrayList<HistorialDto>();
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				HistorialDto historial = new HistorialDto();
				Object[] valores = (Object[]) item;
				historial.setNumeroOrdenCompra(String.valueOf(valores[0]));
				historial.setNumeroRequisicion(String.valueOf(valores[2]));
				historial.setTipoCambio(String.valueOf(valores[4]));
				historial.setValorAnterior(String.valueOf(valores[5]));
				historial.setNuevoValor(String.valueOf(valores[6]));
				historial.setFechaCambio(String.valueOf(valores[7]));
				historial.setHoraCambio(String.valueOf(valores[8]));
				historial.setUsuarioCambio(String.valueOf(valores[9]));
				historial.setMoneda(String.valueOf(valores[1]));
				historiales.add(historial);
			}
		}
		
		return historiales;
	}
	
	default String mapearObservacion(List<Object> lista){
		String observaciones="";
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				Object[] valores = (Object[]) item;
				observaciones = observaciones.concat(String.valueOf(valores[1]).trim().concat(" "));
			}
		}
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(observaciones);
	    String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
		return utf8EncodedString;
	}
	
	default List<ItemOrdenCompraDto> mapearOrdenCompra(List<Object> lista){
		List<ItemOrdenCompraDto> ordenesCompra = new ArrayList<ItemOrdenCompraDto>();
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				ItemOrdenCompraDto orden = new ItemOrdenCompraDto();
				Object[] valores = (Object[]) item;
				orden.setIdItem(String.valueOf(valores[0]));
				orden.setLineaItem(String.valueOf(valores[1]));
				orden.setCodigoItem(String.valueOf(valores[2]).trim());
				orden.setCodigoProveedor(String.valueOf(valores[3]));
				orden.setCantidadOC(String.valueOf(valores[4]));
				orden.setCantidadRQ(String.valueOf(valores[5]));
				orden.setFechaVencimiento(String.valueOf(valores[6]));
				orden.setValorUnitario(String.valueOf(valores[7]));
				orden.setEstadoItem(String.valueOf(valores[8]));
				orden.setBodegaItem(String.valueOf(valores[9]));
				orden.setMonedaItem(String.valueOf(valores[10]));
				orden.setIvaItem(String.valueOf(valores[11]));
				orden.setDescripcionItem(String.valueOf(valores[12]));
				orden.setObservaciones(String.valueOf(valores[13]));
				
				ordenesCompra.add(orden);
			}
		}
		
		return ordenesCompra;
	}
	
	default List<OrdenCompraDto> mapearOrdenCompraProveedores(List<Object> lista){
		List<OrdenCompraDto> ordenes = new ArrayList<OrdenCompraDto>();
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				OrdenCompraDto orden = new OrdenCompraDto();
				Object[] valores = (Object[]) item;
				orden.setNumeroOrdenCompra(String.valueOf(valores[0]));
				orden.setIdProveedor(String.valueOf(valores[1]));
				orden.setNombreProveedor(String.valueOf(valores[2]));
				ordenes.add(orden);
			}
		}
		
		return ordenes;
	}
	
	default List<OrdenCompraDto> mapearOrdenCompraRequisitores(List<Object> lista){
		List<OrdenCompraDto> ordenes = new ArrayList<OrdenCompraDto>();
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				OrdenCompraDto orden = new OrdenCompraDto();
				Object[] valores = (Object[]) item;
				orden.setNumeroOrdenCompra(String.valueOf(valores[0]));
				orden.setIdProveedor(String.valueOf(valores[1]));
				ordenes.add(orden);
			}
		}
		
		return ordenes;
	}
	
	
}
