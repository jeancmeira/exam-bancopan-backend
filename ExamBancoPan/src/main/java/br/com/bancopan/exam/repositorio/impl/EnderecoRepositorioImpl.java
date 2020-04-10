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
import br.com.bancopan.exam.dto.EstadoDTO;
import br.com.bancopan.exam.dto.MunicipioDTO;
import br.com.bancopan.exam.repositorio.def.EnderecoRepositorio;



@Component
public class EnderecoRepositorioImpl implements EnderecoRepositorio {

	@Override
	public Cep consultarCep(String codigoCep) {
		Cep cep = new Cep();
		
		CepDTO cepDTO = get(CepDTO.class, "http://viacep.com.br/ws/{codigoCep}/json", codigoCep);
		if (cepDTO == null || cepDTO.getCep() == null) {
			return null;
		} 
		
		cep.setCodigo(cepDTO.getCep());
		cep.setLogradouro(cepDTO.getLogradouro());
		return cep;
	}

	@Override
	public List<Estado> listarEstados() {
		EstadoDTO[] estadosDTO = get(EstadoDTO[].class, 
					"http://servicodados.ibge.gov.br/api/v1/localidades/estados/");
		
		if (estadosDTO == null || estadosDTO.length == 0) {
			return new ArrayList<>();
		}
		
		List<Estado> estados = new ArrayList<>();
		
		Estado estadoSP = new Estado(); 
		Estado estadoRJ = new Estado();
		
		for (EstadoDTO estadoDTO : estadosDTO ) {
			
			Estado estado = new Estado();
			estado.setSigla(estadoDTO.getSigla());
			estado.setNome(estadoDTO.getNome());
			
			if (estadoDTO.getSigla().equals("SP")) {
				
				estadoSP.setSigla(estadoDTO.getSigla());
				estadoSP.setNome(estadoDTO.getNome());
				
			} else if (estadoDTO.getSigla().equals("RJ")) {

				estadoRJ.setSigla(estadoDTO.getSigla());
				estadoRJ.setNome(estadoDTO.getNome());
				
			} else {
				estados.add(estado);	
			}
			
		}
		
		estados.add(0, estadoRJ);
		estados.add(0, estadoSP);
		
		return estados;
	}

	@Override
	public List<Municipio> consultarMunicipios(String sigla) {
		EstadoDTO[] estadosDTO = get(EstadoDTO[].class, 
				"http://servicodados.ibge.gov.br/api/v1/localidades/estados/");
	
		if (estadosDTO == null || estadosDTO.length == 0) {
			return new ArrayList<>();
		}

		EstadoDTO estadoDoMunicipioDTO = null;
		
		for (EstadoDTO estadoDTO : estadosDTO ) {
			
			if (estadoDTO.getSigla().equalsIgnoreCase(sigla)) {
				
				estadoDoMunicipioDTO = new EstadoDTO();
				estadoDoMunicipioDTO.setId(estadoDTO.getId());
				estadoDoMunicipioDTO.setSigla(estadoDTO.getSigla());
				estadoDoMunicipioDTO.setNome(estadoDTO.getNome());
				break;
			}
		}
		
		if (estadoDoMunicipioDTO == null) {
			return new ArrayList<>();
		}
		
		Estado estado = new Estado();
		estado.setSigla(estadoDoMunicipioDTO.getSigla());
		estado.setNome(estadoDoMunicipioDTO.getNome());
		
		MunicipioDTO[] municipiosDTO = get(MunicipioDTO[].class, 
				"http://servicodados.ibge.gov.br/api/v1/localidades/estados/{idEstado}/municipios",
							estadoDoMunicipioDTO.getId());
		
		if (municipiosDTO == null || municipiosDTO.length == 0) {
			return new ArrayList<>();
		}
		
		List<Municipio> municipios = new ArrayList<>();
		
		for (MunicipioDTO municipioDTO : municipiosDTO ) {
			Municipio municipio = new Municipio();
			municipio.setNome(municipioDTO.getNome());
			municipio.setEstado(estado);
			municipios.add(municipio);
		}
		
		return municipios;
	}

	private <T> T get(Class<T> resultClass, String url) {
		return get(resultClass, url, null);
	}
	
	private <T> T get(Class<T> resultClass, String url, Object parameterValue) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<T> response = restTemplate.exchange(
					url,
			        HttpMethod.GET,
			        new HttpEntity<>(new HttpHeaders()),
			        resultClass,
			        parameterValue
			);

			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			} else  {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
