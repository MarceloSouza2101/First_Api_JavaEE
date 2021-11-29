package br.com.apijavaee.relatorioVendas.controller;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;

class FabricanteControllerTest {

	@Test
	void getAll() {
		given().when().get("http://localhost:8080/relatorioVendas/v1/fabricante").then().statusCode(200).log().all();
	}

	@Test
	void getByCnpj() {
		given().when().get("http://localhost:8080/relatorioVendas/v1/fabricante/38.526.377.000151").then()
				.statusCode(200).log().all();
	}

	@Test
	void cadastrarJogoCamposObrigatorio() {
		given().contentType(MediaType.APPLICATION_JSON)
				.body("{ \"cnpj\":\"38.23443.34\",\"jogos\": [{\"lote\":\"0022\"}]}").when()
				.post("http://localhost:8080/relatorioVendas/v1/fabricante").then().log().all()
				.body("mensagem", containsString("Erro ao tentar cadastrar novo fabricante, verifique os dados!"))
				.statusCode(400);
	}

	 @Test
	 void cadastrarFabricante() {
	 given().contentType(MediaType.APPLICATION_JSON)
	 .body("{ \"cnpj\":\"1234567\",\"nome\":\"efsge\",\"jogos\":[{\"lote\":\"0001\"}]}").when()
	 .post("http://localhost:8080/relatorioVendas/v1/fabricante").then().statusCode(201).log().all();
	 }

	@Test
	void alterarFabricante() {
		given().contentType(MediaType.APPLICATION_JSON).body("{\"nome\":\"irineu\"}").when()
				.put("http://localhost:8080/relatorioVendas/v1/fabricante/38.526.377.000151").then().log().all()
				.body("mensagem", containsString("Os dados foram alterados com sucesso!")).statusCode(200);
	}
	
	@Test
	void deletarFabricante() {
		given().when().delete("http://localhost:8080/relatorioVendas/v1/fabricante/1234567").then().log().all()
		.body("mensagem", containsString("Fabricante deletado com suceeso!")).statusCode(200);
	}
}
