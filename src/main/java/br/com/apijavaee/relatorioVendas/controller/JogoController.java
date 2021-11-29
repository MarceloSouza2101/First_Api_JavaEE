package br.com.apijavaee.relatorioVendas.controller;

import java.net.URI;
import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.apijavaee.relatorioVendas.dto.AtualizarJogoDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesJogoDTO;
import br.com.apijavaee.relatorioVendas.dto.ErroFormularioDTO;
import br.com.apijavaee.relatorioVendas.dto.JogoDTO;
import br.com.apijavaee.relatorioVendas.service.JogoService;

@Path("/jogo")
public class JogoController {

	private static final Logger logger = LoggerFactory.getLogger(JogoController.class);

	@Inject
	JogoService jogoService;

	@Inject
	ErroFormularioDTO dto;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllJogos() {
		try {
			List<JogoDTO> dtos = jogoService.getAll();
			logger.info("Sucesso ao trazer todos os jogos do banco");
			return Response.ok().entity(dtos).build();
		} catch (RuntimeException e) {
			logger.error("Erro ao tentar trazer todos os jogos do banco");
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{lote}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listByLote(@PathParam("lote") String lote) {
		try {
			DetalhesJogoDTO dto = jogoService.getByLote(lote);
			logger.info("Sucesso ao trazer jogo: " + lote);
			return Response.ok().entity(dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao buscar jogo: " + lote);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createdJogo(DetalhesJogoDTO detalhesJogoDTO, @Context UriInfo uriInfo) {
		try {
			jogoService.salvar(detalhesJogoDTO);
			URI uri = uriInfo.getAbsolutePathBuilder().path(detalhesJogoDTO.getLote()).build();
			logger.info("Jogo criado com sucesso!");
			return Response.created(uri).build();
		} catch (EJBException e) {
			logger.error("Impossivel criar novo jogo");
			this.dto.setMensagem("Erro ao tentar cadastrar jogo, verifique os dados");
			return Response.status(Status.BAD_REQUEST).entity(this.dto).build();
		}

	}

	@PUT
	@Path("/{lote}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateJogo(@PathParam("lote") String lote, AtualizarJogoDTO atualizarJogo,
			@Context UriInfo uriInfo) {
		try {
			jogoService.alterarJogo(lote, atualizarJogo);
			URI uri = uriInfo.getAbsolutePathBuilder().build();
			logger.info("Jogo " + lote + " alterado com sucesso!");
			this.dto.setMensagem("Os dados foram alterados com sucesso!");
			return Response.ok().location(uri).entity(this.dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao tentar alterar os dados do jogo: " + lote);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{lote}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteJogo(@PathParam("lote") String lote) {
		try {
			jogoService.deletar(lote);
			logger.info(lote + " deletado!");
			this.dto.setMensagem("Jogo deletado com suceeso!");
			return Response.ok().entity(this.dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao tentar deletar o jogo: " + lote);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
