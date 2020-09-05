package com.pengfu.view.page;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Admin;
import com.pengfu.model.Role;
import com.pengfu.service.AdminService;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.StringUtil;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.InfoBar;
import com.pengfu.view.component.TitleInputBox;

/** 管理员个人信息 */
@Component
@Lazy
public class AdminProfilePage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private Admin admin;
	
	public AdminProfilePage() {
		admin = Role.getAdmin();
		
		initComponents();
	}

	@Override
	protected void initComponents() {
		contxtPane.setLayout(new BoxLayout(contxtPane, BoxLayout.Y_AXIS));
		
		// 信息栏
		JPanel infoPane = new JPanel();
		infoPane.setPreferredSize(new Dimension(0, 450));
		TitledBorder infoTitledBorder = BorderFactory.createTitledBorder("信息");
		infoTitledBorder.setTitleColor(Constant.PAGE_FONT_COLOR);
		infoPane.setBorder(infoTitledBorder);
		infoPane.setBackground(Constant.PAGE_COLOR);
		contxtPane.add(infoPane);
		
		InfoBar nameInfoBar = new InfoBar("姓名", admin.getName());
		InfoBar usernameInfoBar = new InfoBar("账号", admin.getUsername());
		InfoBar phoneInfoBar = new InfoBar("联系电话", admin.getPhone());
		InfoBar roleInfoBar = new InfoBar("权限", 1 == admin.getRole() ? "超级管理员" : "普通管理员");
		InfoBar buildingInfoBar = new InfoBar("管理楼层", admin.getBids().toString());

		infoPane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 32));
		infoPane.add(nameInfoBar);
		infoPane.add(usernameInfoBar);
		infoPane.add(phoneInfoBar);
		infoPane.add(roleInfoBar);
		infoPane.add(buildingInfoBar);
		
		contxtPane.add(Box.createVerticalStrut(32));
		
		// 修改密码
		JPanel passwordPane = new JPanel();
		passwordPane.setPreferredSize(new Dimension(0, 64));
		TitledBorder passwordTitledBorder = BorderFactory.createTitledBorder("修改密码");
		passwordTitledBorder.setTitleColor(Constant.PAGE_FONT_COLOR);
		passwordPane.setBorder(passwordTitledBorder);
		passwordPane.setBackground(Constant.PAGE_COLOR);
		contxtPane.add(passwordPane);
		
		TitleInputBox oldPassword = new TitleInputBox("原密码", 64, 128);
		oldPassword.setBackground(Constant.PAGE_COLOR);
		TitleInputBox newPassword1 = new TitleInputBox("新密码", 64, 128);
		newPassword1.setBackground(Constant.PAGE_COLOR);
		TitleInputBox newPassword2 = new TitleInputBox("确认密码", 64, 128);
		newPassword2.setBackground(Constant.PAGE_COLOR);
		
		passwordPane.add(oldPassword);
		passwordPane.add(newPassword1);
		passwordPane.add(newPassword2);		
		
		AppButton setPasswordBtn = new AppButton("修改密码");
		passwordPane.add(setPasswordBtn);
		
		setPasswordBtn.addActionListener(e -> {
			if(admin.getPassword().equals(oldPassword.getText())) {
				if(StringUtil.isEmpty(newPassword1.getText()) || StringUtil.isEmpty(newPassword2.getText())) {
					JOptionPane.showMessageDialog(null, "请输入新密码");
					return;
				} else if(newPassword1.getText().equals(newPassword2.getText())) {
					admin.setPassword(newPassword1.getText());
					try {
						SpringContextUtils.getBean(AdminService.class).update(admin);
						JOptionPane.showMessageDialog(null, "修改成功");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "密码不同");
				}
			}else {
				JOptionPane.showMessageDialog(null, "密码错误");
			}
			oldPassword.setText("");
			newPassword1.setText("");
			newPassword2.setText("");
		});
		
		contxtPane.add(Box.createVerticalGlue());
		
	}

}
