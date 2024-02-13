package com.furniturecloud.datalayer;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	String email;
	String firstName;
	String lastName;
	// change to list later to accommodate more addresses
	Address address;
	// List<Product> shoppingCart;
	Cart mainCart;
	Cart wishList;
	
	public User() {
		
		
	}
	
	public User(String firstName, String lastName, String email, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.mainCart = new Cart();
		this.wishList = new Cart();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
//	int getQuantity(Product product) {		
//		return shoppingCart.stream().reduce(0, (count, item) -> 
//			(item.SKU == product.SKU) ? count+1 : count);
//	}

	
}
