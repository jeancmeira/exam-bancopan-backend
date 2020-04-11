package br.com.bancopan.exam.validation;

/**
 * 
 * @author Jean
 *
 *Classe de validacao de tamanho de campo
 */
public class TamanhoExcedidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String campo;
	
	public TamanhoExcedidoException(String campo, int tamanho) {
		super("O " + campo + " pode conter ate " + tamanho + " caracteres.");
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}
	
}
