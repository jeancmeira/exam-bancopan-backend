package br.com.bancopan.exam.validation;

public class CampoObrigatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CampoObrigatorioException(String campo) {
		super(campo + " obrigatorio.");
	}
	
}
