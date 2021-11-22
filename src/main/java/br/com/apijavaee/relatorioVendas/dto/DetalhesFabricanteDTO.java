package br.com.apijavaee.relatorioVendas.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class DetalhesFabricanteDTO {

	@NotBlank
	private String nome;
	@NotBlank
	private String cnpj;
	@NotNull
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
