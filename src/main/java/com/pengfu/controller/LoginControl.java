package com.pengfu.controller;

import com.pengfu.entity.Manager;
import com.pengfu.entity.Student;
import com.pengfu.service.ManagerService;
import com.pengfu.service.StudentService;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.LoginFrame;
import com.pengfu.view.MainFrame;

public class LoginControl {
	
	private StudentService studentService;
	private ManagerService managerService;
	
	private LoginFrame loginFrame;
	
	public LoginControl(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
		
		// 获取Service对象
		studentService = SpringContextUtils.getBean("studentService", StudentService.class);
		managerService = SpringContextUtils.getBean("managerService", ManagerService.class);
	}

	/** 登陆操作  */
	public void Logint(boolean isStudent, String username, String password) throws Exception {
//		if("".equals(username)) {
//			throw new Exception("账号不能为空");
//		}else if("".equals(password)) {
//			throw new Exception("密码不能为空");
//		}
		
		MainFrame mainFrame = null;
		if(isStudent) { // 用户登陆
			//Student student = studentService.loginQuery(username, password);
			Student student = studentService.loginQuery("201910097001", "123456");
			mainFrame = new MainFrame(student);
		}else { // 管理员登陆
			//Manager manager = managerService.loginQuery(username, password);
			Manager manager = managerService.loginQuery("123456", "123456");
			mainFrame = new MainFrame(manager);
		}
		mainFrame.setVisible(true);
		close();
	}
	
	/** 关闭资源 */
	public void close() {
		loginFrame.dispose();
	}

}
