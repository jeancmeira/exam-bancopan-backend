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
public class ClienteAdapter implements ClientePort {

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


	@Override
	public void alterarEndereco(Cliente cliente) {
		System.out.println(cliente.getCpf());
		System.out.println(cliente.getNome());
		System.out.println(cliente.getEndereco().getNumero());
		System.out.println(cliente.getEndereco().getCep().getCodigo());
		System.out.println(cliente.getEndereco().getMunicipio().getNome());
		System.out.println(cliente.getEndereco().getMunicipio().getEstado().getSigla());
		
	}

}
