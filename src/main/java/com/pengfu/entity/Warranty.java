package com.pengfu.entity;

import java.util.Date;

public class Warranty {

	private String did; // 宿舍号
	private String name; // 学生姓名
	private Date date; // 日期
	private Date phone; // 联系电话
	private String describe; // 问题描述
	private String state; // 状态

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getPhone() {
		return phone;
	}

	public void setPhone(Date phone) {
		this.phone = phone;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
