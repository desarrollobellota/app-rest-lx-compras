package com.bellota.rest.lx.compras.maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bellota.rest.lx.compras.dtos.UbicacionDto;

@Mapper
public interface UbicacionMapper {
	UbicacionMapper INSTANCE = Mappers.getMapper(UbicacionMapper.class);
	
	
	default List<UbicacionDto> mapearUbicaciones(List<Object> lista){
		List<UbicacionDto> ubicaciones = new ArrayList<UbicacionDto>(0);
		if(Objects.nonNull(lista) && !lista.isEmpty()) {
			for(Object item: lista) {
				UbicacionDto historial = new UbicacionDto();
				Object[] valores = (Object[]) item;
				historial.setCodUbicacion(String.valueOf(valores[0]));
				ubicaciones.add(historial);
			}
		}
		return ubicaciones;
	}
}
