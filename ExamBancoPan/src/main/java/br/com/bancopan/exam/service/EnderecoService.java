package br.com.bancopan.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.port.EnderecoPort;
import br.com.bancopan.exam.usecase.EnderecoUseCase;

@Service
public class EnderecoService implements EnderecoUseCase {

	@Autowired
	private EnderecoPort enderecoPort;
	
	@Override
	public Cep consultarCep(String codigoCep) {
		return enderecoPort.consultarCep(codigoCep);
	}

	@Override
	public List<Estado> listarEstados() {
		return enderecoPort.listarEstados();
	}

	@Override
	public List<Municipio> consultarMunicipios(String sigla) {
		return enderecoPort.consultarMunicipios(sigla);
	}

}