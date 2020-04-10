package br.com.bancopan.exam.service.def;

import org.springframework.web.bind.annotation.PathVariable;

import br.com.bancopan.exam.domain.Cliente;

public interface ClienteService {

	public Cliente consultarCliente(@PathVariable String cpf);

	public Boolean alterarEndereco(Cliente cliente); 
		
}
