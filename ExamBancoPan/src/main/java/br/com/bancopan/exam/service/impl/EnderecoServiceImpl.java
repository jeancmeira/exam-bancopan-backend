package br.com.bancopan.exam.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.service.def.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Override
	public Cep consultarCep(String codigoCep) {
		Cep cep = new Cep();
		
		return cep;
	}

	@Override
	public List<Estado> listarEstados() {
		return null;
	}

	@Override
	public List<Municipio> consultarMunicipios(Long idEstado) {
		return null;
	}

}
