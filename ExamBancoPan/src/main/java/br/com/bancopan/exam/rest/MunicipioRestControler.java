package br.com.bancopan.exam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.service.def.EnderecoService;

@RestController
@RequestMapping("/municipio")
@CrossOrigin("*")
public class MunicipioRestControler {

	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public List<Municipio> consultarMunicipios(@RequestParam Long idEstado) {
		return enderecoService.consultarMunicipios(idEstado);
	}
	
}
