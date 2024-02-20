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
public class ProductRepository implements DAO<Product, Long> {
	@PersistenceContext
	@Autowired
	EntityManager entityManager;

	@Override
	public void create(Product product) {
		entityManager.persist(product);		
	}

	@Override
	public void update(Product product) {
		if(get(product.SKU) != null) 
			entityManager.merge(product);
	}

	
	public void delete(Long sku) {
		entityManager.remove(get(sku));;
	}
	@Override
	public Product get(Long id) {	
		return entityManager.find(Product.class, id);	
	}

	@Override
	public List<Product> getAll(String ...param) {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = entityManager.createNamedQuery(
				"selectAll"+((param[0].equals("none"))?"":param[0]), 
				Product.class);
		if(!param[1].equals("none"))
			query.setParameter(1, param[1]);
		return   query.getResultList();
	}
	


}
