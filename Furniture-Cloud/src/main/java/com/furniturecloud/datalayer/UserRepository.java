package com.furniturecloud.datalayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class UserRepository implements DAO<User> {
	@PersistenceContext
	@Autowired
	EntityManager em;
	@Override
	public void create(User t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public User get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	
}
