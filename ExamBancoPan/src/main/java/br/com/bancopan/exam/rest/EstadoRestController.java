package br.com.bancopan.exam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.service.def.EnderecoService;

public class EstadoRestController {

	@Autowired
	private EnderecoService enderecoService;
	
	public List<Estado> listarEstados() {
		return enderecoService.listarEstados();
	}
	
}
