package br.com.apijavaee.relatorioVendas.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public FabricanteDTO listByLote(@PathParam("cnpj") String cnpj) {
		return fabricanteService.getByCpnj(cnpj);
	}
	
}
