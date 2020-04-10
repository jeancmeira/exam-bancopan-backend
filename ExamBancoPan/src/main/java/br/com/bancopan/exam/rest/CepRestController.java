package br.com.bancopan.exam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<Cep> consultarCep(@PathVariable String codigoCep) {
		Cep cep = enderecoService.consultarCep(codigoCep);
		if (cep != null) {
			return new ResponseEntity<>(cep, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
