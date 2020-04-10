package br.com.bancopan.exam.rest;

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

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.usecase.ClienteUseCase;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteRestController {

	@Autowired
	private ClienteUseCase clienteUseCase;
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> consultarCliente(@PathVariable String cpf) {
		Cliente cliente = clienteUseCase.consultarCliente(cpf);
		if (cliente != null) {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{cpf}/endereco")
	public ResponseEntity<Boolean> alterarEndereco(@RequestBody Cliente cliente) {
		Boolean retorno = clienteUseCase.alterarEndereco(cliente);
		if (retorno) {
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
