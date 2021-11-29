package br.com.apijavaee.relatorioVendas.controller;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class JogoControllerTest {

	@Test
	void getAll() {
		given().when().get("http://localhost:8080/relatorioVendas/v1/jogo").then().statusCode(200).log().all();
	}

	@Test
	void getByLote() {
		given().when().get("http://localhost:8080/relatorioVendas/v1/jogo/0001").then().statusCode(200).log().all();
	}

	@Test
	void getByLoteInvalido() {
		given().when().get("http://localhost:8080/relatorioVendas/v1/jogo/0000").then().statusCode(404).log().all();
	}

	@Test
	void cadastrarJogoCamposObrigatorio() {
		given().contentType(MediaType.APPLICATION_JSON).body(
				"{\"descricao\": \"Jogo de futebol, diversos modos de jogo tanto online quanto offline.\", \"lancamento\": \"2021-09-26\", \"lote\": \"0021\", \"modalidade\": \"FUTEBOL\"}")
				.when().post("http://localhost:8080/relatorioVendas/v1/jogo").then().log().all()
				.body("mensagem", containsString("Erro ao tentar cadastrar jogo, verifique os dados")).statusCode(400);

	}

	@Test
	void cadastrarJogo() {
		given().contentType(MediaType.APPLICATION_JSON).body(
				"{\"descricao\": \"Jogo de futebol, diversos modos de jogo tanto online quanto offline.\", \"lancamento\": \"2021-09-26\", \"lote\": \"1001\", \"modalidade\": \"FUTEBOL\",\"nome\": \"Fifa21\"}")
				.when().post("http://localhost:8080/relatorioVendas/v1/jogo").then().statusCode(201).log().all();
	}

	@Test
	void alterarJogo() {
		given().contentType(MediaType.APPLICATION_JSON).body("{\n" + "    \"modalidade\":\"Ação\",\n"
				+ "    \"descricao\":\"Em God of War, os jogadores controlam Kratos, um guerreiro espartano enviado pelos deuses gregos para matar Ares, o deus da guerra.\"\n"
				+ "}").when().put("http://localhost:8080/relatorioVendas/v1/jogo/1001").then().log().all()
				.body("mensagem", containsString("Os dados foram alterados com sucesso!")).statusCode(200);
	}

	@Test
	void deletarJogo() {
		given().when().delete("http://localhost:8080/relatorioVendas/v1/jogo/1001").then().log().all()
				.body("mensagem", containsString("Jogo deletado com suceeso!")).statusCode(200);
	}

}
