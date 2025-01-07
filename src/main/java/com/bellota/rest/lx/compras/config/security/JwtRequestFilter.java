package com.bellota.rest.lx.compras.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	@Value("${security.llave}")
	private String key;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (validateAuthorization(request.getHeader("authorization"))) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					null,null,null);
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		} else {
			
			log.warn("Autenticación invalida");
		}
		chain.doFilter(request, response);
	}
	
	private Boolean validateAuthorization(final String authorization) {
		log.info("Inicio metodo validateAuthorization: {} ", authorization);
		Base64 base = new Base64();
		String keyEnd = new String(base.decode(authorization));
		log.info("#################LLAVE FINAL############# :{} " , keyEnd);
		if(keyEnd.equals(key)) {
			log.info("Fin metodo validateAuthorization: {} ", Boolean.TRUE);
			return Boolean.TRUE;
		}
		log.info("Fin metodo validateAuthorization: {} ", Boolean.FALSE);
		return Boolean.FALSE;
	}

}
