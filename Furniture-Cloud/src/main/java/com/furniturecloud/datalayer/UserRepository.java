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
public class UserRepository implements DAO<User, Integer> {
	@PersistenceContext
	@Autowired
	EntityManager entityManager;
	@Override
	public void create(User t) {
		entityManager.persist(t);
	}

	@Override
	public User get(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void update(User t) {
		if(get(t.getUser_id())!=null) 
			entityManager.merge(t);		
	}

	@Override
	public void delete(Integer a) {
		entityManager.remove(get(a));		
	}

	@Override
	public List<User> getAll(String ...param) {
		// TODO Auto-generated method stub
		
		TypedQuery<User> query = entityManager.createNamedQuery("selectAllUsers",User.class);
		return   query.getResultList();
	}	
}
