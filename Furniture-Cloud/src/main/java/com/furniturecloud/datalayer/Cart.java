package com.furniturecloud.datalayer;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// when user logins load this from database
// when user logs off or tries to buy
@Entity
public class Cart {
	
	// <product id, quantity>
	@Id
	long cartId;
	
	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	
	Map<Long, Integer> cart;
	
	public Cart() {
		cart = new HashMap<>();
	}
	
	public int getSize() {
		return cart.size();
	}
	
	public boolean isEmpty() {
		return cart.isEmpty();
	}
	
	public void addToCart(Product product) {
		increment(product);
		cart.putIfAbsent(product, 1);
	}
	
	public boolean removeFromCart(Product product) {
		if (cart.containsKey(product)) {
			cart.remove(product);
			return true;
		}
		return false; // item not in cart
	}
	
	public void increment(Product product) {
		if (cart.containsKey(product)) {
			cart.put(product, cart.get(product) + 1);
		}
	}
	
	public void decrement(Product product) {
		if (cart.containsKey(product)) {
			cart.put(product, cart.get(product) - 1);
		}
	}

}
