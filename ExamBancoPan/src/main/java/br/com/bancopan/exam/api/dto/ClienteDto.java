package br.com.bancopan.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ClienteDto {
	
	@JsonInclude(Include.NON_NULL)
	private String cpf;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private EnderecoDto endereco;
	
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

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
}


