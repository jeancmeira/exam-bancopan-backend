package br.com.bancopan.exam.repositorio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.dto.CepDTO;
import br.com.bancopan.exam.repositorio.def.EnderecoRepositorio;



@Component
public class EnderecoRepositorioImpl implements EnderecoRepositorio {

	@Override
	public Cep consultarCep(String codigoCep) {
		Cep cep = new Cep();
		
		CepDTO cepDTO = consultaServicoCep(codigoCep);
		if (cepDTO == null) {
			return null;
		}
		
		cep.setCodigo(cepDTO.getCep());
		cep.setLogradouro(cepDTO.getLogradouro());
		return cep;
	}

	private Municipio getMunicipio(Estado estado) {
		Municipio municipio = new Municipio();
		municipio.setEstado(estado);
		municipio.setNome("SAO PAULO");
		return municipio;
	}

	private Estado getEstado() {
		Estado estado = new Estado();
		estado.setSigla("SP");
		estado.setNome("SAO PAULO");
		return estado;
	}

	@Override
	public List<Estado> listarEstados() {
		List<Estado> estados = new ArrayList<>();
		estados.add(getEstado());
		return estados;
	}

	@Override
	public List<Municipio> consultarMunicipios(Long idEstado) {
		Estado estado = getEstado();
		List<Municipio> municipios = new ArrayList<>();
		municipios.add(getMunicipio(estado));
		return municipios;
	}
	
	private CepDTO consultaServicoCep(String codigoCep) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<CepDTO> response = restTemplate.exchange(
					"http://viacep.com.br/ws/{codigoCep}/json",
			        HttpMethod.GET,
			        new HttpEntity<>(new HttpHeaders()),
			        CepDTO.class,
			        codigoCep
			);

			if (response.getStatusCode() == HttpStatus.OK) {
				
				CepDTO result = response.getBody();
				if (Boolean.TRUE.equals(result.getErro())) {
					return null;
				}
				
				return result;
				
			} else  {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
}
