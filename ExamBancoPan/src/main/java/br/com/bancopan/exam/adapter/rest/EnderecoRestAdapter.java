package br.com.bancopan.exam.adapter.rest;

import java.util.ArrayList;
import java.util.Arrays;
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
import br.com.bancopan.exam.port.EnderecoPort;



@Component
public class EnderecoRestAdapter implements EnderecoPort {

	@Override
	public Cep consultarCep(String codigoCep) {
		CepAdapterDto cepAdapterDto = get(CepAdapterDto.class, "http://viacep.com.br/ws/{codigoCep}/json", codigoCep);
		if (cepAdapterDto == null || cepAdapterDto.getCep() == null) {
			return null;
		} 
		
		return new Cep(cepAdapterDto.getCep(),
				cepAdapterDto.getLogradouro());
	}

	@Override
	public List<Estado> listarEstados() {
		EstadoAdapterDto[] estadosAdapterDto = get(EstadoAdapterDto[].class, 
					"http://servicodados.ibge.gov.br/api/v1/localidades/estados/");
		
		if (estadosAdapterDto == null || estadosAdapterDto.length == 0) {
			return new ArrayList<>();
		}
		
		List<EstadoAdapterDto> estadoAdapterDtoListaOrdenada = Arrays.asList(estadosAdapterDto);
		estadoAdapterDtoListaOrdenada.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
		
		List<Estado> estados = new ArrayList<>();
		
		Estado estadoSP = null; 
		Estado estadoRJ = null;
		
		for (EstadoAdapterDto estadoAdapterDto : estadoAdapterDtoListaOrdenada ) {
			
			if (estadoAdapterDto.getSigla().equals("SP")) {
				
				estadoSP = new Estado(estadoAdapterDto.getSigla(), estadoAdapterDto.getNome());
				
			} else if (estadoAdapterDto.getSigla().equals("RJ")) {
				
				estadoRJ = new Estado(estadoAdapterDto.getSigla(), estadoAdapterDto.getNome());
				
			} else {
				
				estados.add(new Estado(estadoAdapterDto.getSigla(), estadoAdapterDto.getNome()));
				
			}
			
		}
		
		if (estadoRJ != null) {
			estados.add(0, estadoRJ);
		}
		
		if (estadoSP != null) {
			estados.add(0, estadoSP);
		}
		
		return estados;
	}

	@Override
	public List<Municipio> consultarMunicipios(String sigla) {
		EstadoAdapterDto[] estadosAdapterDto = get(EstadoAdapterDto[].class, 
				"http://servicodados.ibge.gov.br/api/v1/localidades/estados/");
	
		if (estadosAdapterDto == null || estadosAdapterDto.length == 0) {
			return new ArrayList<>();
		}

		EstadoAdapterDto estadoDoMunicipioAdapterDto = null;
		
		for (EstadoAdapterDto estadoAdapterDto : estadosAdapterDto ) {
			
			if (estadoAdapterDto.getSigla().equalsIgnoreCase(sigla)) {
				
				estadoDoMunicipioAdapterDto = new EstadoAdapterDto();
				estadoDoMunicipioAdapterDto.setId(estadoAdapterDto.getId());
				estadoDoMunicipioAdapterDto.setSigla(estadoAdapterDto.getSigla());
				estadoDoMunicipioAdapterDto.setNome(estadoAdapterDto.getNome());
				break;
			}
		}
		
		if (estadoDoMunicipioAdapterDto == null) {
			return new ArrayList<>();
		}
		
		Estado estado = new Estado(estadoDoMunicipioAdapterDto.getSigla(),
						estadoDoMunicipioAdapterDto.getNome());
		
		MunicipioAdapterDto[] municipiosAdapterDto = get(MunicipioAdapterDto[].class, 
				"http://servicodados.ibge.gov.br/api/v1/localidades/estados/{idEstado}/municipios",
							estadoDoMunicipioAdapterDto.getId());
		
		if (municipiosAdapterDto == null || municipiosAdapterDto.length == 0) {
			return new ArrayList<>();
		}
		
		List<Municipio> municipios = new ArrayList<>();
		
		for (MunicipioAdapterDto municipioAdapterDto : municipiosAdapterDto ) {
			municipios.add(new Municipio(estado.getSigla(), municipioAdapterDto.getNome()));
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
