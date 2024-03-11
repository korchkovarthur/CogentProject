package com.furniturecloud.datalayer;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@NamedNativeQuery(
	    name="selectAllOrders",
	    query="SELECT Orders.* FROM Orders AS Orders ",
	    resultClass=Orders.class
	)
@NamedNativeQuery(
	    name="selectAllOrdersUser",
	    query="SELECT Orders.* FROM Orders AS Orders WHERE Orders.user_id =?1",
	    resultClass=Orders.class
	)


@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	private String cart;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable=false)
	private User user ;//Used for keeping User and this entity in sync
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	public Orders() {}
	
	public Orders(String cart, User user) { 
		super();
		this.cart = cart;
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}



	

}
