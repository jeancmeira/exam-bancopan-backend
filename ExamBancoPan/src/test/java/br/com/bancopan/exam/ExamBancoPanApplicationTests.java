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

import br.com.bancopan.exam.api.ClienteRestController;
import br.com.bancopan.exam.api.dto.ClienteDto;
import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.usecase.ClienteUseCase;

@SpringBootTest(classes = ExamBancoPanApplicationTests.class)
@TestInstance(Lifecycle.PER_CLASS)
class ExamBancoPanApplicationTests {

	@InjectMocks
	private ClienteRestController clienteRestController;
	
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteUseCase clienteUseCase;


    @BeforeAll
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteRestController).build();
    }
	
	
	@Test
	public void testConsultaCliente() {
		
		Mockito.when(clienteUseCase.consultarCliente(
				anyString())).thenReturn(consultarCliente());

		ObjectMapper mapper = new ObjectMapper();

		try {
			MvcResult mvcResult = mockMvc.perform(get("/cliente/{cpf}", "21250868831")).andReturn();

			assertEquals(mvcResult.getResponse().getStatus(), 200);
			
			ClienteDto parsedResponse = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), ClienteDto.class);
			
			assertNotNull(parsedResponse);
			assertNotNull(parsedResponse.getCpf());
			assertNotNull(parsedResponse.getNome());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	private Cliente consultarCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf("212.508.688-31");
		cliente.setNome("CLIENTE TESTE");
		
		Endereco endereco = new Endereco();
		endereco.setCep("04349000");
		cliente.setEndereco(endereco);
		
		return cliente;
	}
	
}
