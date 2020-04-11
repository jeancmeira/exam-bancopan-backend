package br.com.bancopan.exam.domain;

/**
 * 
 * @author Jean
 *
 *Classe de dominio de negocio de municipio
 *
 */

public class Municipio {

	private String estado;
	
	private String nome;

	public Municipio(String estado, String nome) {
		this.estado = estado;
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public String getNome() {
		return nome;
	}

	
}
