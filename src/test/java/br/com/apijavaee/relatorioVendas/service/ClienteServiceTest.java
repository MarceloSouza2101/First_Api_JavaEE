package br.com.apijavaee.relatorioVendas.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.ClienteDAO;
import br.com.apijavaee.relatorioVendas.dao.JogosDAO;
import br.com.apijavaee.relatorioVendas.dto.AtualizarClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.ClienteDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesClienteDTO;
import br.com.apijavaee.relatorioVendas.model.ClienteEntity;
import br.com.apijavaee.relatorioVendas.model.JogoEntity;

class ClienteServiceTest {

	private ClienteService clienteService;

	@Mock
	private ClienteDAO clienteDAO;

	@Mock
	private JogosDAO jogosDAO;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.clienteService = new ClienteService(clienteDAO, jogosDAO);
	}

	@Test
	void pegaTodos() {

		List<ClienteEntity> list = listaCliente();

		Mockito.when(clienteDAO.findByAll()).thenReturn(list);

		List<ClienteDTO> clienteDTO = this.clienteService.getAll();

		assertEquals(2, clienteDTO.size());
	}

	@Test
	void pegarPorCPF() {
		String valor = "1234";

		ClienteEntity entity = criarCliente(valor);
		Mockito.when(clienteDAO.findByCpf(valor)).thenReturn(entity);

		DetalhesClienteDTO clienteDTO = this.clienteService.getByCpf(valor);

		assertTrue(clienteDTO.getNome().equals("marcelo"));
		assertEquals(DetalhesClienteDTO.class, clienteDTO.getClass());
	}

	@Test
	void salvarCliente() {
		ClienteEntity clienteEntity = criarCliente("1234");

		ModelMapper model = new ModelMapper();

		DetalhesClienteDTO dto = model.map(clienteEntity, DetalhesClienteDTO.class);
		clienteService.salvar(dto);

		Mockito.verify(clienteDAO).salvarCliente(clienteEntity);

	}

	@Test
	void alterarCliente() {
		String cpf = "1234";

		ClienteEntity entity = criarCliente(cpf);
		System.out.println(entity.getTelefone());
		Mockito.when(clienteDAO.findByCpf(ArgumentMatchers.eq(cpf))).thenReturn(entity);

		clienteService.alterarCliente(cpf, updateCliente());

		Mockito.verify(clienteDAO).alterar(entity);
		System.out.println(entity.getTelefone());
	}

	@Test
	void deletarCliente() {
		String cpf = "1234";

		ClienteEntity entity = criarCliente(cpf);
		Mockito.when(clienteDAO.findByCpf(cpf)).thenReturn(entity);

		clienteService.deleteByCpf(cpf);

		Mockito.verify(clienteDAO).deletar(entity);
	}

	private List<ClienteEntity> listaCliente() {
		List<ClienteEntity> list = new ArrayList<>();
		List<JogoEntity> jogoEntities = new ArrayList<>();
		ClienteEntity c1 = new ClienteEntity("1234", "marcelo", 12344, jogoEntities);
		ClienteEntity c2 = new ClienteEntity("111432", "lo", 3432344, jogoEntities);

		list.add(c1);
		list.add(c2);

		return list;
	}

	private ClienteEntity criarCliente(String cpf) {
		List<ClienteEntity> list = new ArrayList<>();
		List<JogoEntity> jogoEntities = new ArrayList<>();

		ClienteEntity c1 = new ClienteEntity("1234", "marcelo", 12344, jogoEntities);
		ClienteEntity c2 = new ClienteEntity("111432", "lo", 3432344, jogoEntities);

		list.add(c1);
		list.add(c2);

		for (ClienteEntity clienteEntity : list) {
			if (clienteEntity.getCpf().equals(cpf))
				return clienteEntity;
		}
		return null;
	}

	private AtualizarClienteDTO updateCliente() {
		AtualizarClienteDTO clienteDTO = new AtualizarClienteDTO();
		clienteDTO.setTelefone(0000);
		return clienteDTO;
	}

}
