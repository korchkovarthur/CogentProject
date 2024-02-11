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
	List<Address> address;
	List<Product> shoppingCart;
	
//	int getQuantity(Product product) {		
//		return shoppingCart.stream().reduce(0, (count, item) -> 
//			(item.SKU == product.SKU) ? count+1 : count);
//	}

}
