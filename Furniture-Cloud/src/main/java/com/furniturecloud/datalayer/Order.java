package com.furniturecloud.datalayer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;

@NamedNativeQuery(
	    name="selectAll",
	    query="SELECT ORDER.* FROM ORDER AS ORDER ",
	    resultClass=Order.class
	)
@NamedNativeQuery(
	    name="selectAllUserId",
	    query="SELECT ORDER.* FROM ORDER AS ORDER WHERE ORDER.userId =?1",
	    resultClass=Order.class
	)


@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	Integer id;
	String cart;
	String userId; // email
	
	public Order() {}
	
	public Order(String cart, String userId) {
		super();
		this.cart = cart;
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
