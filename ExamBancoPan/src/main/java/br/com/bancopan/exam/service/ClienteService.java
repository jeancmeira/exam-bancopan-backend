package br.com.bancopan.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.port.ClientePort;
import br.com.bancopan.exam.usecase.ClienteUseCase;
import br.com.bancopan.exam.validation.CampoObrigatorioException;

@Service
public class ClienteService implements ClienteUseCase {

	@Autowired
	private ClientePort clientePort;
	
	@Override
	public Cliente consultarCliente(String cpf) {
		
		if (cpf == null || cpf.trim().equals("")) {
			throw new CampoObrigatorioException("cpf");
		}
		
		return clientePort.consultarCliente(cpf);
	}

	@Override
	public void alterarEndereco(Cliente cliente) {
		
		if (cliente.getCpf() == null || cliente.getCpf().trim().equals("")) {
			throw new CampoObrigatorioException("cpf");
		}
		
		Endereco endereco = cliente.getEndereco();
		
		if (endereco == null || endereco.getCep() == null 
				|| endereco.getCep().trim().equals("")) {
			throw new CampoObrigatorioException("cep");
		}

		if (endereco.getLogradouro() == null 
				|| endereco.getLogradouro().trim().equals("")) {
			throw new CampoObrigatorioException("logradouro");
		}

		if (endereco.getNumero() == null 
				|| endereco.getNumero().intValue() == 0) {
			throw new CampoObrigatorioException("numero");
		}

		if (endereco.getBairro() == null 
				|| endereco.getBairro().trim().equals("")) {
			throw new CampoObrigatorioException("bairro");
		}

		if (endereco.getMunicipio() == null 
				|| endereco.getMunicipio().trim().equals("")) {
			throw new CampoObrigatorioException("municipio");
		}

		if (endereco.getEstado() == null 
				|| endereco.getEstado().trim().equals("")) {
			throw new CampoObrigatorioException("estado");
		}

		
		clientePort.alterarEndereco(cliente);		
		
	}

}
