package br.com.apijavaee.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.JogosDAO;
import br.com.apijavaee.relatorioVendas.dto.AtualizarJogoDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesJogoDTO;
import br.com.apijavaee.relatorioVendas.dto.JogoDTO;
import br.com.apijavaee.relatorioVendas.model.JogoEntity;

public class JogoService {

	private JogosDAO jogoDAO;

	@Inject
	public JogoService(JogosDAO jogosDAO) {
		this.jogoDAO = jogosDAO;
	}

	private ModelMapper modelMapper = new ModelMapper();

	public List<JogoDTO> getAll() {
		List<JogoDTO> jogoDTOs = new ArrayList<>();
		List<JogoEntity> jogoEntities = this.jogoDAO.findByAll();
		jogoEntities.stream().forEach(jogo -> jogoDTOs.add(this.modelMapper.map(jogo, JogoDTO.class)));

		return jogoDTOs;
	}

	public DetalhesJogoDTO getByLote(String lote) {
		return this.modelMapper.map(this.jogoDAO.findByLote(lote), DetalhesJogoDTO.class);
	}

	public void salvar(DetalhesJogoDTO detalhesJogoDTO) {
		JogoEntity entity = this.modelMapper.map(detalhesJogoDTO, JogoEntity.class);
		this.jogoDAO.salvarJogo(entity);
	}

	public void alterarJogo(String lote, AtualizarJogoDTO atualizarJogo) {
		JogoEntity jogoEntity = this.jogoDAO.findByLote(lote);
		jogoEntity.setModalidade(atualizarJogo.getModalidade());
		jogoEntity.setDescricao(atualizarJogo.getDescricao());
		this.jogoDAO.alterar(jogoEntity);
	}

	public void deletar(String lote) {
		this.jogoDAO.deleteByCpf(this.jogoDAO.findByLote(lote));
	}

}
