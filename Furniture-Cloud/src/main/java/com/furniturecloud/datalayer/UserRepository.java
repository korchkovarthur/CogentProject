package com.furniturecloud.datalayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class UserRepository implements DAO<User, String> {
	@PersistenceContext
	@Autowired
	EntityManager em;
	@Override
	public void create(User t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public User get(String email) {
		// TODO Auto-generated method stub
		return em.find(User.class, email);
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub		
		if(get(t.getEmail())!=null) 
			em.merge(t);		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		em.remove(t);		
	}	
}
