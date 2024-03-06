package com.furniturecloud.datalayer;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

//import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;




@Entity
@NamedNativeQuery(
	    name="selectAllUsers",
	    query="SELECT USER.* FROM USERS AS USER ",
	    resultClass=User.class
	)
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
    private Integer user_id;
    
    @Column(unique = true)
    @Email
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
 
    @NotNull
	private String address;
    
    @Column(length = 1000)
    private String cartData;
    
    @Column(length = 1000)
    private String wishListData;
		
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    


    public User(String email) {
        this.email = email;
    }

    
    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    
    public void addOrder(Orders order) {
        orders.add(order);
        order.setUser(this);
    }
    public Integer getUser_id() {
 		return user_id;
 	}

 	public void setUser_id(Integer user_id) {
 		this.user_id = user_id;
 	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	}
	//Cart Data String
	public String getCartData() {
		return cartData;
	}
	public void setCartData(String cartData) {
		this.cartData = cartData;
	}
	
	//Cart Data String
	public String getWishListData() {
		return wishListData;
	}

	public void setWishListData(String wishListData) {
		this.wishListData = wishListData;
	}
	


	public User() {
		
		
	}
	
	public User(String firstName, String lastName, String email, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
//		this.mainCart = new Cart();
//		this.wishList = new Cart();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
