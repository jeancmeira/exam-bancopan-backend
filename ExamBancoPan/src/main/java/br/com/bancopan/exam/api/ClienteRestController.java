package br.com.bancopan.exam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.api.dto.ClienteDto;
import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.usecase.ClienteUseCase;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteRestController {

	@Autowired
	private ClienteUseCase clienteUseCase;
	
	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteDto> consultarCliente(@PathVariable String cpf) {
		ClienteDto clienteDto = consultar(cpf);
		if (clienteDto != null) {
			return new ResponseEntity<>(clienteDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{cpf}/endereco")
	public ResponseEntity<Boolean> alterarEndereco(@RequestBody ClienteDto clienteDto) {
		
		Cliente cliente = clienteUseCase.consultarCliente(clienteDto.getCpf());
		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		cliente.setCpf(cliente.getCpf());
		cliente.getEndereco().setCep(clienteDto.getCep());
		cliente.getEndereco().setMunicipio(clienteDto.getMunicipio());
		cliente.getEndereco().setEstado(clienteDto.getEstado());
		cliente.getEndereco().setLogradouro(clienteDto.getLogradouro());
		cliente.getEndereco().setNumero(clienteDto.getNumero());
		cliente.getEndereco().setComplemento(clienteDto.getComplemento());
		cliente.getEndereco().setBairro(clienteDto.getBairro());
		
		clienteUseCase.alterarEndereco(cliente); 
		
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}
	
	private ClienteDto consultar(String cpf) {
		Cliente cliente = clienteUseCase.consultarCliente(cpf);
		
		if (cliente == null) {
			return null;
		}
		
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setCpf(cliente.getCpf());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setCep(cliente.getEndereco().getCep());
		clienteDto.setMunicipio(cliente.getEndereco().getMunicipio());
		clienteDto.setEstado(cliente.getEndereco().getEstado());
		clienteDto.setLogradouro(cliente.getEndereco().getLogradouro());
		clienteDto.setNumero(cliente.getEndereco().getNumero());
		clienteDto.setComplemento(cliente.getEndereco().getComplemento());
		clienteDto.setBairro(cliente.getEndereco().getBairro());
		
		return clienteDto;
	}

}
