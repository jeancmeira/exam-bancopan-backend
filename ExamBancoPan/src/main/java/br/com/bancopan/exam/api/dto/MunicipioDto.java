package br.com.bancopan.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MunicipioDto {

	@JsonInclude(Include.NON_NULL)
	private String estado;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;

	public MunicipioDto() {
		
	}
		
	public MunicipioDto(String estado, String nome) {
		this.estado = estado;
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public String getNome() {
		return nome;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
