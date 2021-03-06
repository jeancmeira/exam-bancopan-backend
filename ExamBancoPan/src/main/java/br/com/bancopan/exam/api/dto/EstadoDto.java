package br.com.bancopan.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Jean
 * 
 * Classe dto para o rest controller de estado
 *
 */

public class EstadoDto {

	@JsonInclude(Include.NON_NULL)
	private String sigla;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;

	public EstadoDto() {
		
	}
	
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

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	
}
