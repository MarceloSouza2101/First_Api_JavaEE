package br.com.apijavaee.relatorioVendas.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class DetalhesClienteDTO {

	@NotBlank
	private String cpf;
	@NotBlank
	private String nome;
	@NotNull
	private int telefone;
	@NotEmpty
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
