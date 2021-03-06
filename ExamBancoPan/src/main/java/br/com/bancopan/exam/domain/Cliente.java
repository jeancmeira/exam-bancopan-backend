package br.com.bancopan.exam.domain;

/**
 * 
 * @author Jean
 *
 *Classe de dominio de negocio de cliente
 *
 */

public class Cliente {

	private String cpf;
	
	private String nome;
	
	private Endereco endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}


