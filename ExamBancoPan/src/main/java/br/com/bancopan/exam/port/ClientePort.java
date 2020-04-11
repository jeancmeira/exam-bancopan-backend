package br.com.bancopan.exam.port;

import org.springframework.web.bind.annotation.PathVariable;

import br.com.bancopan.exam.domain.Cliente;

/**
 * 
 * @author Jean
 *
 *Classe port de clientes segunda a arquitetura hexagonal (clean)
 *
 */
public interface ClientePort {

	public Cliente consultarCliente(@PathVariable String cpf);

	public Boolean alterarEndereco(Cliente cliente); 
		
}
