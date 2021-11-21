package br.com.apijavaee.relatorioVendas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.apijavaee.relatorioVendas.model.JogoEntity;

public class JogosDAO {

	@PersistenceContext
	EntityManager em;

	public List<JogoEntity> findByAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<JogoEntity> query = criteriaBuilder.createQuery(JogoEntity.class);
		TypedQuery<JogoEntity> typequery = em.createQuery(query.select(query.from(JogoEntity.class)));
		
		return typequery.getResultList();
	}

	public JogoEntity findByLote(String nome) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<JogoEntity> query = criteriaBuilder.createQuery(JogoEntity.class);
		Root<JogoEntity> root = query.from(JogoEntity.class);
		Path<String> path = root.<String>get("lote");

		Predicate predicate = criteriaBuilder.like(path, nome);
		query.where(predicate);

		TypedQuery<JogoEntity> typedQuery = em.createQuery(query);
		return typedQuery.getSingleResult();

	}

}
