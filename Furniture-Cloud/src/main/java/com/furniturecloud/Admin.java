package com.furniturecloud;



import com.furniturecloud.datalayer.DAO;
import com.furniturecloud.datalayer.Product;
import com.furniturecloud.datalayer.User;

public class Admin {
	
	private static DAO<User, String> user;
	private static DAO<Product, Long> product;
	//Missing an annotation to resolve request to User object  
	//Need to decide on return values
	
	public void createProduct(Product prod) {
		product.create(prod);
	}
	public void deleteProduct(Product prod) {
		product.delete(prod);
	}
	public void updateUser(Product prod) {
		product.update(prod);
	}

	
	public void createUser(User t) {
		user.create(t);
	}

	public void deleteUser(User t) {
		user.delete(t);
	}

	public void updateUser(User t) {  
		user.update(t);
	}

	public void createUser(String email) {
		user.get(email);
	}
	
}
