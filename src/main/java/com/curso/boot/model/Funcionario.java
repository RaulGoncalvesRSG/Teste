package com.curso.boot.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

//@NumberFormat e @DateTimeFormat fazem o papel de conversor
@Entity
public class Funcionario extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 3, max = 255)
	@Column(nullable = false, unique = true)
	private String nome;
	
	/*CURRENCY é referente à moeda e o padrão de conversão definido é o americano (!= da pag) para salvar no BD
	columnDefinition - esse atributo define o tipo de dado que terá no BD. Se o valor for null, terá 0.00
	
	Anotações sem o atributo "message" vão receber as msgs armazenadas no arquivo de validação onde tem a
	instrução "javax.validation.constraints"
	
	Anotações com o atributo "message" tem {} e dentro da expressão possui a chave q foi adicionada no arquivo
	de msgs
	
	@PastOrPresent valida o valor de uma data, a data não pode ser posterior ao dia de hj*/
	
	@NotNull			
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal salario;
	
	@NotNull
	@PastOrPresent(message = "{PastOrPresent.funcionario.dataEntrada}")
	@DateTimeFormat(iso = ISO.DATE)		//ISO.DATE - Apenas a data sem horário
	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_saida", columnDefinition = "DATE")
	private LocalDate dataSaida;
	
	@Valid	  //Está dizendo q esse obj deve ser validado de acordo com as instruções de validação de sua classe
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@NotNull(message = "{NotNull.funcionario.cargo}")
	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}