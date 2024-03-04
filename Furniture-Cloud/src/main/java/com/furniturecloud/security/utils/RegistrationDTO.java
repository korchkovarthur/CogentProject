package com.furniturecloud.security.utils;

public class RegistrationDTO {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RegistrationDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public RegistrationDTO(){
		super();
	}
}
