package com.furniturecloud.datalayer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;


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
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long SKU;
	String name;
	@Column(length = 2000)
	String descr;
	String category;
	Integer stock;
	Float price;
	public Float getPrice() { 
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Product(String name, String descr, String category, Integer stock, Float price) {
		super();
		this.name = name;
		this.descr = descr;
		this.category = category;
		this.stock = stock;
		this.price = price;
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
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}	
	
}
