package com.pengfu.model;

import java.util.List;

import com.pengfu.entity.Admin;
import com.pengfu.entity.Student;
import com.pengfu.service.BuildingService;
import com.pengfu.util.SpringContextUtils;

/**
 * 个人用户模型
 * 用于存储个人信息
 * @author PrideZH
 */
public class PersonalModel {
	
	private static PersonalModel instance = null;
	
	// 用户权限
	private Role role;
	// 用户信息
	private Student student;
	private Admin admin;
	
	private PersonalModel() {}
	
	public static PersonalModel getInstance() {
		if(null == instance) {
			instance = new PersonalModel();
		}
		return instance;
	}

	public Student getStudent() {
		return student;
	}

	/** 设置学生用户信息 */
	public void setStudent(Student student) {
		role = Role.STUDENT;
		this.student = student;
	}

	public Admin getAdmin() {
		return admin;
	}
	
	/** 设置管理员用户信息 */
	public void setAdmin(Admin admin) {
		role = Role.getbycode(admin.getAid());
		this.admin = admin;
	}
	
	public Role getRole() {
		return role;
	}
	
	/** 获得管理楼层列表 */
	public List<String> getBids() {
		switch(role) {
		case GENERAL_ADMIN:
			return admin.getBids();
		case STUDENT:
			return null;
		case SUPER_ADMIN:
			return SpringContextUtils.getBean(BuildingService.class).getAllId();
		}
		return null;
	}
	
}
