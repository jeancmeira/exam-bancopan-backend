package br.com.bancopan.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.service.def.ClienteService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{cpf}")
	public Cliente consultarCliente(@PathVariable String cpf) {
		return clienteService.consultarCliente(cpf);
	}
	
	public Boolean alterarEndereco(Cliente cliente) {
		clienteService.consultarCliente(cliente);
		return Boolean.TRUE;
	}
	
}
