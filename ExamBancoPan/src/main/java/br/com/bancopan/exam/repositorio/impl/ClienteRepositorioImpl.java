package br.com.bancopan.exam.repositorio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.repositorio.def.ClienteRepositorio;
import br.com.bancopan.exam.service.def.EnderecoService;

@Component
public class ClienteRepositorioImpl implements ClienteRepositorio {

	@Autowired
	private EnderecoService enderecoService;//teste
	
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

}
