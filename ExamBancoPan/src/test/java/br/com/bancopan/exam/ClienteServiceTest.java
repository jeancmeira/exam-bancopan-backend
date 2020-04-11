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
import br.com.bancopan.exam.domain.Endereco;
import br.com.bancopan.exam.main.ExamBancoPanApplication;
import br.com.bancopan.exam.port.ClientePort;
import br.com.bancopan.exam.service.ClienteService;
import br.com.bancopan.exam.validation.CampoObrigatorioException;
import br.com.bancopan.exam.validation.TamanhoExcedidoException;

@SpringBootTest(classes = ExamBancoPanApplication.class)
public class ClienteServiceTest {

	private static final String BAIRRO = "BAIRRO TESTE";

	private static final int NUMERO = 111;

	private static final String LOGRADOURO = "RUA TESTE";

	private static final String MUNICPIO = "MUNCIPIO TESTE";

	private static final String CEP = "04349000";

	private static final String CPF = "123.456.789-10";

	
	@InjectMocks
	private ClienteService clienteService;

	@MockBean
	private ClientePort clientePort;

	@Test
	public void testConsultaClienteComCpfVazio() {
		
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

	@Test
	public void testAlteracaoEnderecoComCamposInvalidos() {
		Cliente cliente = getCliente();
		cliente.setCpf(null);
		testarCampoObrigatorio(cliente, "cpf");

		cliente = getCliente();
		cliente.getEndereco().setCep(null);
		testarCampoObrigatorio(cliente, "cep");

		cliente = getCliente();
		cliente.getEndereco().setLogradouro(null);
		testarCampoObrigatorio(cliente, "logradouro");
		
		cliente = getCliente();
		cliente.getEndereco().setNumero(null);
		testarCampoObrigatorio(cliente, "numero");

		cliente = getCliente();
		cliente.getEndereco().setBairro(null);
		testarCampoObrigatorio(cliente, "bairro");

		cliente = getCliente();
		cliente.getEndereco().setMunicipio(null);
		testarCampoObrigatorio(cliente, "municipio");

		cliente = getCliente();
		cliente.getEndereco().setEstado(null);
		testarCampoObrigatorio(cliente, "estado");
		
		/////
		
		
		cliente = getCliente();
		cliente.getEndereco().setCep(criaString(11));
		testarTamanhoCampo(cliente, "cep");

		cliente = getCliente();
		cliente.getEndereco().setLogradouro(criaString(1000));
		testarTamanhoCampo(cliente, "logradouro");

		cliente = getCliente();
		cliente.getEndereco().setComplemento(criaString(1000));
		testarTamanhoCampo(cliente, "complemento");
		
		cliente = getCliente();
		cliente.getEndereco().setBairro(criaString(500));
		testarTamanhoCampo(cliente, "bairro");

		cliente = getCliente();
		cliente.getEndereco().setMunicipio(criaString(500));
		testarTamanhoCampo(cliente, "municipio");

		cliente = getCliente();
		cliente.getEndereco().setEstado(criaString(2));
		testarTamanhoCampo(cliente, "estado");
	
	}

	private Cliente getCliente() {
		Cliente cliente  = new Cliente();
		cliente.setCpf(CPF);
		
		Endereco endereco = new Endereco();
		endereco.setCep(CEP);
		endereco.setLogradouro(LOGRADOURO);
		endereco.setNumero(NUMERO);
		endereco.setBairro(BAIRRO);
		endereco.setMunicipio(MUNICPIO);
		
		cliente.setEndereco(endereco);
		return cliente;
	}

	private Cliente retornaNull() {
		return null;
	}
	
	private void testarCampoObrigatorio(Cliente cliente, String campo) {
		boolean hasError = false;
		
		try {
			clienteService.alterarEndereco(cliente);
		} catch (CampoObrigatorioException e) {
			hasError = true;
			assertEquals(campo, e.getCampo());
		}
		
		assertTrue(hasError);
	}
	
	private void testarTamanhoCampo(Cliente cliente, String campo) {
		boolean hasError = false;
		
		try {
			clienteService.alterarEndereco(cliente);
		} catch (TamanhoExcedidoException e) {
			hasError = true;
			assertEquals(campo, e.getCampo());
		}
		
		assertTrue(hasError);
	}
	
	private String criaString(int tamanho) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 1; i <= tamanho + 1; i++) {
			stringBuilder.append("A");
		}

		return stringBuilder.toString();
	}

	
}
