package br.com.bancopan.exam.domain;

public class Estado {

	private String sigla;
	
	private String nome;

	public Estado(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	
}
