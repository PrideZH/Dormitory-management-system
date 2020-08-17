package com.pengfu.model;

import com.pengfu.entity.Manager;
import com.pengfu.entity.Student;

public enum Role {
	
	Student("学生"), 
	GeneralManage("普通管理员"), 
	SuperManage("超级管理员");
	
	private String RoleName;
	private Student student;
	private Manager manager;
	
	private Role(String RoleName) { 
        this.RoleName = RoleName; 
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Student getStudent() {
		return student;
	}
	
	public Manager getManager() {
		return manager;
	}
	
}
