package com.furniturecloud.datalayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OrderRepository implements DAO<Order, Integer>{
	
	@PersistenceContext
	@Autowired
	EntityManager em;

	@Override
	public void create(Order t) {
		em.persist(t);
	}

	@Override
	public Order get(Integer id) {
		return em.find(Order.class, id);
	}

	@Override
	public void update(Order t) {
		if (get(t.getId()) != null) {
			em.merge(t);
		}
	}

	@Override
	public void delete(Integer id) {
		em.remove(get(id));
	}

	@Override
	public List<Order> getAll(String... param) {
		TypedQuery<Order> query = em.createNamedQuery(
				"selectAll"+((param[0].equals("none"))?"":param[0]), 
				Order.class);
		
		if(!param[1].equals("none"))
			query.setParameter(1, param[1]);
		
		return query.getResultList();
	}

	
}
