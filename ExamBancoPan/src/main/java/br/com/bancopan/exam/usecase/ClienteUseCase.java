package br.com.bancopan.exam.usecase;

import org.springframework.web.bind.annotation.PathVariable;

import br.com.bancopan.exam.domain.Cliente;

public interface ClienteUseCase {

	public Cliente consultarCliente(@PathVariable String cpf);

	public Boolean alterarEndereco(Cliente cliente); 
		
}
