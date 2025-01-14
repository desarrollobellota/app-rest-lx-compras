package com.bellota.rest.lx.compras.maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.bellota.rest.lx.compras.dtos.ProductoDto;
import com.bellota.rest.lx.compras.entities.ProductoEntity;

@Mapper
public interface ProductoMapper {

	ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);
	
	@Mapping(target = "codProducto", source = "codigo", qualifiedByName = "trimString")
	@Mapping(target = "descripcionProducto", source = "descripcion", qualifiedByName = "trimString")
	@Mapping(target = "clase", source = "clase", qualifiedByName = "trimString")
	@Mapping(target = "unidadMedida", source = "unidadMedida", qualifiedByName = "trimString")
	@Mapping(target = "linea", source = "linea", qualifiedByName = "trimString")
	@Mapping(target = "clasificacion", source = "clasificacion", qualifiedByName = "trimString")
	@Mapping(target = "tipo",  expression = "java(\"Producto\")")
	ProductoDto entityToDto(ProductoEntity entity);

	@InheritInverseConfiguration
	ProductoEntity dtoToEntity(ProductoDto dto);

	List<ProductoDto> listEntityToDto(List<ProductoEntity> entities);
	
	@Named("trimString")
	default String trimString(String value) {
        if (value != null) {
            return value.trim();
        }
        return null;
    }
	
	default List<ProductoDto> mapearBienes(List<Object> lista){
		List<ProductoDto> productos = new ArrayList<ProductoDto>(0);
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				Object[] valores = (Object[]) item;
				ProductoDto producto = ProductoDto.builder().codProducto(String.valueOf(valores[0]).trim()).descripcionProducto(String.valueOf(valores[1]).trim()).clase(String.valueOf(valores[2]).trim()).unidadMedida(String.valueOf(valores[3]).trim()).linea(String.valueOf(valores[4]).trim()).tipo("BienEconomico").build();
				productos.add(producto);
			}
		}
		
		return productos;
	}

}
