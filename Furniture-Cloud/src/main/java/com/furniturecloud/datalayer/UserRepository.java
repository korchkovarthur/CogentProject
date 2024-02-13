package com.furniturecloud.datalayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
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
	public void delete(User t) {
		entityManager.remove(t);		
	}	
}
