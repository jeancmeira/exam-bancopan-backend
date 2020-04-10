package br.com.bancopan.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class EstadoDto {

	@JsonInclude(Include.NON_NULL)
	private String sigla;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;

	public EstadoDto(String sigla, String nome) {
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
