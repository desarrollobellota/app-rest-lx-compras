package com.bellota.rest.lx.compras.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bellota.rest.lx.compras.dtos.ProductoDto;
import com.bellota.rest.lx.compras.dtos.ProveedorDto;
import com.bellota.rest.lx.compras.dtos.ResponseDto;
import com.bellota.rest.lx.compras.entities.ProductoEntity;
import com.bellota.rest.lx.compras.maps.ProductoMapper;
import com.bellota.rest.lx.compras.repositories.ProductoRepository;
import com.bellota.rest.lx.compras.service.IProductoService;
import com.bellota.rest.lx.compras.utils.Constantes;
import com.bellota.rest.lx.compras.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoServiceImpl implements IProductoService {

	private final ProductoRepository productoRepository;
	
	@Override
	public ResponseEntity<Object> listarTodos() {
		log.info("Inicio metodo listarTodos ");
		List<ProductoDto> productos = new ArrayList<ProductoDto>();
		
		Pageable page = PageRequest.of(0, 5);
		Page<ProductoEntity> pages = this.productoRepository.findAll(page);
		productos.addAll(ProductoMapper.INSTANCE.listEntityToDto(pages.toList()));
		productos.addAll(ProductoMapper.INSTANCE.mapearBienes(this.productoRepository.buscarBienesNoInventariables(page)));
		log.info("Fin metodo listarTodos:{} ", productos.size());
		return new ResponseEntity<Object>(productos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> buscarPorCodigo(String codigo) {
		log.info("Inicio metodo buscarPorCodigo:{} ", codigo);
		ResponseDto respuesta = new ResponseDto();
        List<ProductoDto> productos = new ArrayList<ProductoDto>(0);
        List<ProductoDto> bienes = new ArrayList<ProductoDto>(0);
		Pageable page = PageRequest.of(0, 1);
		String codigoFinal = "%".concat(codigo).concat("%" );
		productos.addAll(ProductoMapper.INSTANCE.listEntityToDto(this.productoRepository.buscarProductoPorCodigo(page,codigoFinal, codigoFinal)));
		bienes.addAll(ProductoMapper.INSTANCE.mapearBienes(this.productoRepository.buscarBienesPorCodigo(page, codigoFinal, codigoFinal)));
		
		if(!bienes.isEmpty() || !productos.isEmpty()) {
			if(!bienes.isEmpty()) {
				return new ResponseEntity<Object>(bienes.get(0), HttpStatus.OK);
			}else if(!productos.isEmpty()) {
				return new ResponseEntity<Object>(productos.get(0), HttpStatus.OK);
			}
		}
		
		respuesta.setEntidad(AlmacenServiceImpl.class.getName());
		respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
		respuesta.setEstado(String.valueOf(HttpStatus.NO_CONTENT.value()));
		respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
		log.info("Fin metodo buscarPorCodigo: {},{} ", codigo, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> listarProductos(Integer pagina, Integer cantidad, String codigo) {
		log.info("Inicio metodo listarProductos:{},{},{} ", pagina,cantidad, codigo);
		ResponseDto respuesta = new ResponseDto();
        List<ProductoDto> productos = new ArrayList<ProductoDto>(0);
		Pageable page = PageRequest.of(0, 1);
		String codigoFinal = "%".concat(codigo).concat("%" );
		productos.addAll(ProductoMapper.INSTANCE.listEntityToDto(this.productoRepository.buscarProductoPorCodigo(page,codigoFinal, codigoFinal)));
		productos.addAll(ProductoMapper.INSTANCE.mapearBienes(this.productoRepository.buscarBienesPorCodigo(page, codigoFinal, codigoFinal)));
		
		if(productos.isEmpty()) {
			respuesta.setEntidad(AlmacenServiceImpl.class.getName());
			respuesta.setTitulo(Constantes.NO_HAY_RESULTADO);
			respuesta.setEstado(String.valueOf(HttpStatus.NO_CONTENT.value()));
			respuesta.setDetalle(Constantes.NO_HAY_RESULTADO);
			log.info("Fin metodo listarProductos: {},{} ", codigo, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<Object>(respuesta, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(cantidad.equals(1) ? !productos.isEmpty() ? productos.get(0):null :productos, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<Object> buscarPrecio(ProveedorDto proveedor) {
		log.info("Inicio metodo buscarPrecio ");
		int codigoProveedor = proveedor.getCodProveedor();
		int i = 0;
        while (i < proveedor.getCodProd().size()) {
        	 float cant = 0;
             cant = proveedor.getCodProd().get(i).getCantidad();
             String prod = proveedor.getCodProd().get(i).getCodProducto().toUpperCase();
             List<Object> lista =  this.productoRepository.buscarPrecio(cant, prod.toUpperCase(), codigoProveedor, Utils.obtenerFechaActual("yyyyMMdd"));
             if(!lista.isEmpty()) {
            	 Object[] valores = (Object[]) lista.get(0);
            	 proveedor.getCodProd().get(i).setValorUnidad(Float.valueOf(String.valueOf(valores[0])));
            	 proveedor.getCodProd().get(i).setMoneda(String.valueOf(valores[0]));
             }
             i++;
        }
        log.info("Fin metodo buscarPrecio ");
		return new ResponseEntity<Object>(proveedor, HttpStatus.OK);
	}

}
