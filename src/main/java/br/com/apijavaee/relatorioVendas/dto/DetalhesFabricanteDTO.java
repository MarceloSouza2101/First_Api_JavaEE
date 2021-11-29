package br.com.apijavaee.relatorioVendas.dto;

import java.util.List;

public class DetalhesFabricanteDTO {

	private String nome;
	private String cnpj;
	private List<JogoDTO> jogos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<JogoDTO> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoDTO> jogos) {
		this.jogos = jogos;
	}

}
