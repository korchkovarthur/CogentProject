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
public class UserRepository implements DAO<User, String> {
	@PersistenceContext
	@Autowired
	EntityManager entityManager;
	@Override
	public void create(User t) {
		entityManager.persist(t);
	}

	@Override
	public User get(String email) {
		return entityManager.find(User.class, email);
	}

	@Override
	public void update(User t) {
		if(get(t.getEmail())!=null) 
			entityManager.merge(t);		
	}

	@Override
	public void delete(String email) {
		entityManager.remove(get(email));		
	}

	@Override
	public List<User> getAll(String ...param) {
		// TODO Auto-generated method stub
		
		TypedQuery<User> query = entityManager.createNamedQuery("selectAllUsers",User.class);
		return   query.getResultList();
	}	
}
