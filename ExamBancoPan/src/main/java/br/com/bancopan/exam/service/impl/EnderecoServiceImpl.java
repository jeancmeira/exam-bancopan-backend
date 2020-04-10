package br.com.bancopan.exam.service.impl;

import java.util.ArrayList;
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
		
		Estado estado = getEstado();
		
		Municipio municipio = getMunicipio(estado);
		
		Cep cep = new Cep();
		cep.setCodigo("04349000");
		cep.setLogradouro("RUA DAS GRUMIXAMAS");
		cep.setMunicipio(municipio);
		
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
		estado.setId(1L);
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

}
