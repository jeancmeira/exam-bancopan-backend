package br.com.bancopan.exam.api.dto;

public class CepDto {

	private String codigo;
	
	private String logradouro;
	
	public CepDto(String codigo, String logradouro) {
		this.codigo = codigo;
		this.logradouro = logradouro;
	}

	public CepDto(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	
}
