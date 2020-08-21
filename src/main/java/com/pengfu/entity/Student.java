package com.pengfu.entity;

public class Student {
	
	private String sid; // 学号
	private String password; // 密码
	private String name; // 姓名
	private String gender; // 性别
	private String idCard; // 身份证号
	private String college; // 学院
	private String classes; // 班级
	private String phone; // 联系电话
	private String bid; // 楼宇编号
	private String dormName; // 宿舍号

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", password=" + password + ", name=" + name + ", gender=" + gender + ", idCard="
				+ idCard + ", college=" + college + ", classes=" + classes + ", phone=" + phone + ", bid="
				+ bid + ", dormName=" + dormName + "]";
	}

}
