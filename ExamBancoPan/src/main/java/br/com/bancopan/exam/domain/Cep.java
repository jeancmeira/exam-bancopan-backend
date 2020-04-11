package br.com.bancopan.exam.domain;

/**
 * 
 * @author Jean
 *
 *Classe de dominio de negocio de cep
 *
 */
public class Cep {

	private String codigo;
	
	private String logradouro;
	
	public Cep(String codigo, String logradouro) {
		this.codigo = codigo;
		this.logradouro = logradouro;
	}

	public Cep(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	
}
