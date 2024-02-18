package com.furniturecloud.datalayer;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// when user logins load this from database
// when user logs off or tries to buy

@Entity
public class Cart {
	
	@Id
	Long cartId;
	
	// <productId, quantity>
	Map<Long, Integer> cart;
	
	public Cart() {
		cart = new HashMap<>();
	}
	public Cart(String mapData) {
		cart = new HashMap<>();
		parseCart(mapData);
	}
	
	
	public int getSize() {
		return cart.size();
	}
	
	public boolean isEmpty() {
		return cart.isEmpty();
	}
	
	public void addToCart(Long productId) {
		increment(productId);
		cart.putIfAbsent(productId, 1);
	}
	
	public boolean removeFromCart(Long productId) {
		if (cart.containsKey(productId)) {
			cart.remove(productId);
			return true;
		}
		return false; // item not in cart
	}
	
	public void increment(Long productId) {
		if (cart.containsKey(productId)) {
			cart.put(productId, cart.get(productId) + 1);
		}
	}
	
	public void decrement(Long productId) {
		if (cart.containsKey(productId)) {
			cart.put(productId, cart.get(productId) - 1);
		}
	}
	
	public String toData() {
		StringBuilder data =new StringBuilder();
		for(Long id : cart.keySet()) {
			data.append(id+" "+cart.get(id)+",");
		}
		return data.toString();
	}
	public void parseCart(String data) {
		String[] dataArray = data.split(",");
		for(String item:dataArray) {
			String arr[] =item.split(" ");
			cart.put(Long.parseLong(arr[0]),Integer.parseInt(arr[1]));
		}
	}
		


}
