package br.com.bancopan.exam.repositorio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.repositorio.def.ClienteRepositorio;
import br.com.bancopan.exam.service.def.EnderecoService;

@Component
public class ClienteRepositorioImpl implements ClienteRepositorio {

	@Autowired
	@JsonIgnore
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
		
		cliente.setClienteRepositorio(this);
		
		return cliente;
	}

	@Override
	public void alterarEndereco(Cliente cliente) {
		System.out.println(cliente.getCpf());
		System.out.println(cliente.getNome());
		System.out.println(cliente.getEndereco().getNumero());
		System.out.println(cliente.getEndereco().getCep().getMunicipio().getEstado().getNome());
	}

}
