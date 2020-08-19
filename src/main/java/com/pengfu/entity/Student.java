package com.pengfu.entity;

public class Student {
	
	private String sid;
	private String password;
	private String name;
	private String gender;
	private String idCard;
	private String college;
	private String classes;
	private String phone;
	private String BuildingId;
	private String DormitoryId;

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

	public String getBuildingId() {
		return BuildingId;
	}

	public void setBuildingId(String buildingId) {
		BuildingId = buildingId;
	}

	public String getDormitoryId() {
		return DormitoryId;
	}

	public void setDormitoryId(String dormitoryId) {
		DormitoryId = dormitoryId;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", password=" + password + ", name=" + name + ", gender=" + gender + ", idCard="
				+ idCard + ", college=" + college + ", classes=" + classes + ", phone=" + phone + ", BuildingId="
				+ BuildingId + ", DormitoryId=" + DormitoryId + "]";
	}

}
