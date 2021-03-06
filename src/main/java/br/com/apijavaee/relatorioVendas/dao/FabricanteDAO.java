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

import br.com.apijavaee.relatorioVendas.model.FabricanteEntity;

//A @Stateless elimina a necessidade de fazer a transacao manual
@Stateless
public class FabricanteDAO {

	@PersistenceContext
	EntityManager em;

	public List<FabricanteEntity> findAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<FabricanteEntity> query = criteriaBuilder.createQuery(FabricanteEntity.class);
		query.from(FabricanteEntity.class);
		TypedQuery<FabricanteEntity> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

	public FabricanteEntity findByCnpj(String cnpj) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<FabricanteEntity> query = criteriaBuilder.createQuery(FabricanteEntity.class);
		Root<FabricanteEntity> root = query.from(FabricanteEntity.class);
		Path<String> path = root.<String>get("cnpj");

		Predicate predicate = criteriaBuilder.like(path, cnpj);
		query.where(predicate);

		TypedQuery<FabricanteEntity> typedQuery = em.createQuery(query);
		FabricanteEntity entity = typedQuery.getSingleResult();
		return entity;
	}

	public void salvarFabricante(FabricanteEntity fabricanteEntity) {
		em.persist(fabricanteEntity);
	}

	public void alterar(FabricanteEntity fabricanteEntity) {
		em.merge(fabricanteEntity);
	}

	public void delete(FabricanteEntity entity) {
		FabricanteEntity fabricanteEntity = em.merge(entity);
		em.remove(fabricanteEntity);
	}

}
