package br.com.bancopan.exam.validation;

public class CampoObrigatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String campo;
	
	public CampoObrigatorioException(String campo) {
		super("O " + campo + " tem preenchimento obrigatorio.");
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}
}
