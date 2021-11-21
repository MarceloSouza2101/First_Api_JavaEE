package br.com.apijavaee.relatorioVendas.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.ClienteDAO;
import br.com.apijavaee.relatorioVendas.dto.ClienteDTO;
import br.com.apijavaee.relatorioVendas.model.ClienteEntity;

public class ClienteService {

	@Inject
	ClienteDAO clienteDAO;

	private ModelMapper modelMapper = new ModelMapper();

	public List<ClienteDTO> getAll() {
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		List<ClienteEntity> clienteEntities = clienteDAO.findByAll();
		clienteEntities.stream()
				.forEach(cliente -> clientesDTO.add(modelMapper.map(cliente, ClienteDTO.class)));

		return clientesDTO;
	}

	public ClienteDTO getByCpf(String cpf) {
		return modelMapper.map(clienteDAO.findByCpf(cpf), ClienteDTO.class);
	}

}
