package br.com.bancopan.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.bancopan.exam.domain.Cliente;
import br.com.bancopan.exam.main.ExamBancoPanApplication;
import br.com.bancopan.exam.port.ClientePort;
import br.com.bancopan.exam.service.ClienteService;
import br.com.bancopan.exam.validation.CampoObrigatorioException;

@SpringBootTest(classes = ExamBancoPanApplication.class)
public class ClienteServiceTest {

	@InjectMocks
	private ClienteService clienteService;

	@MockBean
	private ClientePort clientePort;

	@Test
	public void testConsultaCliente() {
		
		Mockito.when(clientePort.consultarCliente(
				anyString())).thenReturn(retornaNull());

		boolean hasError = false;
		
		try {
			clienteService.consultarCliente(null);
		} catch (CampoObrigatorioException e) {
			hasError = true;
			assertEquals("cpf", e.getCampo());
		}
		
		assertTrue(hasError);
		
	}

	private Cliente retornaNull() {
		return null;
	}
	

}
