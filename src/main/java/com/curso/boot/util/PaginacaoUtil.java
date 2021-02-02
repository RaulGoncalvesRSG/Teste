package com.curso.boot.util;

import java.util.List;

public class PaginacaoUtil<T> {

	private int tamanho; 			 // Quantidade de linhas
	private int pagina;				 // Número da página atual selecionada pelo usuário
	private long totalPaginas;		 // Se tiver 15 registros e cada pag tiver 5 linhas, terá um total de 3 pag
	private String direcao;
	private List<T> registros;		 // Recebe o resultado da consulta no BD

	//N colocou os setters pq já tem a injeção pelo construtor
	public PaginacaoUtil(int tamanho, int pagina, long totalPaginas, String direcao, List<T> registros) {
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalPaginas = totalPaginas;
		this.direcao = direcao;
		this.registros = registros;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public long getTotalPaginas() {
		return totalPaginas;
	}

	public String getDirecao() {
		return direcao;
	}

	public List<T> getRegistros() {
		return registros;
	}
}
