package br.com.bancopan.exam.validation;

/**
 * 
 * @author Jean
 *
 *Classe de validacao de campo obrigatorio
 */
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
