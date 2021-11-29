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

import br.com.apijavaee.relatorioVendas.dto.AtualizarClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.ClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.ErroFormularioDTO;
import br.com.apijavaee.relatorioVendas.service.ClienteService;

@Path("/cliente")
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Inject
	ClienteService clienteService;

	@Inject
	ErroFormularioDTO dto;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllClientes() {
		try {
			List<ClienteDTO> dtos = clienteService.getAll();
			logger.info("Sucesso ao trazer todos os clientes do banco");
			return Response.ok().entity(dtos).build();
		} catch (RuntimeException e) {
			logger.error("Erro ao tentar trazer todos os clientes do banco");
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@Path("/{cpf}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listByCpf(@PathParam("cpf") String cpf) {
		try {
			DetalhesClienteDTO dto = clienteService.getByCpf(cpf);
			logger.info("Sucesso ao trazer cpf: " + cpf);
			return Response.ok().entity(dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao buscar cpf: " + cpf);
			return Response.status(Status.NOT_FOUND).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createdCliente(DetalhesClienteDTO detalhesClienteDTO, @Context UriInfo uriInfo) {
		try {
			clienteService.salvar(detalhesClienteDTO);
			URI uri = uriInfo.getAbsolutePathBuilder().path(detalhesClienteDTO.getCpf()).build();
			logger.info("Cliente criado com sucesso!");
			return Response.created(uri).build();
		} catch (RuntimeException e) {
			logger.error("Impossivel criar novo usuario");
			this.dto.setMensagem("Erro ao tentar cadastrar novo cliente, verifique os dados!");
			return Response.status(Status.BAD_REQUEST).entity(this.dto).build();
		}
	}

	@PUT
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCliente(@PathParam("cpf") String cpf, AtualizarClienteDTO atualizarCLienteDTO,
			@Context UriInfo uriInfo) {
		try {
			clienteService.alterarCliente(cpf, atualizarCLienteDTO);
			URI uri = uriInfo.getAbsolutePathBuilder().build();
			logger.info("Portador do cpf: " + cpf + " alterado com sucesso!");
			this.dto.setMensagem("Os dados foram alterados com sucesso!");
			return Response.ok().location(uri).entity(this.dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao tentar alterar o cpf: " + cpf);
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(@PathParam("cpf") String cpf) {
		try {
			clienteService.deleteByCpf(cpf);
			logger.info(cpf + " deletado");
			this.dto.setMensagem("Cliente deletado com suceeso!");
			return Response.ok().entity(this.dto).build();
		} catch (EJBException e) {
			logger.error("Nenhum resultado encontrado ao tentar deletar o cpf: " + cpf);
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
