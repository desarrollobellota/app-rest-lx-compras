package com.bellota.rest.lx.compras.service;

import java.util.List;

import org.w3c.dom.Document;

import com.bellota.rest.lx.compras.dtos.ItemOrdenCompraDto;


public interface IConectoresService {

	public List<String> enviarMensaje(String descripcion, Document doc, ItemOrdenCompraDto orden, String canal) ;
	
	public List<String> enviarMensaje(String descripcion, Document doc, String canal) ;
}
