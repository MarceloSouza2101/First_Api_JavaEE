package br.com.apijavaee.relatorioVendas.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.apijavaee.relatorioVendas.dto.JogoDTO;
import br.com.apijavaee.relatorioVendas.service.JogoService;

@Path("/jogo")
public class JogoController {

	@Inject
	JogoService jogoService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<JogoDTO> listAllJogos() {
		return jogoService.getAll();
	}

	@GET
	@Path("/{lote}")
	@Produces(MediaType.APPLICATION_JSON)
	public JogoDTO listByLote(@PathParam("lote") String lote) {
		return jogoService.getByLote(lote);
	}

}
