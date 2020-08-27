package com.pengfu.model;

import com.pengfu.entity.Admin;
import com.pengfu.entity.Student;
import com.pengfu.service.BuildingService;
import com.pengfu.util.SpringContextUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Role {
	
	STUDENT(-1, "学生") {
		@Override
		public Map<String, List<String>> getPermissionList() {
			Map<String, List<String>> permissionList = new LinkedHashMap<String, List<String>>();
			
			// 其他：首页、系统设置
			permissionList.put("other", Arrays.asList("system", "home"));
			// 个人中心：宿舍信息、个人信息
			permissionList.put("personal", Arrays.asList("dormInfo", "studentProfile"));
			// 后勤服务：损坏保修
			permissionList.put("logistics", Arrays.asList("damageWarranty"));
			// 生活服务：电费充值、校园网充值、校园一卡通
			permissionList.put("life", Arrays.asList("electricity", "networkt", "card"));	
			
			return permissionList;
		}
		@Override
		public List<String> getBids() {
			return null;
		}
	},
	GENERAL_ADMIN(0, "普通管理员") {
		@Override
		public Map<String, List<String>> getPermissionList() {
			Map<String, List<String>> permissionList = new LinkedHashMap<String, List<String>>();
			
			// 其他：首页、系统设置
			permissionList.put("other", Arrays.asList("system", "home"));
			// 个人中心：个人信息
			permissionList.put("personal", Arrays.asList("adminProfile"));
			// 宿舍管理：宿舍列表
			permissionList.put("dorm", Arrays.asList("dormList"));
			// 学生管理
			permissionList.put("student", Arrays.asList("studentList"));
			// 后勤服务
			permissionList.put("logistics", Arrays.asList("damageList"));
			
			return permissionList;
		}
		@Override
		public List<String> getBids() {
			return admin.getBids();
		}
	}, 
	SUPER_ADMIN(1, "超级管理员") {
		@Override
		public Map<String, List<String>> getPermissionList() {
			Map<String, List<String>> permissionList = new LinkedHashMap<String, List<String>>();

			// 其他：首页、系统设置
			permissionList.put("other", Arrays.asList("system", "home"));
			// 个人中心：个人信息
			permissionList.put("personal", Arrays.asList("adminProfile"));
			// 楼宇管理：楼宇列表
			permissionList.put("building", Arrays.asList("buildingList"));
			// 宿舍管理：宿舍列表
			permissionList.put("dorm", Arrays.asList("dormList"));
			// 用户权限管理
			permissionList.put("admin", Arrays.asList("adminList"));
			// 学生管理
			permissionList.put("student", Arrays.asList("studentList"));
			// 后勤服务
			permissionList.put("logistics", Arrays.asList("damageList"));

			return permissionList;
		}
		@Override
		public List<String> getBids() {
			return SpringContextUtils.getBean(BuildingService.class).getAllId();
		}
	};
	
	private final int code;
	private final String name;

	/** 保存当前权限角色信息 */
	private static Student student;
	private static Admin admin;

	private Role(int code, String name) { 
		this.code = code;
        this.name = name; 
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public static Student getStudent() {
		return student;
	}

	public static void setStudent(Student student) {
		Role.student = student;
	}

	public static Admin getAdmin() {
		return admin;
	}
	
	public static void setAdmin(Admin admin) {
		Role.admin = admin;
	}
	
	/** 获得已初始化权限 */
	public static Role getRole() {
		if(student != null) {
			return STUDENT;
		}else if(admin != null) {
			return getbycode(admin.getRole());
		}else {
			return null;
		}
	}
	
	/** 通过编号获得权限 */
	public static Role getbycode(int code) {
		for(Role role : values()) {
			if(role.getCode() == code) {
				return role;
			}
		}
		return null;
	}
	
	/** 通过权限名获得编号 */
	public static int getCodeByName(String name) {
		for(Role role : values()) {
			if(role.getName().equals(name)) {
				return role.getCode();
			}
		}
		return 0;
	}
	
	/** 通过编号获得权限名 */
	public static String getNameByCode(int code) {
		for(Role role : values()) {
			if(role.getCode() == code) {
				return role.getName();
			}
		}
		return null;
	}
	
	/** 获得权限管理列表 */
	public abstract Map<String, List<String>> getPermissionList();
	
	/** 获得管理的楼层 */
	public abstract List<String> getBids();
	
}
