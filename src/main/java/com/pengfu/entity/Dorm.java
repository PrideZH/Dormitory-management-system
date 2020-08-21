package com.pengfu.entity;

import java.util.List;

public class Dorm {

	private String name;
	private String bid;
	private List<Student> students;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Dorm [name=" + name + ", bid=" + bid + ", students=" + students + "]";
	}
	
}
