package br.com.apijavaee.relatorioVendas.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

class ClienteControllerTest {

	@Test
	void getAll() {
		given().when().get("http://localhost:8080/relatorioVendas/v1/cliente").then().statusCode(200).log().all();
	}

	@Test
	void getByCpf() {
		given().when().get("http://localhost:8080/relatorioVendas/v1/cliente/885.260.770.66").then().statusCode(200)
				.log().all();
	}

	@Test
	void cadastrarJogoCamposObrigatorio() {
		given().contentType(MediaType.APPLICATION_JSON)
				.body("{\"cpf\": \"1234\",\"telefone\": \"962571977\",\"jogos\": [{\"lote\":\"0001\"}]}").when()
				.post("http://localhost:8080/relatorioVendas/v1/cliente").then().log().all()
				.body("mensagem", containsString("Erro ao tentar cadastrar novo cliente, verifique os dados!"))
				.statusCode(400);

	}

	@Test
	void cadastrarCliente() {
		given().contentType(MediaType.APPLICATION_JSON).body(
				"{\"cpf\": \"1234\",\"nome\": \"JOse\",\"telefone\": \"962571977\",\"jogos\": [{\"lote\":\"0001\"}]}")
				.when().post("http://localhost:8080/relatorioVendas/v1/cliente").then().statusCode(201).log().all();

	}

	@Test
	void alterarJogo() {
		given().contentType(MediaType.APPLICATION_JSON).body("{\"telefone\":96303}").when()
				.put("http://localhost:8080/relatorioVendas/v1/cliente/885.260.770.66").then().log().all()
				.body("mensagem", containsString("Os dados foram alterados com sucesso!")).statusCode(200);
	}

	@Test
	void deletarJogo() {
		given().when().delete("http://localhost:8080/relatorioVendas/v1/cliente/1234").then().log().all()
				.body("mensagem", containsString("Cliente deletado com suceeso!")).statusCode(200);
	}

}
