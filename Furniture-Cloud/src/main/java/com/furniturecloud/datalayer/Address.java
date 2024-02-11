package com.furniturecloud.datalayer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {	
	@Id
	String addressId;
	String addressLine1;
	String addressLine2;
	String city;
	String state;
	int zipCode;
}
