package com.pengfu.entity;

/** 管理员 */
public class Admin {

	private int aid; // id
	private String username; // 用户名
	private String password; // 密码
	private String name; // 姓名
	private String phone; // 联系电话
	private int role; // 权限 0-普通管理 1-超级管理
	private String bid; // 管理楼宇编号
	
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		return "Manager [username=" + username + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", role=" + role + ", bid=" + bid + "]";
	}
	
}
