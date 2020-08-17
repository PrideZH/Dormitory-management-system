package com.pengfu.entity;

public class Building {

	private String bid;
	private Manager manager;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Building [bid=" + bid + ", manager=" + manager + "]";
	}
	
}
