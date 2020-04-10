package br.com.bancopan.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.repositorio.def.EnderecoRepositorio;
import br.com.bancopan.exam.service.def.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@Override
	public Cep consultarCep(String codigoCep) {
		return enderecoRepositorio.consultarCep(codigoCep);
	}

	@Override
	public List<Estado> listarEstados() {
		return enderecoRepositorio.listarEstados();
	}

	@Override
	public List<Municipio> consultarMunicipios(String sigla) {
		return enderecoRepositorio.consultarMunicipios(sigla);
	}

}
