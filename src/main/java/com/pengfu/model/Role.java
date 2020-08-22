package com.pengfu.model;

import com.pengfu.entity.Admin;
import com.pengfu.entity.Student;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Role {
	
	Student("学生") {
		@Override
		public Map<String, List<String>> getPermissionList() {
			Map<String, List<String>> permissionList = new LinkedHashMap<String, List<String>>();
			
			// 个人中心：宿舍信息、个人信息
			permissionList.put("personal", Arrays.asList("dormitoryInfo", "studentProfile"));
			// 后勤服务：损坏保修
			permissionList.put("logistics", Arrays.asList("damageWarranty"));
			// 生活服务：电费充值、校园网充值、校园一卡通
			permissionList.put("life", Arrays.asList("electricity", "networkt", "card"));	
			
			return permissionList;
		}
	},
	GeneralManage("普通管理员") {
		@Override
		public Map<String, List<String>> getPermissionList() {
			Map<String, List<String>> permissionList = new LinkedHashMap<String, List<String>>();
			
			// 个人中心：个人信息
			permissionList.put("personal", Arrays.asList("adminProfile"));
			// 楼宇管理：楼宇列表
			permissionList.put("building", Arrays.asList("buildingList"));
			// 宿舍管理：宿舍列表
			permissionList.put("dormitory", Arrays.asList("dormitoryList"));
			// 学生管理
			permissionList.put("student", Arrays.asList("studentList"));
			// 后勤服务
			permissionList.put("logistics", Arrays.asList("damageList"));
			
			return permissionList;
		}
	}, 
	SuperManage("超级管理员") {
		@Override
		public Map<String, List<String>> getPermissionList() {
			Map<String, List<String>> permissionList = new LinkedHashMap<String, List<String>>();

			// 个人中心：个人信息
			permissionList.put("personal", Arrays.asList("adminProfile"));
			// 楼宇管理：楼宇列表
			permissionList.put("building", Arrays.asList("buildingList"));
			// 宿舍管理：宿舍列表
			permissionList.put("dormitory", Arrays.asList("dormitoryList"));
			// 用户权限管理
			permissionList.put("manager", Arrays.asList("managerList"));
			// 学生管理
			permissionList.put("student", Arrays.asList("studentList"));
			// 后勤服务
			permissionList.put("logistics", Arrays.asList("damageList"));

			return permissionList;
		}
	};
	
	private String RoleName;
	private static Student student;
	private static Admin manager;
	
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
	
	/** 获得权限管理列表 */
	public abstract Map<String, List<String>> getPermissionList();
	
	private Role(String RoleName) { 
        this.RoleName = RoleName; 
	}
	
	public static Student getStudent() {
		return student;
	}

	public static Admin getManager() {
		return manager;
	}

	public static void setStudent(Student student) {
		Role.student = student;
	}

	public static void setManager(Admin manager) {
		Role.manager = manager;
	}

	public String getRoleName() {
		return RoleName;
	}

}
