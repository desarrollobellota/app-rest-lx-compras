package com.bellota.rest.lx.compras.maps;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface NotaMapper {
	NotaMapper INSTANCE = Mappers.getMapper(NotaMapper.class);
	
}
