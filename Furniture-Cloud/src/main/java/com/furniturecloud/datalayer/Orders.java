package com.furniturecloud.datalayer;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.validation.constraints.NotNull;

@NamedNativeQuery(
	    name="selectAllOrderss",
	    query="SELECT Orders.* FROM Orders AS Orders ",
	    resultClass=Orders.class
	)
@NamedNativeQuery(
	    name="selectAllUserId",
	    query="SELECT Orders.* FROM Orders AS Orders WHERE Orders.userId =?1",
	    resultClass=Orders.class
	)


@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	private String cart;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable=false)
	@NotNull
	private User user ;//Used for keeping User and this entity in sync
	
	
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
