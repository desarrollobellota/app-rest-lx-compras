package com.bellota.rest.lx.compras.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bellota.rest.lx.compras.repositories.EsnRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Utils {

	private final EsnRepository esnRepository; 
	
	public static String obtenerFechaActual(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String fechaActual = dateFormat.format(date);
        
        return fechaActual;
	}
	
	public static String obtenerHoraActual(String format) {
        DateFormat timeFormat = new SimpleDateFormat(format);
        Date date = new Date();
        String horaActual = timeFormat.format(date);
        return horaActual;
	}
	
	public Integer guardarDato(String descripcion, Integer contador, String dato) {
		log.info("Inicio metodo guardarDato: {} ", descripcion);
		if (Objects.nonNull(dato) && !dato.isEmpty()) {
			log.info("Fin metodo guardarDato: {} ", descripcion);
			this.esnRepository.guardarDato(descripcion, contador, Utils.obtenerFechaActual("yyyyMMdd"),
					Utils.obtenerHoraActual("HHmmss"), dato);
			contador++;
		}

		return contador;
	}
	
	public void procesarNota(String nota, String descripcion, Integer contador) {
		log.info("Inicio metodo procesarNota: {},{},{} ", nota, descripcion, contador);
		if (Objects.nonNull(nota) && !nota.equals("")) {
			int g = 0;
			int tamano = nota.length() / 50;
			String notas[] = new String[tamano + 1];
			while (g <= tamano) {
				int var = 0;
				int conse = g + contador;
				if (g < tamano) {
					var = (g + 1) * 50;
				} else {
					var = nota.length();
				}

				notas[g] = nota.substring((g * 50), var);
				guardarDato(descripcion, conse, notas[g]);

				g++;
			}
		}
		log.info("Fin metodo procesarNota: {},{},{} ", nota, descripcion, contador);
	}
}
