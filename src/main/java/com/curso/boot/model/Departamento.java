package com.curso.boot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Departamento extends AbstractEntity<Long>{

	private static final long serialVersionUID = 1L;

	//@NotBlank vefifica se campo do formulário está em branco ou contido por espaços em branco
	@NotBlank(message = "Informe um nome.")		
	@Size(min = 3, max = 60, message = "O nome do departamento deve ter entre {min} e {max} caracteres.")
	@Column(nullable = false, unique = true, length = 60)
	private String nome;
	
	@OneToMany(mappedBy = "departamento")			//Lado fraco
	private List<Cargo> cargos = new ArrayList<Cargo>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}
}
