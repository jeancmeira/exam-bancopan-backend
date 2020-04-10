package br.com.bancopan.exam.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.service.def.EnderecoService;

public class MunicipioRestControler {

	@Autowired
	private EnderecoService enderecoService;
	
	public List<Municipio> consultarMunicipios(Long idEstado) {
		return enderecoService.consultarMunicipios(idEstado);
	}
	
}
