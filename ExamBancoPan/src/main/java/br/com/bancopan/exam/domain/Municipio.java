package br.com.bancopan.exam.domain;

public class Municipio {

	private Estado estado;
	
	private String nome;

	public Municipio(Estado estado, String nome) {
		this.estado = estado;
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public String getNome() {
		return nome;
	}

	
}
