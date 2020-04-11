package br.com.bancopan.exam.adapter.jpa;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.persistence.jpa.entity.ClienteEntity;
import br.com.bancopan.exam.persistence.jpa.repository.ClienteEntityJpaRepository;
import br.com.bancopan.exam.port.ClientePort;

/**
 * @author Jean
 *  
 * Classe adapter de clientes de acordo com a arquitetura hexagonal (clean) 
 *
 */
@Service
public class ClienteJpaAdapter implements ClientePort {

	@Autowired
	private ClienteEntityJpaRepository clienteEntityJpaRepository;
	
	@Override
	public Cliente consultarCliente(String cpf) {
		
		Optional<ClienteEntity> clienteEntityResult = clienteEntityJpaRepository.findByCpf(cpf);
		
		if (!clienteEntityResult.isPresent()) {
			return null;
		}
		
		ClienteEntity clienteEntity = clienteEntityResult.get();
		
		Cliente cliente = new Cliente();
		cliente.setCpf(clienteEntity.getCpf());
		cliente.setNome(clienteEntity.getNome());
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro(clienteEntity.getLogradouro());
		endereco.setCep(clienteEntity.getCep());
		endereco.setNumero(clienteEntity.getNumero());
		endereco.setComplemento(clienteEntity.getComplemento());
		endereco.setBairro(clienteEntity.getBairro());
		endereco.setEstado(clienteEntity.getEstado());
		endereco.setMunicipio(clienteEntity.getMunicipio());
		cliente.setEndereco(endereco);
		
		return cliente;
	}

	@Override
	@Transactional
	public Boolean alterarEndereco(Cliente cliente) {
		Optional<ClienteEntity> clienteEntityResult = clienteEntityJpaRepository.findByCpf(cliente.getCpf());
		
		if (!clienteEntityResult.isPresent()) {
			return null;
		}
		
		ClienteEntity clienteEntity = clienteEntityResult.get();
		clienteEntity.setCep(cliente.getEndereco().getCep());
		clienteEntity.setLogradouro(cliente.getEndereco().getLogradouro());
		clienteEntity.setNumero(cliente.getEndereco().getNumero());
		clienteEntity.setComplemento(cliente.getEndereco().getComplemento());
		clienteEntity.setBairro(cliente.getEndereco().getBairro());
		clienteEntity.setMunicipio(cliente.getEndereco().getMunicipio());
		clienteEntity.setEstado(cliente.getEndereco().getEstado());
		
		clienteEntityJpaRepository.save(clienteEntity);
		
		return Boolean.TRUE;
	}


}
