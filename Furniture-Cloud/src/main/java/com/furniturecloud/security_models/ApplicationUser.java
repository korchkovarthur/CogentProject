package com.furniturecloud.security_models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.furniturecloud.datalayer.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="app_users")
public class ApplicationUser implements UserDetails {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="app_user_id")
	private Integer userId;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
	
	private String username;
	
	
	private String password;
	
	
	public ApplicationUser(String username, String password, Set<Role> authorities, User u) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.user=u;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="user_role",
	        joinColumns= {@JoinColumn(name="sec_user_id")},
	        inverseJoinColumns= {@JoinColumn(name="role_id")}
			)
	private Set<Role> authorities;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public ApplicationUser() {
		// TODO Auto-generated constructor stub
		authorities=new HashSet<Role>();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		this.username=user.getEmail();
	}
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}
	public ApplicationUser(Integer userId, String username, String password, Set<Role> authorities,User user) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.user=user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
