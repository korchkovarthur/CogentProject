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

	private String cartData;
	private String wishListData;
	
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

//	}
	//Cart Data String
	public String getCartData() {
		return cartData;
	}
	public void setCartData(String cartData) {
		this.cartData = cartData;
	}
	
	//Cart Data String
	public String getWishListData() {
		return wishListData;
	}

	public void setWishListData(String wishListData) {
		this.wishListData = wishListData;
	}
	


	public User() {
		
		
	}
	
	public User(String firstName, String lastName, String email, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
//		this.mainCart = new Cart();
//		this.wishList = new Cart();
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
//	List<Product> shoppingCart;
//	@Transient
//	private Cart mainCart;
//	@Transient
//	private Cart wishList;

	//Cart Object
//	public Cart getWishList() {
//		return wishList;
//	}
//
//	public void setWishList(Cart wishList) {
//		this.wishList = wishList;
//	}
	//Cart Object
//	public Cart getMainCart() {
//		return mainCart;
//	}
//
//	public void setMainCart(Cart mainCart) {
//		this.mainCart = mainCart;
}
