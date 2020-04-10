package br.com.bancopan.exam.service.impl;

import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.service.def.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Override
	public Cliente consultarCliente(String cpf) {
		Cliente cliente = new Cliente();
		cliente.setCpf("212.508.688-31");
		cliente.setNome("Cliente 1");
		return cliente;
	}

	@Override
	public void alterarEndereco(Cliente cliente) {
		
	}

}
