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
		
		Cliente clienteExistente = consultarCliente(cliente.getCpf());
		if (clienteExistente == null) {
			return Boolean.FALSE;
		}
		
		Endereco endereco = clienteExistente.getEndereco();
		
		endereco.setCep(cliente.getEndereco().getCep());
		endereco.setLogradouro(cliente.getEndereco().getLogradouro());
		endereco.setNumero(cliente.getEndereco().getNumero());
		endereco.setComplemento(cliente.getEndereco().getComplemento());
		endereco.setBairro(cliente.getEndereco().getBairro());
		endereco.setMunicipio(cliente.getEndereco().getMunicipio());
		endereco.setEstado(cliente.getEndereco().getEstado());
		
		clienteExistente.alterarEndereco();
		
		return Boolean.TRUE;
	}

}
