package br.com.bancopan.exam.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancopan.exam.api.dto.EstadoDto;
import br.com.bancopan.exam.api.dto.MunicipioDto;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.usecase.EnderecoUseCase;

@RestController
@RequestMapping("/estado")
@CrossOrigin("*")
public class EstadoRestController {

	Logger logger = LoggerFactory.getLogger(EstadoRestController.class);
	
	@Autowired
	private EnderecoUseCase enderecoUseCase;
	
	@GetMapping
	public List<EstadoDto> listarEstados() {
		
		logger.debug("Acessando GET /estado");
		
		List<Estado> estados = enderecoUseCase.listarEstados();
		
		List<EstadoDto> estadosDto = new ArrayList<>();
		
		for (Estado estado : estados) {
			estadosDto.add(new EstadoDto(estado.getSigla(), estado.getNome()));
		}
		
		return estadosDto;
	}
	
	@GetMapping("/{sigla}/municipio")
	public ResponseEntity<List<MunicipioDto>> consultarMunicipios(@PathVariable String sigla) {
		
		logger.debug("Acessando GET /estado/{}/municipio", sigla);
		
		List<MunicipioDto> municipiosDto = consultar(sigla);
		
		if (municipiosDto != null) {
			return new ResponseEntity<>(municipiosDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private List<MunicipioDto> consultar(String sigla) {
		List<Municipio> municipios= enderecoUseCase.consultarMunicipios(sigla);
		
		if (municipios == null || municipios.isEmpty()) {
			return null;
		}
		
		List<MunicipioDto> municipiosDto = new ArrayList<>();
		
		for (Municipio municipio : municipios) {
			municipiosDto.add(new MunicipioDto(municipio.getEstado(), municipio.getNome()));
		}

		
		return municipiosDto;
	}
	
}
