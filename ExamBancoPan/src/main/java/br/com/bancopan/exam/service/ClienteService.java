package br.com.bancopan.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.port.ClientePort;
import br.com.bancopan.exam.usecase.ClienteUseCase;

@Service
public class ClienteService implements ClienteUseCase {

	@Autowired
	private ClientePort clientePort;
	
	@Override
	public Cliente consultarCliente(String cpf) {
		return clientePort.consultarCliente(cpf);
	}

	@Override
	public Boolean alterarEndereco(Cliente cliente) {
		
		Cliente clienteBusca = consultarCliente(cliente.getCpf());
		if (clienteBusca == null) {
			return Boolean.FALSE;
		}
		
		clienteBusca.alterarEndereco();
		
		return Boolean.TRUE;
	}

}