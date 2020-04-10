package br.com.bancopan.exam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.service.def.EnderecoService;

@RestController
@RequestMapping("/estado")
@CrossOrigin("*")
public class EstadoRestController {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public List<Estado> listarEstados() {
		return enderecoService.listarEstados();
	}
	
}
