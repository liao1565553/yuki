package com.yuki.account.dto;

public class User extends BaseDto{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1531962007125555084L;

	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
