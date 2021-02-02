package com.curso.boot.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIntegerConverter implements Converter<String, Integer>{

	/*O Spring converte automaticamente um número que vem da página em um Integer. Ele vem como um texto, mas é 
	convertido pelo String. Então, n precisa realziar essa conversão. Na aplicação usamos a conversão apenas 
	por conta de uma validação q foi inserida e q queriamos manipular o valor para null quando não fosse aquele 
	padrão desejado*/
	@Override
	public Integer convert(String text) {
		text = text.trim();
		
		if (text.matches("[0-9]+")) {		//Expressão regular para verificar se a str possui apenas dígitos
			return Integer.valueOf(text);
		}
		return null;				//Retorna null para o atributo "numero" do Endereco
	}
}
