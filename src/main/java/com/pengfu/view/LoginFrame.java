package com.pengfu.view;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.controller.AppControl;
import com.pengfu.model.Role;
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
	
	private InputBox usernameInputBox;
	private InputBox passwordInputBox;
	private JRadioButton studentRBtn;
	private JRadioButton adminRBtn;
	
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
		usernameInputBox = new InputBox("username", 16, true);
		usernameInputBox.setLocation(90, 50);
		contentPane.add(usernameInputBox);
		// 密码输入框
		passwordInputBox = new InputBox("password", 16, false);
		passwordInputBox.setLocation(90, 140);
		contentPane.add(passwordInputBox);
		
		// 登陆人员单选按钮
		studentRBtn = new JRadioButton("学生");
		studentRBtn.setForeground(Constant.PAGE_FONT_COLOR);
		studentRBtn.setBackground(Constant.BG_COLOR);
		studentRBtn.setSelected(true);
		studentRBtn.setBounds(90, 240, 64, 32);
		contentPane.add(studentRBtn);
		adminRBtn = new JRadioButton("管理员");
		adminRBtn.setForeground(Constant.PAGE_FONT_COLOR);
		adminRBtn.setBackground(Constant.BG_COLOR);
		adminRBtn.setBounds(190, 240, 64, 32);
		contentPane.add(adminRBtn);	
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(studentRBtn);
		buttonGroup.add(adminRBtn);
		
		// 记住密码
//		JRadioButton keepPassword = new JRadioButton("记住密码");
//		keepPassword.setBackground(ColorConfig.BG_COLOR);
//		keepPassword.setBounds(380, 220, 128, 32);
//		contentPane.add(keepPassword);	
		
		// 找回密码
		
		// 登陆按钮
		AppButton LoginBtn = new AppButton("登陆", 96);
		LoginBtn.setLocation(380, 240);
		contentPane.add(LoginBtn);

		// 添加监听器
		// 登陆按钮
		LoginBtn.addActionListener(e -> login());
		// 回车登陆
		KeyAdapter ka = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		};
		usernameInputBox.addKeyListener(ka);
		passwordInputBox.addKeyListener(ka);
	}
	
	private void login() {
		if(studentRBtn.isSelected()) {
			control.Logint(Role.STUDENT, usernameInputBox.getText(), passwordInputBox.getText());
		}else if(adminRBtn.isSelected()) {
			control.Logint(Role.GENERAL_ADMIN, usernameInputBox.getText(), passwordInputBox.getText());
		}
	}

}

