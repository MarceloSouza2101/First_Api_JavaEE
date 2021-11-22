package br.com.apijavaee.relatorioVendas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.apijavaee.relatorioVendas.model.JogoEntity;

//A @Stateless elimina a necessidade de fazer a transacao manual
@Stateless
public class JogosDAO {

	@PersistenceContext
	EntityManager em;

	public List<JogoEntity> findByAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<JogoEntity> query = criteriaBuilder.createQuery(JogoEntity.class);
		query.from(JogoEntity.class);
		TypedQuery<JogoEntity> typequery = em.createQuery(query);
		
		return typequery.getResultList();
	}

	public JogoEntity findByLote(String lote) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<JogoEntity> query = criteriaBuilder.createQuery(JogoEntity.class);
		Root<JogoEntity> root = query.from(JogoEntity.class);
		Path<String> path = root.<String>get("lote");

		Predicate predicate = criteriaBuilder.like(path, lote);
		query.where(predicate);

		TypedQuery<JogoEntity> typedQuery = em.createQuery(query);
		return typedQuery.getSingleResult();
	}

	
	public void salvarJogo(JogoEntity detalhesJogoDTO) {
		em.persist(detalhesJogoDTO);
	}

}
