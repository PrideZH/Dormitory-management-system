package com.pengfu.entity;

import java.util.List;

public class Dormitory {

	private String name;
	private String buildingId;
	private List<Student> students;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Dormitory [name=" + name + ", BuildingId=" + buildingId + "]";
	}
	
}
