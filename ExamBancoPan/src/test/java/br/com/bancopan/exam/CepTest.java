package br.com.bancopan.exam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.bancopan.exam.api.CepRestController;
import br.com.bancopan.exam.usecase.EnderecoUseCase;

@SpringBootTest(classes = ClienteTest.class)
@TestInstance(Lifecycle.PER_CLASS)
public class CepTest {

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
		
	}


	
}
