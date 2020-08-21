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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.pengfu.model.Role;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.component.Sidebar;
import com.pengfu.view.component.SidebarBtn;
import com.pengfu.view.page.BasePage;

/**
 * 主界面
* @author PrideZH
*/
@Controller
@Lazy
public class MainFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int SIDEBAR_WIDTH = 200;
	
	private Container contentPane;
	private JPanel pagePanel;
	private CardLayout cardLayout;
	
	// 路径显示
	private String parentPath;
	private JLabel pathLabel; 
	
	// 用户权限
	private Role role;

	private MainFrame() {
		setText("宿舍信息管理系统");
		setSize(1600, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		role = Role.getRole();
		
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
		
		pathLabel = new JLabel("首页");
		pathLabel.setFont(new Font("宋体", Font.BOLD, 16));
		pathPanel.add(pathLabel);
		
		// 页面显示		
		pagePanel = new JPanel();
		pagePanel.setBackground(ConstantConfig.BG_COLOR);
		pagePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		contentBar.add(pagePanel, BorderLayout.CENTER);
		
		// 设置卡片布局
		cardLayout = new CardLayout();
		pagePanel.setLayout(cardLayout);
		// 首页
		pagePanel.add(SpringContextUtils.getBean("homePage", BasePage.class)); 
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
		dormitoryInfoBtn.setName("dormInfoPage");
		
		SidebarBtn personalInfoBtn = new SidebarBtn("", "个人信息", SIDEBAR_WIDTH);
	
		// 楼宇管理
		SidebarBtn buildingBtn = new SidebarBtn("", "楼宇管理", SIDEBAR_WIDTH);	
		
		SidebarBtn buildingListBtn = new SidebarBtn("", "楼宇列表", SIDEBAR_WIDTH);
		buildingListBtn.setName("buildingListPage");
		
		// 宿舍管理
		SidebarBtn dormitoryBtn = new SidebarBtn("", "宿舍管理", SIDEBAR_WIDTH);	
		
		SidebarBtn dormitoryListBtn = new SidebarBtn("", "宿舍列表", SIDEBAR_WIDTH);
		dormitoryListBtn.setName("dormListPage");
		
		// 用户权限管理
		SidebarBtn managerBtn = new SidebarBtn("", "用户权限管理", SIDEBAR_WIDTH);	
		
		SidebarBtn managerListBtn = new SidebarBtn("", "用户权限列表", SIDEBAR_WIDTH);
		managerListBtn.setName("adminListPage");
		
		// 学生管理
		SidebarBtn studentBtn = new SidebarBtn("", "学生管理", SIDEBAR_WIDTH);
		
		SidebarBtn studentListBtn = new SidebarBtn("", "学生列表", SIDEBAR_WIDTH);
		studentListBtn.setName("studentListPage");
		
		// 后勤服务
		SidebarBtn logisticsBtn = new SidebarBtn("", "后勤服务", SIDEBAR_WIDTH);
		
		SidebarBtn damageWarrantyBtn = new SidebarBtn("", "损坏保修", SIDEBAR_WIDTH);
		damageWarrantyBtn.setName("damageWarrantyPage");
		
		SidebarBtn damageListBtn = new SidebarBtn("", "损坏列表", SIDEBAR_WIDTH);
		damageListBtn.setName("damageListPage");
		
		// 生活服务
		SidebarBtn lifeBtn = new SidebarBtn("", "生活服务", SIDEBAR_WIDTH);
		
		SidebarBtn electricityBtn = new SidebarBtn("", "电费充值", SIDEBAR_WIDTH);
		electricityBtn.setName("electricityPage");
		
		SidebarBtn networktBtn = new SidebarBtn("", "校园网充值", SIDEBAR_WIDTH);
		networktBtn.setName("networkPage");
		
		SidebarBtn cardBtn = new SidebarBtn("", "校园一卡通", SIDEBAR_WIDTH);
		cardBtn.setName("cardPage");
		
		// 根据权限添加按钮
		switch(role) {
		case Student:
			sidebar.addBtn(personalBtn);
			personalBtn.addSideBtnItem(dormitoryInfoBtn);
			personalBtn.addSideBtnItem(personalInfoBtn);
			personalInfoBtn.setName("studentProfilePage");
			
			sidebar.addBtn(logisticsBtn);
			logisticsBtn.addSideBtnItem(damageWarrantyBtn);
			
			sidebar.addBtn(lifeBtn);
			lifeBtn.addSideBtnItem(electricityBtn);
			lifeBtn.addSideBtnItem(networktBtn);
			lifeBtn.addSideBtnItem(cardBtn);
			break;
		case GeneralManage:
			sidebar.addBtn(personalBtn);
			personalBtn.addSideBtnItem(personalInfoBtn);
			personalInfoBtn.setName("adminProfilePage");
			
			sidebar.addBtn(buildingBtn);
			buildingBtn.addSideBtnItem(buildingListBtn);
			
			sidebar.addBtn(dormitoryBtn);
			dormitoryBtn.addSideBtnItem(dormitoryListBtn);
			
			sidebar.addBtn(studentBtn);
			studentBtn.addSideBtnItem(studentListBtn);
			
			sidebar.addBtn(logisticsBtn);
			logisticsBtn.addSideBtnItem(damageListBtn);
			break;
		case SuperManage:
			sidebar.addBtn(personalBtn);
			personalBtn.addSideBtnItem(personalInfoBtn);
			personalInfoBtn.setName("adminProfilePage");
			
			sidebar.addBtn(buildingBtn);
			buildingBtn.addSideBtnItem(buildingListBtn);
			
			sidebar.addBtn(dormitoryBtn);
			dormitoryBtn.addSideBtnItem(dormitoryListBtn);
			
			sidebar.addBtn(managerBtn);
			managerBtn.addSideBtnItem(managerListBtn);
			
			sidebar.addBtn(studentBtn);
			studentBtn.addSideBtnItem(studentListBtn);
			
			sidebar.addBtn(logisticsBtn);
			logisticsBtn.addSideBtnItem(damageListBtn);
			break;
		default:
			break;
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
				// 保存父路径
				parentPath = btn.getText();
			}
		};
		
		// 子级按钮监听器
		ActionListener al2 = new ActionListener() {

			/** 实现页面切换 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SidebarBtn btn = (SidebarBtn) e.getSource();
					BasePage page = SpringContextUtils.getBean(btn.getName(), BasePage.class);
					pagePanel.add(btn.getName(), page);
					cardLayout.show(pagePanel, btn.getName());
					// 显示路径
					pathLabel.setText(parentPath + " > " + btn.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "功能尚未实现，敬请期待!");
				}
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
