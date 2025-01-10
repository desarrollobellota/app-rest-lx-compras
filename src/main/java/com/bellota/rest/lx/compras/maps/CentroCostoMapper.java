package com.bellota.rest.lx.compras.maps;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.bellota.rest.lx.compras.dtos.CentroCostoDto;
import com.bellota.rest.lx.compras.entities.CentroCostoEntity;

@Mapper
public interface CentroCostoMapper {
	
	CentroCostoMapper INSTANCE = Mappers.getMapper(CentroCostoMapper.class);

	@Mapping(target = "codigoCentroCosto",source = "codigo")
	@Mapping(target = "estadoCentroCosto",source = "estado")
	@Mapping(target = "departamentoCentroCosto",source = "departamento")
	@Mapping(target = "descripcionCentroCosto",source = "descripcion")
	CentroCostoDto entityToDto(CentroCostoEntity entity);

	@InheritInverseConfiguration
	CentroCostoEntity dtoToEntity(CentroCostoDto dto);

	List<CentroCostoDto> listEntityToDto(List<CentroCostoEntity> entities);

}
