package com.bellota.rest.lx.compras.service;

import org.springframework.http.ResponseEntity;

public interface ICentroCostoService {

	public ResponseEntity<Object> obtenerCentroCosto();

	public ResponseEntity<Object> obtenerPorCodigo(final String codigo);

	public ResponseEntity<Object> obtenerPorArea(final Integer pagina,final Integer cantidad, final String area);
}
