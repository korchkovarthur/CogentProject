package com.furniturecloud.datalayer;

public interface DAO <T>{
	abstract void create(T t);
	abstract T get();
	abstract void update(T t);
	abstract void delete(T t);
}
