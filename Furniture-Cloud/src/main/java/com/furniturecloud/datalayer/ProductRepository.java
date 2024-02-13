package com.furniturecloud.datalayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class ProductRepository implements DAO<Product> {
	@PersistenceContext
	@Autowired
	EntityManager entityManager;

	@Override
	public void create(Product product) {
		entityManager.persist(product);		
	}

	@Override
	public Product get(long SKU) {
		return entityManager.find(Product.class, SKU);
	}

	@Override
	public void update(Product product) {
		if(((ProductRepository) entityManager).get(product.SKU) == null) entityManager.merge(product);
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}
	
	

}
