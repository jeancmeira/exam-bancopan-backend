package br.com.bancopan.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.port.ClientePort;
import br.com.bancopan.exam.usecase.ClienteUseCase;
import br.com.bancopan.exam.validation.CpfObrigatorioException;

@Service
public class ClienteService implements ClienteUseCase {

	@Autowired
	private ClientePort clientePort;
	
	@Override
	public Cliente consultarCliente(String cpf) {
		
		if (cpf == null || cpf.trim().equals("")) {
			throw new CpfObrigatorioException();
		}
		
		return clientePort.consultarCliente(cpf);
	}

	@Override
	public void alterarEndereco(Cliente cliente) {
		cliente.alterarEndereco();
	}

}
