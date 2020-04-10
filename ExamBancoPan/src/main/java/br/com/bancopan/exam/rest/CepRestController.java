package br.com.bancopan.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.service.def.EnderecoService;

public class CepRestController {
	
	@Autowired
	private EnderecoService enderecoService;

	public Cep consultarCep(@RequestParam String codigoCep) {
		return enderecoService.consultarCep(codigoCep);
	}
	
}
