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

/**
 * Classe de teste do service de clientes
 * @author Jean
 *
 */
@SpringBootTest(classes = ExamBancoPanApplication.class)
public class ClienteServiceTest {

	private static final String CAMPO_ESTADO = "estado";

	private static final String CAMPO_MUNICIPIO = "municipio";

	private static final String CAMPO_BAIRRO = "bairro";

	private static final String CAMPO_NUMERO = "numero";

	private static final String CAMPO_LOGRADOURO = "logradouro";

	private static final String CAMPO_CEP = "cep";

	private static final String CAMPO_CPF = "cpf";

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
			assertEquals(CAMPO_CPF, e.getCampo());
		}
		
		assertTrue(hasError);
		
	}

	@Test
	public void testAlteracaoEnderecoComCamposInvalidos() {
		Cliente cliente = getCliente();
		cliente.setCpf(null);
		testarCampoObrigatorio(cliente, CAMPO_CPF);

		cliente = getCliente();
		cliente.getEndereco().setCep(null);
		testarCampoObrigatorio(cliente, CAMPO_CEP);

		cliente = getCliente();
		cliente.getEndereco().setLogradouro(null);
		testarCampoObrigatorio(cliente, CAMPO_LOGRADOURO);
		
		cliente = getCliente();
		cliente.getEndereco().setNumero(null);
		testarCampoObrigatorio(cliente, CAMPO_NUMERO);

		cliente = getCliente();
		cliente.getEndereco().setBairro(null);
		testarCampoObrigatorio(cliente, CAMPO_BAIRRO);

		cliente = getCliente();
		cliente.getEndereco().setMunicipio(null);
		testarCampoObrigatorio(cliente, CAMPO_MUNICIPIO);

		cliente = getCliente();
		cliente.getEndereco().setEstado(null);
		testarCampoObrigatorio(cliente, CAMPO_ESTADO);
		
		/////
		
		
		cliente = getCliente();
		cliente.getEndereco().setCep(criaString(11));
		testarTamanhoCampo(cliente, CAMPO_CEP);

		cliente = getCliente();
		cliente.getEndereco().setLogradouro(criaString(1000));
		testarTamanhoCampo(cliente, CAMPO_LOGRADOURO);

		cliente = getCliente();
		cliente.getEndereco().setComplemento(criaString(1000));
		testarTamanhoCampo(cliente, "complemento");
		
		cliente = getCliente();
		cliente.getEndereco().setBairro(criaString(500));
		testarTamanhoCampo(cliente, CAMPO_BAIRRO);

		cliente = getCliente();
		cliente.getEndereco().setMunicipio(criaString(500));
		testarTamanhoCampo(cliente, CAMPO_MUNICIPIO);

		cliente = getCliente();
		cliente.getEndereco().setEstado(criaString(2));
		testarTamanhoCampo(cliente, CAMPO_ESTADO);
	
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
