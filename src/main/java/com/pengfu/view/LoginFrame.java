package com.pengfu.view;

import java.awt.Container;
import java.awt.Graphics;
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
import com.pengfu.util.StringUtil;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.LoginInputBox;

/**
 * 登录界面
* @author PrideZH
*/
@Component
@Lazy
public class LoginFrame extends BaseFrame {
	
	private static final long serialVersionUID = 1L;
	
	private LoginInputBox usernameInputBox;
	private LoginInputBox passwordInputBox;
	private JRadioButton studentRBtn;
	private JRadioButton adminRBtn;
	
	private AppButton loginBtn;
	// 是否有加载动画
	private boolean loadFlag = false;
	
	// 控制层对象
	@Autowired
	private AppControl control;
	
	public LoginFrame() {
		setTitle("登录");
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
		usernameInputBox = new LoginInputBox("username", 16, true);
		usernameInputBox.setLocation(90, 50);
		contentPane.add(usernameInputBox);
		// 密码输入框
		passwordInputBox = new LoginInputBox("password", 16, false);
		passwordInputBox.setLocation(90, 140);
		contentPane.add(passwordInputBox);
		
		// 登录人员单选按钮
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
		
		// 登录按钮
		loginBtn = new AppButton("登录", 96);
		loginBtn.setLocation(380, 240);
		contentPane.add(loginBtn);

		// 添加监听器
		// 登陆按钮
		loginBtn.addActionListener(e -> login());
		// 回车登录
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
	
	/** 登陆 */
	private void login() {
		String username = usernameInputBox.getText();
		String password = passwordInputBox.getText();
		
		usernameInputBox.setTipsText("");
		passwordInputBox.setTipsText("");
		
		if(StringUtil.isEmpty(username)) {
			usernameInputBox.setTipsText("请输入用户名");
			return;
		}else if(StringUtil.isEmpty(password)) {
			passwordInputBox.setTipsText("请输入密码");
			return;
		}
		
		if(studentRBtn.isSelected()) {
			control.Logint(Role.STUDENT, username, password);
		}else if(adminRBtn.isSelected()) {
			control.Logint(Role.GENERAL_ADMIN, username, password);
		}
	}
	
	/** 启动登陆按钮加载动画 */
	public void loadAnimation() {
		loadFlag = true;
		new Thread(() -> {
			// 设置加载动画位置大小
			int w = loginBtn.getHeight() * 5 / 6;
			int h = loginBtn.getHeight() * 5 / 6;
			int x = (loginBtn.getWidth() - w) / 2;
			int y = (loginBtn.getHeight() - h) / 2;
			
			// 设置按钮状态
			loginBtn.setEntered(true);
			loginBtn.setEnabled(false);
			
			// 绘制加载动画
			Graphics g = loginBtn.getGraphics();
			int a = 0;
			while(loadFlag) {
				loginBtn.paint(g);
				g.setColor(Constant.BTN_ENTERED_COLOR);
				g.fillRect(x, y, w, h);
				g.setColor(Constant.BTN_FONT_COLOR);
				g.fillArc(x, y, w, h, a, 240);
				g.setColor(Constant.BTN_ENTERED_COLOR);
				g.fillArc(x + 2, y + 2, w - 4, h - 4, a, 240);
				a += 50;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}
	
	/** 关闭登陆按钮加载动画 */
	public void stopLoadAnimation() {
		loadFlag = false;
		loginBtn.updateUI();
		loginBtn.setEnabled(true);
	}

}

