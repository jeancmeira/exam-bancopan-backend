package br.com.bancopan.exam.domain;

/**
 * 
 * @author Jean
 *
 *Classe de dominio de negocio de estado
 *
 */

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
