package com.furniturecloud.datalayer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	    name="selectAll",
	    query="SELECT PRODUCT.* FROM PRODUCT_ AS PRODUCT ",
	    resultClass=Product.class
	)


//	Query query = em.createNamedQuery("complexQuery", User.class);
//	query.setParameter(1, id);
//	User user = (User) query.getSingleResult();
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long SKU;
	String name;
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
	String category;
	int stock;
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
