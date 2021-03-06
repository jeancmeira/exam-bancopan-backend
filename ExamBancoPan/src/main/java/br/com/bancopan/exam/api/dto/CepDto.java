package br.com.bancopan.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Jean
 * 
 * Classe dto para o rest controller de cep
 *
 */
public class CepDto {

	@JsonInclude(Include.NON_NULL)
	private String codigo;
	
	@JsonInclude(Include.NON_NULL)
	private String logradouro;
	
	public CepDto() {
		
	}
	
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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	

	
}
