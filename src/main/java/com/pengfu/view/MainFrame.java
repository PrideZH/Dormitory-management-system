package com.pengfu.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.pengfu.entity.Manager;
import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.util.ColorConfig;
import com.pengfu.util.PageFactory;
import com.pengfu.view.component.Sidebar;
import com.pengfu.view.component.SidebarBtn;
import com.pengfu.view.page.BasePanel;
import com.pengfu.view.page.HomePanel;

public class MainFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int SIDEBAR_WIDTH = 200;
	
	private Container contentPane;
	private JPanel pagePanel;
	private CardLayout cardLayout;
	
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
			role = Role.GeneralManage;
		}else if(manager.getRole() == 1) {
			role = Role.SuperManage;
		}
		initComponents();
	}

	private void initComponents() {
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		// 侧边栏
		initSidebar();
	
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
		pagePanel = new JPanel();
		pagePanel.setBackground(ColorConfig.BG_COLOR);
		pagePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		contentBar.add(pagePanel, BorderLayout.CENTER);
		
		// 设置卡片布局
		cardLayout = new CardLayout();
		pagePanel.setLayout(cardLayout);
		pagePanel.add(new HomePanel()); // 首页
	}
	
	/** 初始化侧边 */
	private void initSidebar() {
		Sidebar sidebar = new Sidebar();
		sidebar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0));
		contentPane.add(sidebar, BorderLayout.WEST);
		
		// 创建按钮
		// 个人中心
		SidebarBtn personalBtn = new SidebarBtn("", "个人中心", SIDEBAR_WIDTH);	
		
		SidebarBtn dormitoryInfoBtn = new SidebarBtn("", "宿舍信息", SIDEBAR_WIDTH);
		personalBtn.addSideBtnItem(dormitoryInfoBtn);
		dormitoryInfoBtn.setName("DormitoryInfoPanel");
		
		SidebarBtn personalInfoBtn = new SidebarBtn("", "个人信息", SIDEBAR_WIDTH);
		personalBtn.addSideBtnItem(personalInfoBtn);
		personalInfoBtn.setName("PersonalInfoPanel");
	
		// 楼宇管理
		SidebarBtn buildingBtn = new SidebarBtn("", "楼宇管理", SIDEBAR_WIDTH);	
		
		SidebarBtn buildingListBtn = new SidebarBtn("", "楼宇列表", SIDEBAR_WIDTH);
		buildingBtn.addSideBtnItem(buildingListBtn);
		buildingListBtn.setName("BuildingListPanel");
		
		// 宿舍管理
		SidebarBtn dormitoryBtn = new SidebarBtn("", "宿舍管理", SIDEBAR_WIDTH);	
		
		SidebarBtn dormitoryListBtn = new SidebarBtn("", "宿舍列表", SIDEBAR_WIDTH);
		dormitoryBtn.addSideBtnItem(dormitoryListBtn);
		dormitoryListBtn.setName("DormitoryListPanel");
		
		// 用户权限管理
		SidebarBtn managerBtn = new SidebarBtn("", "用户权限管理", SIDEBAR_WIDTH);	
		
		SidebarBtn managerListBtn = new SidebarBtn("", "用户权限列表", SIDEBAR_WIDTH);
		managerBtn.addSideBtnItem(managerListBtn);
		managerListBtn.setName("ManagerListPanel");
		
		// 学生管理
		SidebarBtn studentBtn = new SidebarBtn("", "学生管理", SIDEBAR_WIDTH);
		
		SidebarBtn studentListBtn = new SidebarBtn("", "学生列表", SIDEBAR_WIDTH);
		studentBtn.addSideBtnItem(studentListBtn);
		studentListBtn.setName("StudentListPanel");
		
//		SidebarBtn logisticsBtn = new SidebarBtn("", "后勤服务", SIDEBAR_WIDTH);
//		SidebarBtn lifeBtn = new SidebarBtn("", "生活服务", SIDEBAR_WIDTH);
		
		// 根据权限添加按钮
		if(role == Role.Student) {
			sidebar.addBtn(personalBtn);
		}else if(role == Role.GeneralManage) {
			sidebar.addBtn(personalBtn);
			sidebar.addBtn(buildingBtn);
			sidebar.addBtn(dormitoryBtn);
			sidebar.addBtn(studentBtn);
		}else if(role == Role.SuperManage) {
			sidebar.addBtn(personalBtn);
			sidebar.addBtn(buildingBtn);
			sidebar.addBtn(dormitoryBtn);
			sidebar.addBtn(managerBtn);
			sidebar.addBtn(studentBtn);
		}

		// 父级按钮监听器
		ActionListener al = new ActionListener() {
			
			/** 实现下拉按钮 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// 移除侧边栏所有组件
				sidebar.removeAll();
				// 添加所有父按钮
				List<SidebarBtn> sideBtns = sidebar.getSideBtns();
				for(SidebarBtn sideBtn : sideBtns) {
					sidebar.add(sideBtn);
				}
				// 获得点击的父按钮
				SidebarBtn btn = (SidebarBtn) e.getSource();
				// 获得该父按钮下标
				int index = 0;
				for (int i = 0; i < sideBtns.size(); ++i){
				     if(sideBtns.get(i).equals(e.getSource())){
				    	 index = i;
				     }
				 }
				// 添加该父按钮的所有子按钮
				List<SidebarBtn> items = btn.getItems();
				for(SidebarBtn item : items) {
					sidebar.add(item, index + 1);
				}
				sidebar.updateUI();
			}
		};
		
		// 子级按钮监听器
		ActionListener al2 = new ActionListener() {

			/** 实现页面切换 */
			@Override
			public void actionPerformed(ActionEvent e) {
				SidebarBtn btn = (SidebarBtn) e.getSource();
				BasePanel page = PageFactory.getPage(btn.getName());
				if(page == null) {
					JOptionPane.showMessageDialog(null, "功能尚未实现，敬请期待!");
					return;
				}
				pagePanel.add(btn.getName(), page);
				cardLayout.show(pagePanel, btn.getName());
			}
		
		};
		
		// 为所有侧边栏按钮添加监听器
		List<SidebarBtn> sideBtns = sidebar.getSideBtns();
		for(SidebarBtn sideBtn : sideBtns) {
			sideBtn.addActionListener(al);
			for(SidebarBtn item : sideBtn.getItems()) {
				item.addActionListener(al2);
			}
		}

	}
	
}
