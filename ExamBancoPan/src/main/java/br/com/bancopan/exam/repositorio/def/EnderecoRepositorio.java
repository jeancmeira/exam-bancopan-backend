package br.com.bancopan.exam.repositorio.def;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;

public interface EnderecoRepositorio {

	public Cep consultarCep(@RequestParam String codigoCep);
	
	public List<Estado> listarEstados();
	
	public List<Municipio> consultarMunicipios(Long idEstado);
	
}
