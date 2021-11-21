package br.com.apijavaee.relatorioVendas.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.apijavaee.relatorioVendas.dto.ClienteDTO;
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
	public ClienteDTO listByCpf(@PathParam("cpf") String cpf){
		return clienteService.getByCpf(cpf);
	}

}
