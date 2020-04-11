package br.com.bancopan.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.main.ExamBancoPanApplication;
import br.com.bancopan.exam.port.EnderecoPort;
import br.com.bancopan.exam.service.EnderecoService;
import br.com.bancopan.exam.validation.CampoObrigatorioException;

@SpringBootTest(classes = ExamBancoPanApplication.class)
public class EnderecoServiceTest {

	@InjectMocks
	private EnderecoService enderecoService;

	@MockBean
	private EnderecoPort enderecoPort;

	@Test
	public void testConsultaCep() {
		
		Mockito.when(enderecoPort.consultarCep(
				anyString())).thenReturn(retornaCepNull());

		boolean hasError = false;
		
		try {
			enderecoService.consultarCep(null);
		} catch (CampoObrigatorioException e) {
			hasError = true;
			assertEquals("codigoCep", e.getCampo());
		}
		
		assertTrue(hasError);
		
	}

	@Test
	public void testConsultaMunicipios() {
		
		Mockito.when(enderecoPort.consultarMunicipios(
				anyString())).thenReturn(retornaListMunicipiosNull());

		boolean hasError = false;
		
		try {
			enderecoService.consultarMunicipios(null);
		} catch (CampoObrigatorioException e) {
			hasError = true;
			assertEquals("estado", e.getCampo());
		}
		
		assertTrue(hasError);
		
	}

	private List<Municipio> retornaListMunicipiosNull() {
		return null;
	}

	private Cep retornaCepNull() {
		return null;
	}
	

}
