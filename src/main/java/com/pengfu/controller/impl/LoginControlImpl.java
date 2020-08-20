package com.pengfu.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pengfu.controller.LoginControl;
import com.pengfu.entity.Manager;
import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.service.ManagerService;
import com.pengfu.service.StudentService;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.LoginFrame;
import com.pengfu.view.MainFrame;

@Controller("loginControl")
public class LoginControlImpl implements LoginControl {
	
	// 业务层对象
	private StudentService studentService;
	private ManagerService managerService;
	
	/** 注入成员变量 */
	@Autowired
	public LoginControlImpl(StudentService studentService, ManagerService managerService) {
		this.studentService = studentService;
		this.managerService = managerService;
	}

	/** 登陆操作  */
	@Override
	public void Logint(boolean isStudent, String username, String password) throws Exception {
//		if("".equals(username)) {
//			throw new Exception("账号不能为空");
//		}else if("".equals(password)) {
//			throw new Exception("密码不能为空");
//		}
		
		if(isStudent) { // 用户登陆
			//Student student = studentService.loginQuery(username, password);
			Student student = studentService.loginQuery("201910097001", "123456");
			Role.setStudent(student);
		}else { // 管理员登陆
			//Manager manager = managerService.loginQuery(username, password);
			Manager manager = managerService.loginQuery("123456", "123456");
			Role.setManager(manager);
		}
		// 显示主窗口
		SpringContextUtils.getBean(MainFrame.class).setVisible(true);
		close();
	}
	
	/** 关闭窗口 */
	@Override
	public void close() {
		SpringContextUtils.getBean(LoginFrame.class).dispose();
	}

}
