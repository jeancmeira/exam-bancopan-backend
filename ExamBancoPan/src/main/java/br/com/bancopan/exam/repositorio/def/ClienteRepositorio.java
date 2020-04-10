package br.com.bancopan.exam.repositorio.def;

import org.springframework.web.bind.annotation.PathVariable;

import br.com.bancopan.exam.domain.Cliente;

public interface ClienteRepositorio {

	public Cliente consultarCliente(@PathVariable String cpf);

	public void alterarEndereco(Cliente cliente); 
		
}
