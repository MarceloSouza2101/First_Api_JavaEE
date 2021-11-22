package br.com.apijavaee.relatorioVendas.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.apijavaee.relatorioVendas.dto.AtualizarClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.ClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesClienteDTO;
import br.com.apijavaee.relatorioVendas.service.ClienteService;

@Path("/cliente")
public class ClienteController {
	
	@Inject
	ClienteService clienteService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClienteDTO> listAllClientes(){
		return clienteService.getAll();
	}
	
	@Path("/{cpf}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public DetalhesClienteDTO listByCpf(@PathParam("cpf") String cpf){
		return clienteService.getByCpf(cpf);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createdCliente(DetalhesClienteDTO detalhesClienteDTO) {
		clienteService.salvar(detalhesClienteDTO);
		return Response.status(201).entity("created").build();
	}

	@PUT
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCliente(@PathParam("cpf") String cpf, AtualizarClienteDTO atualizarCLienteDTO) {
		clienteService.alterarCliente(cpf, atualizarCLienteDTO);
	}
}
