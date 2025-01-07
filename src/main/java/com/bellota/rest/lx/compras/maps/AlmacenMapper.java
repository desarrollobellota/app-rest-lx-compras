package com.bellota.rest.lx.compras.maps;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bellota.rest.lx.compras.dtos.AlmacenDto;
import com.bellota.rest.lx.compras.entities.AlmacenEntity;

@Mapper
public interface AlmacenMapper {
	
	AlmacenMapper INSTANCE = Mappers.getMapper(AlmacenMapper.class);
	
	AlmacenDto entityToDto(AlmacenEntity entity);
	
	@InheritInverseConfiguration
	AlmacenEntity dtoToEntity(AlmacenDto dto);
	
	List<AlmacenDto> listEntityToDto(List<AlmacenEntity> entities);
}
