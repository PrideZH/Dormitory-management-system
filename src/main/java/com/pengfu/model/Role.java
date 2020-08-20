package com.pengfu.model;

import com.pengfu.entity.Manager;
import com.pengfu.entity.Student;

public enum Role {
	
	Student("学生"),
	GeneralManage("普通管理员"), 
	SuperManage("超级管理员");
	
	private String RoleName;
	private static Student student;
	private static Manager manager;
	
	private Role(String RoleName) { 
        this.RoleName = RoleName; 
	}
	
	public static Student getStudent() {
		return student;
	}

	public static Manager getManager() {
		return manager;
	}

	public static void setStudent(Student student) {
		Role.student = student;
	}

	public static void setManager(Manager manager) {
		Role.manager = manager;
	}

	/** 获得已初始化对象 */
	public static Role getRole() {
		if(student != null) {
			return Student;
		}else if(manager != null) {
			return manager.getRole() == 1 ? SuperManage : GeneralManage;
		}else {
			return null;
		}
	}

	public String getRoleName() {
		return RoleName;
	}

}
