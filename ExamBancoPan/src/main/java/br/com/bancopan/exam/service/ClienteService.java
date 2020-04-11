package br.com.bancopan.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.port.ClientePort;
import br.com.bancopan.exam.usecase.ClienteUseCase;
import br.com.bancopan.exam.validation.CampoObrigatorioException;
import br.com.bancopan.exam.validation.TamanhoExcedidoException;

/**
 * 
 * @author Jean
 *
 *Classe service (implementacao de use case) de clientes segundo a arquitetura hexagonal (clean)
 *
 */
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

		if (endereco.getCep().length() > 11) {
			throw new TamanhoExcedidoException("cep", 11);
		}

		///////
		
		if (endereco.getLogradouro() == null 
				|| endereco.getLogradouro().trim().equals("")) {
			throw new CampoObrigatorioException("logradouro");
		}
		
		if (endereco.getLogradouro().length() > 1000) {
			throw new TamanhoExcedidoException("logradouro", 1000);
		}
		
		///////
		
		if (endereco.getNumero() == null 
				|| endereco.getNumero().intValue() == 0) {
			throw new CampoObrigatorioException("numero");
		}
		
		/////
		
		if (endereco.getComplemento() != null 
				&& !endereco.getComplemento().trim().equals("")) {
			
			if (endereco.getComplemento().length() > 1000) {
				throw new TamanhoExcedidoException("complemento", 1000);
			}
			
			
		}
		
		
		
		///////
		
		if (endereco.getBairro() == null 
				|| endereco.getBairro().trim().equals("")) {
			throw new CampoObrigatorioException("bairro");
		}
		
		if (endereco.getBairro().length() > 500) {
			throw new TamanhoExcedidoException("bairro", 500);
		}
		
		
		///////

		if (endereco.getMunicipio() == null 
				|| endereco.getMunicipio().trim().equals("")) {
			throw new CampoObrigatorioException("municipio");
		}

		if (endereco.getMunicipio().length() > 500) {
			throw new TamanhoExcedidoException("municipio", 500);
		}
		
		
		///////
		
		if (endereco.getEstado() == null 
				|| endereco.getEstado().trim().equals("")) {
			throw new CampoObrigatorioException("estado");
		}
		
		if (endereco.getEstado().length() > 2) {
			throw new TamanhoExcedidoException("estado", 2);
		}
		

		
		clientePort.alterarEndereco(cliente);		
		
	}

}
