package com.bellota.rest.lx.compras.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.bellota.rest.lx.compras.dtos.ItemOrdenCompraDto;
import com.bellota.rest.lx.compras.service.IConectoresService;
import com.bellota.rest.lx.compras.utils.Constantes;
import com.bellota.rest.lx.compras.utils.LXConnectores;
import com.infor.lx.xmg.bean.LxIntegratorConnection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConectoresServiceImpl implements IConectoresService {
	
	private LxIntegratorConnection connector;
	private final LXConnectores lxConector;

	@Override
	public List<String> enviarMensaje(String descripcion, Document doc, ItemOrdenCompraDto orden, String canal) {
		log.info("Inicio metodo enviarMensaje: {},{},{} ", descripcion,doc,canal );
		List<String> datos = new ArrayList<String>();
		SAXBuilder builder = new SAXBuilder();
		try {
			this.connector = obtenerConexion();
			if (connector.isLoggedon()) {
				connector.setWaitForReply(true);
				connector.sendMessage(doc);
				String message = connector.getReplyAsString();
				log.info("message:{} ", message);
				String estado = connector.getPass();

				ByteArrayInputStream inputXML = new ByteArrayInputStream(message.getBytes());
				org.jdom2.Document document = (org.jdom2.Document) builder.build(inputXML);
				Element rootNode = document.getRootElement();
				Element pedidoXml = rootNode;
				datos.add(mapearDescripcion(canal, descripcion, estado, pedidoXml, orden));
				datos.add(estado);
				descripcion = mapearDescripcion(canal, descripcion, estado, pedidoXml, orden);
				connector.disconnect();
			}else {
				datos.add(Constantes.NO_HAY_CONEXION_CONECTOR_LX);
				datos.add(Constantes.FAIL);
				return datos;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			datos.add(e.getMessage());
			datos.add(Constantes.FAIL);
			return datos;
			
		}
		return datos;
	}

	@Override
	public List<String> enviarMensaje(String descripcion, Document doc, String canal) {
		log.info("Inicio metodo enviarMensaje: {},{},{} ", descripcion,doc,canal );
		SAXBuilder builder = new SAXBuilder();
		List<String> datos = new ArrayList<String>();
		try {
			this.connector = obtenerConexion();
			if (connector.isLoggedon()) {
				connector.setWaitForReply(true);
				connector.sendMessage(doc);
				String message = connector.getReplyAsString();
				log.info("message:{} ", message);
				String estado = connector.getPass();// pass o fail
	
				ByteArrayInputStream inputXML = new ByteArrayInputStream(message.getBytes());
				org.jdom2.Document document = (org.jdom2.Document) builder.build(inputXML);
				Element rootNode = document.getRootElement();
				Element pedidoXml = rootNode;
	
				datos.add(mapearDescripcion(canal, descripcion, estado, pedidoXml, null));
				datos.add(estado);
				connector.disconnect();
			}else {
				datos.add(Constantes.NO_HAY_CONEXION_CONECTOR_LX);
				datos.add(Constantes.NA);
				return datos;
			}
		} catch (Exception e) {
			e.printStackTrace();
			datos.add(e.getLocalizedMessage());
			datos.add(Constantes.FAIL);
			return datos;
		}
		return datos;
	}
	
	private String mapearDescripcion(String canal , String descripcion, String estado, Element pedidoXml, ItemOrdenCompraDto orden) {
		String msg4 = "";
		 
		switch (canal) {
		case "OC":
			if (Objects.nonNull(estado) && estado.equals(Constantes.PASS)) {
				List<Element> msg = pedidoXml.getChildren("PurchaseOrder");
				List<Element> msg1 = msg.get(0).getChildren("PurchaseOrderCode");
				msg4 = msg1.get(0).getContent(0).getValue() + "\n";
				descripcion = descripcion + "" + msg4 + "-" +orden.getLineaItem() + "-OK // ";
			} else if (Objects.nonNull(estado) &&  estado.equals(Constantes.FAIL)) {
				List<Element> msg = pedidoXml.getChildren("PurchaseOrder");
				List<Element> msg1 = msg.get(0).getChildren("MessageDetails");
				String msgW = msg1.get(0).getContent(1).getValue();
				msg4 = "Error OC : " + orden.getNumeroOrdenCompra() + " linea:"
						+ orden.getLineaItem() + ": " + msgW + " // ";
				descripcion = descripcion + msg4;
			}
			break;
        case "PD":
        	if (Objects.nonNull(estado) && estado.equals(Constantes.PASS)) {
        		List<Element> msg = pedidoXml.getChildren("CustomerOrder");
                List<Element> msg1 = msg.get(0).getChildren("CustomerOrderCode");
                descripcion = msg1.get(0).getContent(0).getValue();
        	}else if (Objects.nonNull(estado) && estado.equals(Constantes.FAIL)) {
        		List<Element> msg = pedidoXml.getChildren("CustomerOrder");
                List<Element> msg1 = msg.get(0).getChildren("MessageDetails");
                String msgW = msg1.get(0).getContent(1).getValue();
                descripcion = "Error: " + msgW;
        	}
        	
			break;
        case "RQS":
        	if (Objects.nonNull(estado) && estado.equals(Constantes.PASS)) {
        		List<Element> msg = pedidoXml.getChildren("Requisition");
                List<Element> msg1 = msg.get(0).getChildren("PurchaseOrderCode");
                descripcion = msg1.get(0).getContent(0).getValue();

                int requi = Integer.parseInt(msg1.get(0).getContent(0).getValue());
                descripcion = "" + requi;
        	}else if (Objects.nonNull(estado) && estado.equals(Constantes.FAIL)) {
        		List<Element> msg = pedidoXml.getChildren("Requisition");
                List<Element> msg1 = msg.get(0).getChildren("MessageDetails");
                String msgW = msg1.get(0).getContent(1).getValue();
                descripcion = "Error: " + msgW;
        	}
        	
			break;
		default:
			break;
		}
		
		return descripcion;
	}
	
	private LxIntegratorConnection obtenerConexion() {
		if(Objects.isNull(connector)) {
			return this.lxConector.obtenerConexion();
		}
		return connector;
	}

}
