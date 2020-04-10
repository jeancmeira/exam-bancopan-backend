package br.com.bancopan.exam.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.persistence.jpa.entity.ClienteEntity;
import br.com.bancopan.exam.persistence.jpa.repository.ClienteEntityJpaRepository;
import br.com.bancopan.exam.port.ClientePort;

@Component
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
		
		Cep cep = getCep(clienteEntity);
		
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		endereco.setNumero(clienteEntity.getNumero());
		endereco.setComplemento(clienteEntity.getComplemento());
		endereco.setBairro(clienteEntity.getBairro());
		
		Estado estado = getEstado(clienteEntity);
		endereco.setMunicipio(getMunicipio(clienteEntity, estado));
		
		cliente.setEndereco(endereco);
		
		cliente.setClientePort(this);
		
		return cliente;
	}

	@Override
	public Boolean alterarEndereco(Cliente cliente) {
		Optional<ClienteEntity> clienteEntityResult = clienteEntityJpaRepository.findByCpf(cliente.getCpf());
		
		if (!clienteEntityResult.isPresent()) {
			return null;
		}
		
		ClienteEntity clienteEntity = clienteEntityResult.get();
		clienteEntity.setCep(cliente.getCpf());
		clienteEntity.setLogradouro(cliente.getEndereco().getCep().getLogradouro());
		clienteEntity.setNumero(cliente.getEndereco().getNumero());
		clienteEntity.setComplemento(cliente.getEndereco().getComplemento());
		clienteEntity.setBairro(cliente.getEndereco().getBairro());
		clienteEntity.setMunicipio(cliente.getEndereco().getMunicipio().getNome());
		clienteEntity.setEstadoNome(cliente.getEndereco().getMunicipio().getEstado().getNome());
		clienteEntity.setEstadoSigla(cliente.getEndereco().getMunicipio().getEstado().getSigla());
		
		return Boolean.TRUE;
	}

	private Cep getCep(ClienteEntity clienteEntity) {
		Cep cep = new Cep();
		cep.setCodigo(clienteEntity.getCep());
		cep.setLogradouro(clienteEntity.getLogradouro());
		return cep;
	}
	
	private Municipio getMunicipio(ClienteEntity clienteEntity, Estado estado) {
		Municipio municipio = new Municipio();
		municipio.setEstado(estado);
		municipio.setNome(clienteEntity.getMunicipio());
		return municipio;
	}

	private Estado getEstado(ClienteEntity clienteEntity) {
		Estado estado = new Estado();
		estado.setSigla(clienteEntity.getEstadoSigla());
		estado.setNome(clienteEntity.getEstadoNome());
		return estado;
	}

}
