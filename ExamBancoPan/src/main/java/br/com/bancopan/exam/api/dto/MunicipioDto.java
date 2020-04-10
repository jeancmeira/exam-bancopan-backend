package br.com.bancopan.exam.api.dto;

public class MunicipioDto {

	private String estado;
	
	private String nome;

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

	
}
