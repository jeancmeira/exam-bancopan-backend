package br.com.bancopan.exam.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.domain.Cliente;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteRestController {

	@GetMapping
	public List<Cliente> consultarClientes(@RequestParam String cpf) {
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo(1L);
		cliente.setCpf("212.508.688-31");
		cliente.setNome("Cliente 1");
		clientes.add(cliente);
		
		return clientes;
	}
	
}
