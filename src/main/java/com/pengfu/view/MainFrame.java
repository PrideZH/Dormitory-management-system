package com.pengfu.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.pengfu.controller.AppControl;
import com.pengfu.model.Role;
import com.pengfu.util.Constant;
import com.pengfu.util.SidebarBuilder;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.component.AppButton;
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

	private Container contentPane;
	private JPanel pagePanel;
	private CardLayout cardLayout;
	
	private Sidebar sidebar; // 侧边栏
	
	// 侧边栏路径显示
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
		
		// 顶部栏
		JPanel upPanel = new JPanel();
		upPanel.setBackground(Constant.BG_COLOR);
		upPanel.setPreferredSize(new Dimension(0, 48));
		contentBar.add(upPanel, BorderLayout.NORTH);
		upPanel.setLayout(new BoxLayout(upPanel, BoxLayout.X_AXIS));
		upPanel.add(Box.createHorizontalStrut(24));
		// 路径显示
		pathLabel = new JLabel("首页");
		pathLabel.setFont(new Font("宋体", Font.BOLD, 16));
		pathLabel.setForeground(Constant.PAGE_FONT_COLOR);
		upPanel.add(pathLabel);
		upPanel.add(Box.createHorizontalGlue());
		// 人物信息
		JLabel roleLbl = new JLabel("欢迎您！" + role.getName() + "，"
			+ (Role.STUDENT == role ? Role.getStudent().getName() : Role.getAdmin().getName()));
		roleLbl.setForeground(Constant.PAGE_FONT_COLOR);
		upPanel.add(roleLbl);
		upPanel.add(Box.createHorizontalStrut(24));
		// 退出登陆按钮
		AppButton appButton = new AppButton("退出登陆");
		upPanel.add(appButton);
		upPanel.add(Box.createHorizontalStrut(32));
		appButton.addActionListener(e -> SpringContextUtils.getBean(AppControl.class).logOut());
		
		// 页面显示		
		pagePanel = new JPanel();
		pagePanel.setBackground(Constant.BG_COLOR);
		pagePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		contentBar.add(pagePanel, BorderLayout.CENTER);
		// 设置卡片布局
		cardLayout = new CardLayout();
		pagePanel.setLayout(cardLayout);
		// 显示首页
		goToPage("homePage");
	}
	
	/** 初始化侧边 */
	private void initSidebar() {
		// 侧边栏
		sidebar = SpringContextUtils.getBean(SidebarBuilder.class).build(role.getPermissionList());
		contentPane.add(sidebar, BorderLayout.WEST);

		// 子按钮监听器
		ActionListener al = new ActionListener() {

			/** 实现页面切换 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SidebarBtn btn = (SidebarBtn) e.getSource();
					// 页面跳转
					goToPage(btn.getPageName());
					// 显示路径
					pathLabel.setText(sidebar.getParentPath() + " > " + btn.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "功能尚未实现，敬请期待!");
				}
			}
			
		};
		
		// 为所有侧边栏按钮添加监听器
		sidebar.setActionListener(al);
	}
	
	/** 跳转页面 */
	public void goToPage(String name) {
		BasePage page = SpringContextUtils.getBean(name, BasePage.class);
		pagePanel.add(name, page);
		cardLayout.show(pagePanel, name);
	}
	
	public void updateUI() {
		sidebar.updateUI();
	}
}
