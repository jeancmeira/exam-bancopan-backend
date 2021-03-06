package br.com.bancopan.exam.adapter.rest.dto;

/**
 * @author Jean
 *  
 * Dto para obter informacoes do servico rest de cep 
 *
 */
public class CepAdapterDto {
	
	private String cep;
	
	private String logradouro;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
}
