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

import br.com.apijavaee.relatorioVendas.model.ClienteEntity;

public class ClienteDAO {

	@PersistenceContext
	EntityManager em;

	public List<ClienteEntity> findByAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ClienteEntity> query = criteriaBuilder.createQuery(ClienteEntity.class);
		query.from(ClienteEntity.class);
		TypedQuery<ClienteEntity> typequery = em.createQuery(query);
		
		return typequery.getResultList();
	}

	public ClienteEntity findByCpf(String cpf){
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ClienteEntity> query = criteriaBuilder.createQuery(ClienteEntity.class);
		Root<ClienteEntity> root = query.from(ClienteEntity.class);
		Path<String> path = root.<String>get("cpf");
		
		Predicate predicate = criteriaBuilder.like(path, cpf);
		query.where(predicate);
		
		TypedQuery<ClienteEntity> typedQuery = em.createQuery(query);
		return typedQuery.getSingleResult();
	}
	
}
