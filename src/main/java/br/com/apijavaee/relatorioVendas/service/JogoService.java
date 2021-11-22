package br.com.apijavaee.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.JogosDAO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesJogoDTO;
import br.com.apijavaee.relatorioVendas.dto.JogoDTO;
import br.com.apijavaee.relatorioVendas.model.JogoEntity;

public class JogoService {

	@Inject
	JogosDAO jogoDAO;

	private ModelMapper modelMapper = new ModelMapper();

	public List<JogoDTO> getAll() {
		List<JogoDTO> jogoDTOs = new ArrayList<>();
		List<JogoEntity> jogoEntities = jogoDAO.findByAll();
		jogoEntities.stream()
				.forEach(jogo -> jogoDTOs.add(modelMapper.map(jogo, JogoDTO.class)));

		return jogoDTOs;
	}

	public DetalhesJogoDTO getByLote(String lote) {
		return modelMapper.map(jogoDAO.findByLote(lote), DetalhesJogoDTO.class);
	}

	public void salvar(DetalhesJogoDTO detalhesJogoDTO) {
		JogoEntity entity = modelMapper.map(detalhesJogoDTO, JogoEntity.class);
		jogoDAO.salvarJogo(entity);
	}

}
