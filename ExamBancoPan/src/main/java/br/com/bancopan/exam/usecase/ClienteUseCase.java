package br.com.bancopan.exam.usecase;

import org.springframework.web.bind.annotation.PathVariable;

import br.com.bancopan.exam.domain.Cliente;

/**
 * 
 * @author Jean
 *
 *Interface use case de clientes segundo a arquitetura hexagonal (clean)
 *
 */
public interface ClienteUseCase {

	public Cliente consultarCliente(@PathVariable String cpf);

	public void alterarEndereco(Cliente cliente); 
		
}
