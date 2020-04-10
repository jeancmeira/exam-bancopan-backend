package br.com.bancopan.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.service.def.ClienteService;
import br.com.bancopan.exam.service.def.EnderecoService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private EnderecoService enderecoService;
	
	@Override
	public Cliente consultarCliente(String cpf) {
		Cliente cliente = new Cliente();
		cliente.setCpf("212.508.688-31");
		cliente.setNome("Cliente 1");
		
		Endereco endereco = new Endereco();
		endereco.setCep(enderecoService.consultarCep(null));
		endereco.setNumero(327);
		endereco.setComplemento("AP 103");
		cliente.setEndereco(endereco);
		
		return cliente;
	}

	@Override
	public Boolean alterarEndereco(Cliente cliente) {
		return Boolean.TRUE;
	}

}
