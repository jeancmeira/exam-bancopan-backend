package br.com.bancopan.exam.usecase;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;

public interface EnderecoUseCase {

	public Cep consultarCep(@RequestParam String codigoCep);
	
	public List<Estado> listarEstados();
	
	public List<Municipio> consultarMunicipios(String sigla);
	
}