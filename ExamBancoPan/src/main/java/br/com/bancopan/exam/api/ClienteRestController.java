package br.com.bancopan.exam.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import br.com.bancopan.exam.api.dto.EnderecoDto;
import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.usecase.ClienteUseCase;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteRestController {

	Logger logger = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Autowired
	private ClienteUseCase clienteUseCase;
	
	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteDto> consultarCliente(@PathVariable String cpf) {
		
        logger.debug("Acessando GET /cliente/{}", cpf);
        
		ClienteDto clienteDto = consultar(cpf);
		if (clienteDto != null) {
			return new ResponseEntity<>(clienteDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{cpf}/endereco")
	public ResponseEntity<Boolean> alterarEndereco(@PathVariable String cpf, @RequestBody EnderecoDto enderecoDto) {
		
		logger.debug("Acessando POST /cliente/{}/endereco", cpf);
		logger.debug("Informacoes do body: ");
		logger.debug("cep: " + enderecoDto.getCep());
		logger.debug("Municipio: " + enderecoDto.getMunicipio());
		logger.debug("Estado: " + enderecoDto.getEstado());
		logger.debug("Logradouro: " + enderecoDto.getLogradouro());
		logger.debug("Numero: " + enderecoDto.getNumero());
		logger.debug("Complemento: " + enderecoDto.getComplemento());
		logger.debug("Bairro: " + enderecoDto.getBairro());
		
		Cliente cliente = clienteUseCase.consultarCliente(cpf);
		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		cliente.getEndereco().setCep(enderecoDto.getCep());
		cliente.getEndereco().setMunicipio(enderecoDto.getMunicipio());
		cliente.getEndereco().setEstado(enderecoDto.getEstado());
		cliente.getEndereco().setLogradouro(enderecoDto.getLogradouro());
		cliente.getEndereco().setNumero(enderecoDto.getNumero());
		cliente.getEndereco().setComplemento(enderecoDto.getComplemento());
		cliente.getEndereco().setBairro(enderecoDto.getBairro());
		
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
		
		EnderecoDto enderecoDto = new EnderecoDto();
		enderecoDto.setCep(cliente.getEndereco().getCep());
		enderecoDto.setMunicipio(cliente.getEndereco().getMunicipio());
		enderecoDto.setEstado(cliente.getEndereco().getEstado());
		enderecoDto.setLogradouro(cliente.getEndereco().getLogradouro());
		enderecoDto.setNumero(cliente.getEndereco().getNumero());
		enderecoDto.setComplemento(cliente.getEndereco().getComplemento());
		enderecoDto.setBairro(cliente.getEndereco().getBairro());
		clienteDto.setEndereco(enderecoDto);
		
		return clienteDto;
	}

}
