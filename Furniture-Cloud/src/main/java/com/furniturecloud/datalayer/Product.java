package com.furniturecloud.datalayer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	    name="selectAll",
	    query="SELECT PRODUCT.* FROM PRODUCT AS PRODUCT ",
	    resultClass=Product.class
	)
@NamedNativeQuery(
	    name="selectAllCategory",
	    query="SELECT PRODUCT.* FROM PRODUCT AS PRODUCT WHERE PRODUCT.category =?1",
	    resultClass=Product.class
	)


public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long SKU;
	String name;
	String category;
	Integer stock;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSKU(Long sKU) {
		SKU = sKU;
	}
	public Product(String category, int stock) {
		super();
		this.category = category;
		this.stock = stock;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public long getSKU() {
		return SKU;
	}
	public void setSKU(long sKU) {
		SKU = sKU;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}	
	
}
