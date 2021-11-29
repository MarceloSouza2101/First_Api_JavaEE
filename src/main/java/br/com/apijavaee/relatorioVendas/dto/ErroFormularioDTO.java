package br.com.apijavaee.relatorioVendas.dto;

import javax.validation.ConstraintViolationException;

public class ErroFormularioDTO {

	String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public String getMensagem(RuntimeException exception) {
		if (exception.getCause() instanceof ConstraintViolationException) {
			this.mensagem = exception.getMessage();
		} else {
			this.mensagem = "Erro ao tentar cadastrar este lote";
		}
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
