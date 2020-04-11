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

@SpringBootTest(classes = ClienteTest.class)
@TestInstance(Lifecycle.PER_CLASS)
class ClienteTest {

	private static final String CONSULTA_CLIENTE_URL = "/cliente/{cpf}";

	private static final int HTTP_STATUS_OK = 200;

	private static final String BAIRRO = "BAIRRO TESTE";

	private static final String COMPLEMENTO = "COMPLEMENTO TESTE";

	private static final int NUMERO = 111;

	private static final String LOGRADOURO = "RUA TESTE";

	private static final String ESTADO = "AM";

	private static final String MUNICPIO = "MUNCIPIO TESTE";

	private static final String CEP = "04349000";

	private static final String NOME = "CLIENTE TESTE";

	private static final String CPF = "123.456.789-10";

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
			MvcResult mvcResult = mockMvc.perform(get(CONSULTA_CLIENTE_URL, CPF)).andReturn();

			assertEquals(mvcResult.getResponse().getStatus(), HTTP_STATUS_OK);
			
			ClienteDto clienteDto = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), ClienteDto.class);
			
			assertNotNull(clienteDto);

			assertNotNull(clienteDto.getCpf());
			assertNotNull(clienteDto.getNome());

			assertNotNull(clienteDto.getCep());
			assertNotNull(clienteDto.getMunicipio());
			assertNotNull(clienteDto.getEstado());
			assertNotNull(clienteDto.getLogradouro());
			assertNotNull(clienteDto.getNumero());
			assertNotNull(clienteDto.getComplemento());
			assertNotNull(clienteDto.getBairro());
			
			assertEquals(clienteDto.getCpf(), CPF);
			assertEquals(clienteDto.getNome(), NOME);

			assertEquals(clienteDto.getCep(), CEP);
			assertEquals(clienteDto.getMunicipio(), MUNICPIO);
			assertEquals(clienteDto.getEstado(), ESTADO);
			assertEquals(clienteDto.getLogradouro(), LOGRADOURO);
			assertEquals(clienteDto.getNumero(), NUMERO);
			assertEquals(clienteDto.getComplemento(), COMPLEMENTO);
			assertEquals(clienteDto.getBairro(), BAIRRO);

			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Test
	public void testAlterarEndereco() {
		
	}

	private Cliente consultarCliente() {
		Cliente cliente = new Cliente();
		cliente.setCpf(CPF);
		cliente.setNome(NOME);
		
		Endereco endereco = new Endereco();
		endereco.setCep(CEP);
		endereco.setMunicipio(MUNICPIO);
		endereco.setEstado(ESTADO);
		endereco.setLogradouro(LOGRADOURO);
		endereco.setNumero(NUMERO);
		endereco.setComplemento(COMPLEMENTO);
		endereco.setBairro(BAIRRO);
		
		cliente.setEndereco(endereco);
		
		return cliente;
	}
	
}