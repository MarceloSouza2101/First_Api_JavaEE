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

import br.com.apijavaee.relatorioVendas.dto.AtualizarFabricanteDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesFabricanteDTO;
import br.com.apijavaee.relatorioVendas.dto.FabricanteDTO;
import br.com.apijavaee.relatorioVendas.service.FabricanteService;

@Path("/fabricante")
public class FabricanteController {

	@Inject
	FabricanteService fabricanteService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FabricanteDTO> listAllFabricante(){
		return fabricanteService.getAll();
	}
	
	@GET
	@Path("/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public DetalhesFabricanteDTO listByLote(@PathParam("cnpj") String cnpj) {
		return fabricanteService.getByCnpj(cnpj);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createdFabricante(DetalhesFabricanteDTO detalhesFabricanteDTO) {
		fabricanteService.salvar(detalhesFabricanteDTO);
		return Response.status(201).entity("created").build();
	}
	
	@PUT
	@Path("/{cnpj}")
    @Consumes("application/json")
    public void updateFabricante(@PathParam("cnpj") String cnpj, AtualizarFabricanteDTO atualizarFabricanteDTO) {
        fabricanteService.alterarFabricante(cnpj, atualizarFabricanteDTO);
    }
	
}
