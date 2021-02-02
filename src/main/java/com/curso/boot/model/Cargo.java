package com.curso.boot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
public class Cargo extends AbstractEntity<Long> {

	@NotBlank(message = "Informe um nome.")		
	@Size(max = 60, message = "O nome do departamento deve ter no máximo 60 caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	//Se não inserir um departamento na hr de cadastrar um cargo, o DPT será nulo
	@NotNull(message = "Selecione o departamento relativo ao cargo.")
	@ManyToOne
	@JoinColumn(name = "departamento_id")			//Lado forte
	private Departamento departamento;
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	} 	
}
