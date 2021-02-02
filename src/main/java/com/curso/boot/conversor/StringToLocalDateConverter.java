package com.curso.boot.conversor;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate>{

	@Override
	public LocalDate convert(String text) {
		StringBuilder data = new StringBuilder();
		
		try {
			LocalDate localDate = LocalDate.parse(text);
			
			data.append(text.substring(0, 4));				//2020-10-10
			data.append(text.substring(5, 7));
			data.append(text.substring(8, text.length()));
			
			Long.parseLong(data.toString());				//20201010
			return localDate;
			
		} catch (Exception e) {
			return null;
		}
	}
}
