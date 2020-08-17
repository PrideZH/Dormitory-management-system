package com.pengfu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pengfu.entity.Manager;
import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.util.ColorConfig;
import com.pengfu.view.Panel.HomePanel;
import com.pengfu.view.component.Sidebar;

public class MainFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	
	// 用户权限
	private Role role;

	private MainFrame() {
		setSize(1600, 900);
		setLocationRelativeTo(null);
		setText("宿舍信息管理系统");
	}

	/** 学生登陆 */
	public MainFrame(Student student) {
		this();
		role = Role.Student;
		initComponents();
	}
	
	/** 管理员登陆 */
	public MainFrame(Manager manager) {
		this();
		if(manager.getRole() == 0) {
			role = Role.SuperManage;
		}else if(manager.getRole() == 1) {
			role = Role.GeneralManage;
		}
		initComponents();
	}

	private void initComponents() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		// 侧边栏
		Sidebar sidebar = new Sidebar(200, role);
		contentPane.add(sidebar, BorderLayout.WEST);
	
		// 内容框
		JPanel contentBar = new JPanel();
		contentPane.add(contentBar, BorderLayout.CENTER);
		contentBar.setLayout(new BorderLayout());
		
		// 路径显示
		JPanel pathPanel = new JPanel();
		pathPanel.setPreferredSize(new Dimension(0, 48));
		contentBar.add(pathPanel, BorderLayout.NORTH);
		pathPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 16));
		
		JLabel pathLabel = new JLabel("首页");
		pathLabel.setFont(new Font("宋体", Font.BOLD, 16));
		pathPanel.add(pathLabel);
		
		
		// 页面显示		
		JPanel pagePanel = new JPanel();
		pagePanel.setBackground(ColorConfig.BG_COLOR);
		pagePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(0, 20, 20, 20), // 外边框 空白边框
				BorderFactory.createLineBorder(new Color(65, 113, 156), 1))); // 内边框 蓝条
		contentBar.add(pagePanel, BorderLayout.CENTER);
		pagePanel.setLayout(new BorderLayout());
		
		// 首页
		HomePanel homePanel = new HomePanel();
		pagePanel.add(homePanel, BorderLayout.CENTER);
	}
	
}
