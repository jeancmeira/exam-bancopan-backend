package br.com.bancopan.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bancopan.exam.api.CepRestController;
import br.com.bancopan.exam.api.dto.CepDto;
import br.com.bancopan.exam.domain.Cep;
import br.com.bancopan.exam.main.ExamBancoPanApplication;
import br.com.bancopan.exam.usecase.EnderecoUseCase;

@SpringBootTest(classes = ExamBancoPanApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CepRestControllerTest {
	
	private static final String URL_CONSULTA_CEP = "/cep/{codigoCep}";

	private static final String LOGRADOURO = "RUA TESTE";

	private static final String CEP = "12345-679";

	private static final int HTTP_STATUS_OK = 200;

	@InjectMocks
	private CepRestController cepRestController;
	
	private MockMvc mockMvc;
	
	@MockBean
	private EnderecoUseCase enderecoUseCase;

    @BeforeAll
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	
        this.mockMvc = MockMvcBuilders.standaloneSetup(cepRestController).build();
    }
    
	@Test
	public void testConsultaCep() {

		Mockito.when(enderecoUseCase.consultarCep(
				anyString())).thenReturn(consultarCep());
	
		ObjectMapper mapper = new ObjectMapper();
	
		try {
			MvcResult mvcResult = mockMvc.perform(get(URL_CONSULTA_CEP, CEP)).andReturn();
	
			assertEquals(mvcResult.getResponse().getStatus(), HTTP_STATUS_OK);
			
			CepDto cepDto = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), CepDto.class);
			
			assertNotNull(cepDto);
	
			assertNotNull(cepDto.getCodigo());
			assertNotNull(cepDto.getLogradouro());
	
			assertEquals(cepDto.getCodigo(), CEP);
			assertEquals(cepDto.getLogradouro(), LOGRADOURO);

			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	
	private Cep consultarCep() {
		return new Cep(CEP, LOGRADOURO);
	}


	
}
