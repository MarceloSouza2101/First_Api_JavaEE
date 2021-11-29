package br.com.apijavaee.relatorioVendas.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import br.com.apijavaee.relatorioVendas.dao.JogosDAO;
import br.com.apijavaee.relatorioVendas.dto.AtualizarJogoDTO;
import br.com.apijavaee.relatorioVendas.dto.DetalhesJogoDTO;
import br.com.apijavaee.relatorioVendas.dto.JogoDTO;
import br.com.apijavaee.relatorioVendas.model.JogoEntity;

public class JogoServiceTest {

	private JogoService jogoService;

	@Mock
	private JogosDAO jogosDAO;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void before() {
		MockitoAnnotations.initMocks(this);
		this.jogoService = new JogoService(jogosDAO);
	}

	@Test
	void pegarTodos() {

		List<JogoEntity> todos = listJogo();

		Mockito.when(jogosDAO.findByAll()).thenReturn(todos);

		List<JogoDTO> dtos = this.jogoService.getAll();
		
		assertEquals(2, dtos.size());
	}

	@Test
	void pegarPorCpf() {
		String valor = "0001";

		JogoEntity dto = criarJogo(valor);

		Mockito.when(jogosDAO.findByLote(ArgumentMatchers.eq(valor))).thenReturn(dto);

		DetalhesJogoDTO dto1 = this.jogoService.getByLote(valor);

		Assert.assertTrue(dto1.getLote().equals(valor));
	}

	@Test
	void pegarPorCpfFail() {
		String valor = "0003";

		JogoEntity dto = criarJogo(valor);

		Mockito.when(jogosDAO.findByLote(ArgumentMatchers.eq(valor))).thenReturn(dto);

		assertNull(dto);
	}

	@Test
	void salvar() {
		JogoEntity jogo = criarJogo("0001");

		ModelMapper model = new ModelMapper();

		jogoService.salvar(model.map(jogo, DetalhesJogoDTO.class));

		Mockito.verify(jogosDAO).salvarJogo(jogo);
	}

	@Test
	void alterarJogoeSalvar() {
		String valor = "0001";

		JogoEntity dto = criarJogo(valor);
		System.out.println(dto.getModalidade());
		Mockito.when(jogosDAO.findByLote(ArgumentMatchers.eq(valor))).thenReturn(dto);

		jogoService.alterarJogo(valor, criarAtualizarJogo());

		Mockito.verify(jogosDAO).alterar(dto);
		System.out.println(dto.getModalidade());
	}

	@Test
	void deletarJogo() {

		String valor = "0001";

		JogoEntity dto = criarJogo(valor);
		Mockito.when(jogosDAO.findByLote(ArgumentMatchers.eq(valor))).thenReturn(dto);

		jogoService.deletar(valor);

		Mockito.verify(jogosDAO).deleteByCpf(dto);
	}

	private List<JogoEntity> listJogo() {
		List<JogoEntity> list = new ArrayList<>();
		JogoEntity jogoEntity = new JogoEntity("0002", "gthrt2", "acao", LocalDate.now(), "efdhiufhd");
		JogoEntity jogoEntity2 = new JogoEntity("0001", "gtfdsfdshrt2", "acao", LocalDate.now(), "efdhiufhd");

		list.add(jogoEntity2);
		list.add(jogoEntity);

		return list;
	}

	private JogoEntity criarJogo(String valor) {
		JogoEntity jogoEntity = new JogoEntity("0002", "gthrt2", "acao", LocalDate.now(), "efdhiufhd");
		JogoEntity jogoEntity2 = new JogoEntity("0001", "gtfdsfdshrt2", "acao", LocalDate.now(), "efdhiufhd");

		List<JogoEntity> entities = new ArrayList<>();
		entities.add(jogoEntity);
		entities.add(jogoEntity2);

		JogoEntity novo = null;

		for (JogoEntity jogoEntity3 : entities) {
			if (jogoEntity3.getLote().equals(valor)) {
				novo = jogoEntity3;
				break;
			}
		}
		return novo;
	}

	private AtualizarJogoDTO criarAtualizarJogo() {
		AtualizarJogoDTO atualiar = new AtualizarJogoDTO();
		atualiar.setDescricao("eu estou sem criatividade");
		atualiar.setModalidade("vai passar");

		return atualiar;
	}
}