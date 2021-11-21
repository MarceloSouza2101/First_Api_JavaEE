package br.com.apijavaee.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.FabricanteDAO;
import br.com.apijavaee.relatorioVendas.dto.FabricanteDTO;
import br.com.apijavaee.relatorioVendas.model.FabricanteEntity;

public class FabricanteService {

	@Inject
	FabricanteDAO fabricanteDAO;

	ModelMapper modelMapper = new ModelMapper();

	public List<FabricanteDTO> getAll() {
		List<FabricanteDTO> fabricantesDTO = new ArrayList<>();
		List<FabricanteEntity> fabricanteEntities = fabricanteDAO.findAll();
		fabricanteEntities.stream()
				.forEach(fabricante -> fabricantesDTO.add(modelMapper.map(fabricante, FabricanteDTO.class)));
		
		return fabricantesDTO;
	}

	public FabricanteDTO getByCpnj(String cnpj) {
		return modelMapper.map(fabricanteDAO.findByCnpj(cnpj), FabricanteDTO.class);
	}

}
