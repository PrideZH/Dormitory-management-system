package com.pengfu.entity;

public class Manager {

	private String username;
	private String password;
	private String name;
	private int role;
	private String bid;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "Manager [username=" + username + ", password=" + password + ", name=" + name
				+ ", role=" + role + ", bid=" + bid + "]";
	}
	
}
