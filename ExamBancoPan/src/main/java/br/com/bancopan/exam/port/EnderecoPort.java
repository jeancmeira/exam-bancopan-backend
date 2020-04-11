package br.com.bancopan.exam.port;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;

/**
 * 
 * @author Jean
 *
 *Interface port de endereco segundo a arquitetura hexagonal (clean)
 *
 */

public interface EnderecoPort {

	public Cep consultarCep(@RequestParam String codigoCep);
	
	public List<Estado> listarEstados();
	
	public List<Municipio> consultarMunicipios(String sigla);
	
}
