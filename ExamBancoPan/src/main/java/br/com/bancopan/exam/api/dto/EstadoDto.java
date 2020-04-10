package br.com.bancopan.exam.api.dto;

public class EstadoDto {

	private String sigla;
	
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
