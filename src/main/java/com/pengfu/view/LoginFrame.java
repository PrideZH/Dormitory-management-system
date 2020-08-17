package com.pengfu.view;

import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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

		// 账号输入框
		InputBox UsernameInputBox = new InputBox("username", 16, true);
		UsernameInputBox.setLocation(90, 50);
		contentPane.add(UsernameInputBox);
		// 密码输入框
		InputBox PasswordInputBox = new InputBox("password", 16, false);
		PasswordInputBox.setLocation(90, 140);
		contentPane.add(PasswordInputBox);
		
		// 登陆人员单选按钮
		JRadioButton studentRBtn = new JRadioButton("学生");
		studentRBtn.setBackground(ColorConfig.BG_COLOR);
		studentRBtn.setSelected(true);
		studentRBtn.setBounds(90, 240, 64, 32);
		contentPane.add(studentRBtn);
		JRadioButton manageRBtn = new JRadioButton("管理员");
		manageRBtn.setBackground(ColorConfig.BG_COLOR);
		manageRBtn.setBounds(190, 240, 64, 32);
		contentPane.add(manageRBtn);	
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(studentRBtn);
		buttonGroup.add(manageRBtn);
		
		// 记住密码
//		JRadioButton keepPassword = new JRadioButton("记住密码");
//		keepPassword.setBackground(ColorConfig.BG_COLOR);
//		keepPassword.setBounds(380, 220, 128, 32);
//		contentPane.add(keepPassword);	
		
		// 登陆按钮
		JButton LoginBtn = new JButton("登陆");
		LoginBtn.setBounds(380, 240, 96, 32);
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

