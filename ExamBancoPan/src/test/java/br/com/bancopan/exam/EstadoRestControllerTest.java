package br.com.bancopan.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

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

import br.com.bancopan.exam.api.EstadoRestController;
import br.com.bancopan.exam.api.dto.EstadoDto;
import br.com.bancopan.exam.api.dto.MunicipioDto;
import br.com.bancopan.exam.domain.Estado;
import br.com.bancopan.exam.domain.Municipio;
import br.com.bancopan.exam.main.ExamBancoPanApplication;
import br.com.bancopan.exam.usecase.EnderecoUseCase;

/**
 * @author Jean
 * Classe de teste do rest controller de estado
 *
 */
@SpringBootTest(classes = ExamBancoPanApplication.class)
@TestInstance(Lifecycle.PER_CLASS)
public class EstadoRestControllerTest {
	
	private static final String URL_CONSULTA_ESTADO = "/estado";
	
	private static final String URL_CONSULTA_MUNCIPIO = "/estado/{sigla}/municipio";

	private static final String NOME = "ESTADO TESTE";
	
	private static final String NOME_MUNICIPIO = "MUNICIPIO TESTE";
	
	private static final String SIGLA = "ES";

	private static final int HTTP_STATUS_OK = 200;

	@InjectMocks
	private EstadoRestController estadoRestController;
	
	private MockMvc mockMvc;
	
	@MockBean
	private EnderecoUseCase enderecoUseCase;

    @BeforeAll
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	
        this.mockMvc = MockMvcBuilders.standaloneSetup(estadoRestController).build();
    }
    
	@Test
	public void testListarEstados() {

		Mockito.when(enderecoUseCase.listarEstados()).thenReturn(listarEstados());
	
		ObjectMapper mapper = new ObjectMapper();
	
		try {
			MvcResult mvcResult = mockMvc.perform(get(URL_CONSULTA_ESTADO)).andReturn();
	
			assertEquals(mvcResult.getResponse().getStatus(), HTTP_STATUS_OK);
			
			EstadoDto[] estadosDto = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), EstadoDto[].class);
			
			assertNotNull(estadosDto);
			
			assertEquals(estadosDto.length, 1);
			
			EstadoDto estadoDto = estadosDto[0];
			
			assertNotNull(estadoDto);
	
			assertNotNull(estadoDto.getSigla());
			assertNotNull(estadoDto.getNome());
	
			assertEquals(estadoDto.getSigla(), SIGLA);
			assertEquals(estadoDto.getNome(), NOME);

			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Test
	public void testConsultarMuncipios() {

		Mockito.when(enderecoUseCase.consultarMunicipios(
				anyString())).thenReturn(consultarMunicipios());

	
		ObjectMapper mapper = new ObjectMapper();
	
		try {
			MvcResult mvcResult = mockMvc.perform(get(URL_CONSULTA_MUNCIPIO, SIGLA)).andReturn();
	
			assertEquals(mvcResult.getResponse().getStatus(), HTTP_STATUS_OK);
			
			MunicipioDto[] municipiosDto = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), MunicipioDto[].class);
			
			assertNotNull(municipiosDto);
			
			assertEquals(municipiosDto.length, 1);
			
			MunicipioDto municipioDto = municipiosDto[0];
			
			assertNotNull(municipioDto);
	
			assertNotNull(municipioDto.getEstado());
			assertNotNull(municipioDto.getNome());
	
			assertEquals(municipioDto.getEstado(), SIGLA);
			assertEquals(municipioDto.getNome(), NOME_MUNICIPIO);

			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	
	private List<Estado> listarEstados() {
		List<Estado> lista = new ArrayList<>();
		lista.add(new Estado(SIGLA, NOME));
		return lista;
	}

	private List<Municipio> consultarMunicipios() {
		List<Municipio> lista = new ArrayList<>();
		lista.add(new Municipio(SIGLA, NOME_MUNICIPIO));
		return lista;
	}

	
}
