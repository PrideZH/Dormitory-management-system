package com.pengfu.view;

import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import com.pengfu.controller.LoginControl;
import com.pengfu.util.ColorConfig;
import com.pengfu.view.component.InputBox;

public class LoginFrame extends BaseFrame {
	
	private static final long serialVersionUID = 1L;
	
	private LoginControl control;
	
	public LoginFrame() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		// 设置最大化按钮不可用
		setMaximizeEnabled(false);
		
		setText("登陆");
		
		// 创建控制器
		control = new LoginControl(this);
		
		initComponents();
	}
	
	private void initComponents() {
		Container contentPane = getContentPane();
		contentPane.setBackground(ColorConfig.BG_COLOR);
		contentPane.setLayout(null);
		
		// 页面标题
		JLabel Label0 = new JLabel("宿舍信息管理系统");
		Label0.setBounds(50, 10, 64, 32);
		contentPane.add(Label0);
		
		JLabel Label1 = new JLabel("登陆");
		Label1.setBounds(100, 20, 64, 32);
		contentPane.add(Label1);
		
		InputBox UsernameInputBox = new InputBox("username", 8);
		UsernameInputBox.setLocation(100, 60);
		contentPane.add(UsernameInputBox);
		
		InputBox PasswordInputBox = new InputBox("password", 8);
		PasswordInputBox.setLocation(100, 150);
		contentPane.add(PasswordInputBox);
		
		// 登陆人员单选按钮
		JRadioButton studentRBtn = new JRadioButton("学生");
		studentRBtn.setSelected(true);
		studentRBtn.setBounds(100, 250, 64, 32);
		contentPane.add(studentRBtn);
		JRadioButton manageRBtn = new JRadioButton("管理员");
		manageRBtn.setBounds(200, 250, 64, 32);
		contentPane.add(manageRBtn);	
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(studentRBtn);
		buttonGroup.add(manageRBtn);
		
		JButton LoginBtn = new JButton("登陆");
		LoginBtn.setBounds(400, 300, 64, 32);
		contentPane.add(LoginBtn);
		
		// 添加监听器
		// 登陆按钮
		LoginBtn.addActionListener((e) -> {
			try {
				control.Logint(studentRBtn.isSelected(), UsernameInputBox.getText(), PasswordInputBox.getText());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		});
	}

}

