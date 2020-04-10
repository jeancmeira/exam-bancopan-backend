package br.com.bancopan.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
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
		
		//TODO VALIDAR SE ESTA PREENCHIDO
		
		Cliente clienteBusca = consultarCliente(cliente.getCpf());
		if (clienteBusca == null) {
			return Boolean.FALSE;
		}
		
		Endereco endereco = clienteBusca.getEndereco();
		endereco.setBairro(cliente.getEndereco().getBairro());
		endereco.setNumero(cliente.getEndereco().getNumero());
		
		
		
		clienteBusca.alterarEndereco();
		
		return Boolean.TRUE;
	}

}
