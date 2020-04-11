package br.com.bancopan.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Jean
 * 
 * Classe dto para o rest controller de enddereco
 *
 */

public class EnderecoDto {

	@JsonInclude(Include.NON_NULL)
	private String cep;
	
	@JsonInclude(Include.NON_NULL)
	private String municipio;
	
	@JsonInclude(Include.NON_NULL)
	private String estado;
	
	@JsonInclude(Include.NON_NULL)
	private String logradouro;
	
	@JsonInclude(Include.NON_NULL)
	private Integer numero;
	
	@JsonInclude(Include.NON_NULL)
	private String complemento;
	
	@JsonInclude(Include.NON_NULL)
	private String bairro;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}



	
}
