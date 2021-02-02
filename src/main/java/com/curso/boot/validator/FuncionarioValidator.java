package com.curso.boot.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.curso.boot.model.Funcionario;

//Spring Validation consegue formar a lógica da validação. A outra forma utilizada no sistema é o BeanValidation
public class FuncionarioValidator implements Validator{

	/*Esse método tem objetivo validar o obj q está sendo enviado a partir do formulário com obj q realmente essa
	classe deve validar. */
	@Override
	public boolean supports(Class<?> clazz) {
		//Testa se a classe é do msmo tipo que a esperada. (FuncionarioValidator) espera o tipo Funcionario
		return Funcionario.class.equals(clazz);		
	}

	@Override	  //Pode colocar qualquer validação, basta adicionar o nome do campo e a msg na variável "errors"
	public void validate(Object object, Errors errors) {
		Funcionario funcionario = (Funcionario) object;
		LocalDate entrada = funcionario.getDataEntrada();
		
		if (funcionario.getDataEntrada() != null && funcionario.getDataSaida() != null) {
			if (funcionario.getDataSaida().isBefore(entrada)) {
				//O primeiro arg é o nome do campo q está validando e o 2° é a msg de validação
				//Utilizando a msg na forma de uma chave do arquivo de propriedades (properties)
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
	}
}
