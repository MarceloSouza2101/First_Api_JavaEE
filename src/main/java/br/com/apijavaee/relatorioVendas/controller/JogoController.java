package br.com.apijavaee.relatorioVendas.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.apijavaee.relatorioVendas.dto.DetalhesJogoDTO;
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
	public DetalhesJogoDTO listByLote(@PathParam("lote") String lote) {
		return jogoService.getByLote(lote);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createdJogo(DetalhesJogoDTO detalhesJogoDTO) {
		jogoService.salvar(detalhesJogoDTO);
		return Response.status(201).entity("created").build();
	}
}
