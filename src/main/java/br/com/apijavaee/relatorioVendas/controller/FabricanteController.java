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

import br.com.apijavaee.relatorioVendas.dto.AtualizarFabricanteDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesFabricanteDTO;
import br.com.apijavaee.relatorioVendas.dto.ErroFormularioDTO;
import br.com.apijavaee.relatorioVendas.dto.FabricanteDTO;
import br.com.apijavaee.relatorioVendas.service.FabricanteService;

@Path("/fabricante")
public class FabricanteController {

	private static final Logger logger = LoggerFactory.getLogger(FabricanteController.class);

	@Inject
	FabricanteService fabricanteService;

	@Inject
	ErroFormularioDTO dto;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllFabricante() {
		try {
			List<FabricanteDTO> dtos = fabricanteService.getAll();
			logger.info("Sucesso ao trazer todos os fabricantes do banco");
			return Response.ok().entity(dtos).build();
		} catch (RuntimeException e) {
			logger.error("Erro ao tentar trazer todos os fabricantes do banco");
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listByLote(@PathParam("cnpj") String cnpj) {
		try {
			DetalhesFabricanteDTO dto = fabricanteService.getByCnpj(cnpj);
			logger.info("Sucesso ao trazer cnpj: " + cnpj);
			return Response.ok().entity(dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao buscar cnpj: " + cnpj);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createdFabricante(DetalhesFabricanteDTO detalhesFabricanteDTO, @Context UriInfo uriInfo) {
		try {
			fabricanteService.salvar(detalhesFabricanteDTO);
			URI uri = uriInfo.getAbsolutePathBuilder().path(detalhesFabricanteDTO.getCnpj()).build();
			logger.info("Fabricante criado com sucesso!");
			return Response.created(uri).build();
		} catch (RuntimeException e) {
			logger.error("Impossivel criar novo fabricante");
			this.dto.setMensagem("Erro ao tentar cadastrar novo fabricante, verifique os dados!");
			return Response.status(Status.BAD_REQUEST).entity(this.dto).build();
		}
	}

	@PUT
	@Path("/{cnpj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFabricante(@PathParam("cnpj") String cnpj, AtualizarFabricanteDTO atualizarFabricanteDTO,
			@Context UriInfo uriInfo) {
		try {
			fabricanteService.alterarFabricante(cnpj, atualizarFabricanteDTO);
			URI uri = uriInfo.getAbsolutePathBuilder().build();
			logger.info("Portador do cnpj: " + cnpj + " alterado com sucesso!");
			this.dto.setMensagem("Os dados foram alterados com sucesso!");
			return Response.ok().location(uri).entity(this.dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao tentar alterar o cnpj: " + cnpj);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFabricante(@PathParam("cnpj") String cnpj) {
		try {
			fabricanteService.deleteByCnpj(cnpj);
			logger.info(cnpj + " deletado");
			this.dto.setMensagem("Fabricante deletado com suceeso!");
			return Response.ok().entity(this.dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao tentar deletar o cnpj: " + cnpj);
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
