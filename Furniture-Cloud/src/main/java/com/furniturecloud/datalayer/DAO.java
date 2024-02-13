package com.furniturecloud.datalayer;

public interface DAO <T, K>{
	abstract void create(T t);
	abstract T get(K id);
	abstract void update(T t);
	abstract void delete(T t);
	Product get(long SKU);
}
