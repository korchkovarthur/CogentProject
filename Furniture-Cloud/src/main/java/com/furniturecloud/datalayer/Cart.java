package com.furniturecloud.datalayer;

import java.util.HashMap;
import java.util.Map;

// when user logins load this from database
// when user logs off or tries to buy

public class Cart {
	
	// <product id, quantity>
	Map<Product, Integer> cart;
	
	public Cart() {
		cart = new HashMap<>();
	}
	
	void addToCart(Product product) {
		if (cart.containsKey(product)) {
			cart.put(product, cart.get(product) + 1);
		}
		cart.putIfAbsent(product, 1);
	}
	
	boolean removeFromCart(Product product) {
		if (cart.containsKey(product)) {
			cart.put(product, cart.get(product) - 1);
			return true;
		}
		return false;
	}

}
