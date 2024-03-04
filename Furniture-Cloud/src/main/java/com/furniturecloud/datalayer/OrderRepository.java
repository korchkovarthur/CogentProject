package com.furniturecloud.datalayer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OrderRepository implements DAO<Orders, UUID>{
	
	@PersistenceContext
	@Autowired
	EntityManager em;

	@Override
	public void create(Orders t) {
		em.persist(t);
	}

	@Override
	public Orders get(UUID id) {
		return em.find(Orders.class, id);
	}

	@Override
	public void update(Orders t) {
		if (get(t.getId()) != null) {
			em.merge(t);
		}
	}

	@Override
	public void delete(UUID id) {
		em.remove(get(id));
	}

	@Override
	public List<Orders> getAll(String... param) {
		TypedQuery<Orders> query = em.createNamedQuery(
				"selectAllOrders"+((param[0].equals("none"))?"":param[0]), 
				Orders.class);
		
		if(!param[1].equals("none"))
			query.setParameter(1, param[1]);
		
		return query.getResultList();
	}

	
}
