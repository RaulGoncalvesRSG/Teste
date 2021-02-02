package com.curso.boot.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.curso.boot.model.Departamento;
import com.curso.boot.service.DepartamentoService;

@Component //Transforma a classe em um Bean gerenciado pelo Spring
public class StringToDepartamentoConverter implements Converter<String, Departamento>{

	@Autowired
	private DepartamentoService service;
	
	/*Essa classe será chamada sempre q a classe CargoController seja utilizada, msmo esteja utilizando a 
	pag "lista" q n tem comboBox, essa classe será chamada*/
	@Override
	public Departamento convert(String id) {
		if (id.isEmpty()) {
			return null;				//Na pag "listar" o texto estará vazio, então n faz conversão 
		}
		//ID selecionado no comboBox (vem como String e não como objeto Departamento)
		return service.buscarPorId(Long.valueOf(id));		
	}
}
