package br.com.apijavaee.relatorioVendas.dto;

import java.time.LocalDate;

public class DetalhesJogoDTO {

	private String lote;
	private String nome;
	private String modalidade;
	private LocalDate lancamento;
	private String descricao;

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
