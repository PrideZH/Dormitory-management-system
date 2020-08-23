package com.pengfu.entity;

import java.util.List;

public class Dorm {

	private String number;
	private String bid;
	private List<Student> students;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Dorm [number=" + number + ", bid=" + bid + ", students=" + students + "]";
	}
	
}
