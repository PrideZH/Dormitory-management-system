package com.pengfu.view.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import com.pengfu.model.Role;

public class Sidebar extends JPanel {

	private static final long serialVersionUID = 1L;
	private int width;
	private Role role;

	public Sidebar(int width, Role role) {
		this.width = width;
		this.role = role;
		
		setBackground(new Color(57, 62, 75));
		setPreferredSize(new Dimension(width, 0));
		
		initComponents();
	}
	
	private void initComponents() {
		SidebarBtn personalBtn = new SidebarBtn("", "个人中心", width);	
		SidebarBtn buildingBtn = new SidebarBtn("", "楼宇管理", width);	
		SidebarBtn dormitoryBtn = new SidebarBtn("", "宿舍管理", width);	
		SidebarBtn managerBtn = new SidebarBtn("", "用户权限管理", width);	
		SidebarBtn studentBtn = new SidebarBtn("", "学生管理", width);
		SidebarBtn logisticsBtn = new SidebarBtn("", "后勤服务", width);
		SidebarBtn lifeBtn = new SidebarBtn("", "生活服务", width);
			
		if(role == Role.Student) {
			add(personalBtn);
			add(logisticsBtn);
			add(lifeBtn);
		}else if(role == Role.GeneralManage) {
			add(personalBtn);
			add(buildingBtn);
			add(dormitoryBtn);
			add(studentBtn);
			add(logisticsBtn);
			add(lifeBtn);
		}else if(role == Role.SuperManage) {
			add(personalBtn);
			add(buildingBtn);
			add(dormitoryBtn);
			add(managerBtn);
			add(studentBtn);
			add(logisticsBtn);
			add(lifeBtn);
		}
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(personalBtn);
		buttonGroup.add(buildingBtn);
		buttonGroup.add(dormitoryBtn);
		buttonGroup.add(managerBtn);
		buttonGroup.add(studentBtn);
		buttonGroup.add(logisticsBtn);
		buttonGroup.add(lifeBtn);
	}

}
