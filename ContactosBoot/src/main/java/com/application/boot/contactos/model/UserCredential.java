package com.application.boot.contactos.model;

public class UserCredential {

	private String userName;
	private String password;
	
	public UserCredential() {
		//constructor por omision
	}
	public UserCredential(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return String.format("UserCredential [userName=%s, password=%s]", userName, password);
	}	
}
