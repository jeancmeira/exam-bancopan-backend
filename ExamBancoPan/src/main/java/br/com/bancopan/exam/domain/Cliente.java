package br.com.bancopan.exam.domain;

import br.com.bancopan.exam.repositorio.def.ClienteRepositorio;

public class Cliente {

	private ClienteRepositorio clienteRepositorio; 
	
	private String cpf;
	
	private String nome;
	
	private Endereco endereco;

	public void setClienteRepositorio(ClienteRepositorio clienteRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void alterarEndereco() {
		System.out.println(cpf);
		System.out.println(nome);
		System.out.println(endereco.getNumero());
		System.out.println(endereco.getCep().getMunicipio().getEstado().getNome());
		
		Cliente clienteBusca = clienteRepositorio.consultarCliente(cpf);
		System.out.println(clienteBusca.getNome());
		
	}
	
}


