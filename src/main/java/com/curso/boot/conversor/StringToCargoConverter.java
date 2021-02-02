package com.curso.boot.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.curso.boot.model.Cargo;
import com.curso.boot.service.CargoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo>{

	@Autowired
	private CargoService service;
	
	@Override
	public Cargo convert(String id) {
		if (id.isEmpty()) {
			return null;		
		}
		return service.buscarPorId(Long.valueOf(id));		
	}
}
