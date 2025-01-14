package com.bellota.rest.lx.compras.utils;

import com.infor.lx.xmg.bean.LxIntegratorConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LXConnectores {

	@Value("${conexiones.lx.host}")
    private String host;
	@Value("${conexiones.lx.puerto}")
	private int port;
	@Value("${conexiones.lx.home}")
	private String home;
	@Value("${conexiones.lx.instancia}")
	private String instance;

    
    public LxIntegratorConnection obtenerConexion() {
        try {
            LxIntegratorConnection connection = new LxIntegratorConnection(home);
            connection.logon(port, host, instance);
            return connection;
        } catch (Exception e) {
            Logger.getLogger(LxIntegratorConnection.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }
}
