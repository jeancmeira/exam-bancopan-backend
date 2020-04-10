package br.com.bancopan.exam.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.domain.Cliente;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteRestController {

	@GetMapping("/{cpf}")
	public Cliente consultarCliente(@PathVariable String cpf) {

		Cliente cliente = new Cliente();
		cliente.setCodigo(1L);
		cliente.setCpf("212.508.688-31");
		cliente.setNome("Cliente 1");
		
		return cliente;
	}
	
	public Boolean alterarEndereco(Cliente cliente) {
		return Boolean.TRUE;
	}
	
}
