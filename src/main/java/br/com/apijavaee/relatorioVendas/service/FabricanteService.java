package br.com.apijavaee.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.FabricanteDAO;
import br.com.apijavaee.relatorioVendas.dao.JogosDAO;
import br.com.apijavaee.relatorioVendas.dto.AtualizarFabricanteDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesFabricanteDTO;
import br.com.apijavaee.relatorioVendas.dto.FabricanteDTO;
import br.com.apijavaee.relatorioVendas.model.FabricanteEntity;
import br.com.apijavaee.relatorioVendas.model.JogoEntity;

public class FabricanteService {

	@Inject
	FabricanteDAO fabricanteDAO;

	@Inject
	JogosDAO jogosDAO;
	
	ModelMapper modelMapper = new ModelMapper();

	public List<FabricanteDTO> getAll() {
		List<FabricanteDTO> fabricantesDTO = new ArrayList<>();
		List<FabricanteEntity> fabricanteEntities = fabricanteDAO.findAll();
		fabricanteEntities.stream()
				.forEach(fabricante -> fabricantesDTO.add(modelMapper.map(fabricante, FabricanteDTO.class)));
		
		return fabricantesDTO;
	}

	public DetalhesFabricanteDTO getByCnpj(String cnpj) {
		FabricanteEntity clienteEntity = fabricanteDAO.findByCnpj(cnpj);
		return modelMapper.map(clienteEntity, DetalhesFabricanteDTO.class);
	}

	public void salvar(DetalhesFabricanteDTO detalhesFabricanteDTO) {
		List<JogoEntity> jogos = new ArrayList<>();
		detalhesFabricanteDTO.getJogos().stream().forEach(jogo -> jogos.add(jogosDAO.findByLote(jogo.getLote())));
		FabricanteEntity fabricanteEntity = modelMapper.map(detalhesFabricanteDTO, FabricanteEntity.class);
		fabricanteEntity.setJogos(jogos);
		fabricanteDAO.salvarFabricante(fabricanteEntity);
	}

	public void alterarFabricante(String cnpj, AtualizarFabricanteDTO atualizarFabricanteDTO) {
		FabricanteEntity fabricanteEntity = fabricanteDAO.findByCnpj(cnpj);
		fabricanteEntity.setNome(atualizarFabricanteDTO.getNome());
		fabricanteDAO.alterar(fabricanteEntity);
	}

	public void deleteByCnpj(String cnpj) {
		fabricanteDAO.delete(fabricanteDAO.findByCnpj(cnpj));
	}

}
