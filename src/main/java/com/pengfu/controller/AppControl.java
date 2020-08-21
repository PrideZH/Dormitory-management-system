package com.pengfu.controller;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pengfu.entity.Building;
import com.pengfu.entity.Dorm;
import com.pengfu.entity.Admin;
import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.service.BuildingService;
import com.pengfu.service.DormService;
import com.pengfu.service.AdminService;
import com.pengfu.service.StudentService;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.LoginFrame;
import com.pengfu.view.MainFrame;

@Controller("appControl")
public class AppControl {
	
	// 业务层对象
	private BuildingService buildingService;
	private DormService dormService;
	private AdminService adminService;
	private StudentService studentService;
	
	/** 注入成员变量 */
	@Autowired
	public AppControl(BuildingService buildingService, DormService dormService,
			AdminService adminService, StudentService studentService) {
		this.buildingService = buildingService;
		this.dormService = dormService;
		this.adminService = adminService;
		this.studentService = studentService;
	}

	/** 登陆操作  */
	public void Logint(boolean isStudent, String username, String password) {
		try {
			if(isStudent) { // 用户登陆
				//Student student = studentService.loginQuery(username, password);
				Student student = studentService.loginQuery("201910097001", "123456");
				Role.setStudent(student);
			}else { // 管理员登陆
				//Manager manager = managerService.loginQuery(username, password);
				Admin manager = adminService.loginQuery("123456", "123456");
				Role.setManager(manager);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
		// 显示主窗口
		SpringContextUtils.getBean(MainFrame.class).setVisible(true);
		
		closeLoginFrame();
	}
	
	/** 关闭登陆窗口 */
	public void closeLoginFrame() {
		SpringContextUtils.getBean(LoginFrame.class).dispose();
	}
	
	/** 添加楼宇 */
	public void addBuilding(Building building) throws Exception {
		buildingService.addBuilding(building);
	}

	/** 添加宿舍 */
	public void addDormitory(Dorm dormitory) throws Exception {
		dormService.addDormitoryt(dormitory);
	}

	/** 添加管理员 */
	public void addManager(Admin manager) throws Exception {
		adminService.addManager(manager);
	}

	/** 添加学生 */
	public void addStudent(Student student) throws Exception {
		studentService.addStudent(student);
	}

}
