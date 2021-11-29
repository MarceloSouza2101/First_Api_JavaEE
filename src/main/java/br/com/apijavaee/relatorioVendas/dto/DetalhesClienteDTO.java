package br.com.apijavaee.relatorioVendas.dto;

import java.util.List;

public class DetalhesClienteDTO {

	private String cpf;
	private String nome;
	private int telefone;
	private List<JogoDTO> jogos;

	public List<JogoDTO> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoDTO> jogos) {
		this.jogos = jogos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
}
