package com.pengfu.entity;

public class Building {

	private String bid;
	private Admin admin;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Building [bid=" + bid + ", admin=" + admin + "]";
	}
	
}
