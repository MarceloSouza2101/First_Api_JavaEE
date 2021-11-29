package br.com.apijavaee.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.ClienteDAO;
import br.com.apijavaee.relatorioVendas.dao.JogosDAO;
import br.com.apijavaee.relatorioVendas.dto.AtualizarClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.ClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesClienteDTO;
import br.com.apijavaee.relatorioVendas.model.ClienteEntity;
import br.com.apijavaee.relatorioVendas.model.JogoEntity;

public class ClienteService {

	private ClienteDAO clienteDAO;

	private JogosDAO jogosDAO;

	@Inject
	public ClienteService(ClienteDAO clienteDAO, JogosDAO jogosDAO) {
		this.clienteDAO = clienteDAO;
		this.jogosDAO = jogosDAO;
	}

	private ModelMapper modelMapper = new ModelMapper();

	public List<ClienteDTO> getAll() {
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		List<ClienteEntity> clienteEntities = clienteDAO.findByAll();
		clienteEntities.stream().forEach(cliente -> clientesDTO.add(modelMapper.map(cliente, ClienteDTO.class)));

		return clientesDTO;
	}

	public DetalhesClienteDTO getByCpf(String cpf) {
		return modelMapper.map(clienteDAO.findByCpf(cpf), DetalhesClienteDTO.class);
	}

	public void salvar(DetalhesClienteDTO detalhesClienteDTO) {
		List<JogoEntity> jogos = new ArrayList<>();
		detalhesClienteDTO.getJogos().stream().forEach(jogo -> jogos.add(jogosDAO.findByLote(jogo.getLote())));
		ClienteEntity clienteEntity = modelMapper.map(detalhesClienteDTO, ClienteEntity.class);
		clienteEntity.setJogos(jogos);
		clienteDAO.salvarCliente(clienteEntity);
	}

	public void alterarCliente(String cpf, AtualizarClienteDTO atualizarCLienteDTO) {
		ClienteEntity clienteEntity = clienteDAO.findByCpf(cpf);
		clienteEntity.setTelefone(atualizarCLienteDTO.getTelefone());
		clienteDAO.alterar(clienteEntity);
	}

	public void deleteByCpf(String cpf) {
		clienteDAO.deletar(clienteDAO.findByCpf(cpf));
	}

}
