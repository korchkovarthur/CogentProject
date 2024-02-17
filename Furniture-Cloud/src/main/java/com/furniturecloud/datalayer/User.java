package com.furniturecloud.datalayer;

//import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User {
	@Id
	private String email;
	private String firstName;
	private String lastName;
//	change to list later to accommodate more addresses
	@OneToOne
	private Address address;
//	List<Product> shoppingCart;
	@OneToOne
	private Cart mainCart;
	@OneToOne
	private Cart wishList;
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Cart getMainCart() {
		return mainCart;
	}

	public void setMainCart(Cart mainCart) {
		this.mainCart = mainCart;
	}

	public Cart getWishList() {
		return wishList;
	}

	public void setWishList(Cart wishList) {
		this.wishList = wishList;
	}

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
	

	
}
