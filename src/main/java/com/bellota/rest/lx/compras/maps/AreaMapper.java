package com.bellota.rest.lx.compras.maps;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bellota.rest.lx.compras.dtos.AreaDto;
import com.bellota.rest.lx.compras.entities.AreaEntity;

@Mapper
public interface AreaMapper {
	
	AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

	AreaDto entityToDto(AreaEntity entity);

	@InheritInverseConfiguration
	AreaEntity dtoToEntity(AreaDto dto);

	List<AreaDto> listEntityToDto(List<AreaEntity> entities);
}
