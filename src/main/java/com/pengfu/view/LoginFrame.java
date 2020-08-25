package com.pengfu.view;

import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.controller.AppControl;
import com.pengfu.util.Constant;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.InputBox;

/**
 * 登陆界面
* @author PrideZH
*/
@Component
@Lazy
public class LoginFrame extends BaseFrame {
	
	private static final long serialVersionUID = 1L;
	
	// 控制层对象
	@Autowired
	private AppControl control;
	
	public LoginFrame() {
		setText("登陆");
		setSize(570, 380);
		setLocationRelativeTo(null);
		// 设置最大化按钮不可用
		setMaximizeEnabled(false);

		initComponents();
	}
	
	/** 初始化组件 */
	private void initComponents() {
		Container contentPane = getContentPane();
		contentPane.setBackground(Constant.BG_COLOR);
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
		studentRBtn.setBackground(Constant.BG_COLOR);
		studentRBtn.setSelected(true);
		studentRBtn.setBounds(90, 240, 64, 32);
		contentPane.add(studentRBtn);
		JRadioButton manageRBtn = new JRadioButton("管理员");
		manageRBtn.setBackground(Constant.BG_COLOR);
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
		AppButton LoginBtn = new AppButton("登陆", 96);
		LoginBtn.setLocation(380, 240);
		contentPane.add(LoginBtn);

		// 添加监听器
		// 登陆按钮
		LoginBtn.addActionListener((e) -> {
			control.Logint(studentRBtn.isSelected(), UsernameInputBox.getText(), PasswordInputBox.getText());
		});
	}

}

