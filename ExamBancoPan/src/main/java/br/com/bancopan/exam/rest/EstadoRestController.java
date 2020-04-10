package br.com.bancopan.exam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.usecase.EnderecoUseCase;

@RestController
@RequestMapping("/estado")
@CrossOrigin("*")
public class EstadoRestController {

	@Autowired
	private EnderecoUseCase enderecoUseCase;
	
	@GetMapping
	public List<Estado> listarEstados() {
		return enderecoUseCase.listarEstados();
	}
	
	@GetMapping("/{sigla}/municipio")
	public ResponseEntity<List<Municipio>> consultarMunicipios(@PathVariable String sigla) {
		List<Municipio> municipios = enderecoUseCase.consultarMunicipios(sigla);
		if (municipios != null && !municipios.isEmpty()) {
			return new ResponseEntity<>(municipios, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
