package br.com.bancopan.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.service.def.EnderecoService;

@RestController
@RequestMapping("/cep")
@CrossOrigin("*")
public class CepRestController {
	
	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/{codigoCep}")
	public Cep consultarCep(@RequestParam String codigoCep) {
		return enderecoService.consultarCep(codigoCep);
	}
	
}
