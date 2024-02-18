package com.furniturecloud.datalayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
	public List<?> getAll() {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("selectAll",Product.class);
			
		return  query.getResultList();
	}
	


}
