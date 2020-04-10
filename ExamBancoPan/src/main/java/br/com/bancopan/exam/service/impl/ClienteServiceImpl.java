package br.com.bancopan.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.repositorio.def.ClienteRepositorio;
import br.com.bancopan.exam.service.def.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Override
	public Cliente consultarCliente(String cpf) {
		return clienteRepositorio.consultarCliente(cpf);
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
