package br.com.bancopan.exam.api;

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

import br.com.bancopan.exam.api.dto.CepDto;
import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.usecase.EnderecoUseCase;

/**
 * 
 * @author Jean
 * 
 * Classe controller de cep
 * 
 */
@RestController
@RequestMapping("/cep")
@CrossOrigin("*")
public class CepRestController {
	
	Logger logger = LoggerFactory.getLogger(CepRestController.class);
	
	@Autowired
	private EnderecoUseCase enderecoUseCase;

	@GetMapping("/{codigoCep}")
	public ResponseEntity<CepDto> consultarCep(@PathVariable String codigoCep) {
		
		logger.debug("Acessando GET /cep/{}", codigoCep);
		
		CepDto cepDto = consultar(codigoCep);
		if (cepDto != null) {
			return new ResponseEntity<>(cepDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private CepDto consultar(String codigoCep) {
		Cep cep = enderecoUseCase.consultarCep(codigoCep);
		
		if (cep == null) {
			return null;
		}
		
		return new CepDto(cep.getCodigo(), cep.getLogradouro());
	}
	
}
