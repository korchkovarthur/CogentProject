package com.furniturecloud.datalayer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DAO <T, K>{
	abstract void create(T t);
	abstract T get(K id);
	abstract void update(T t);
	abstract void delete(K id);
	abstract List<T> getAll(String ...param);
}
