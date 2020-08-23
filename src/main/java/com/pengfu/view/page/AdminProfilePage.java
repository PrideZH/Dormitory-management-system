package com.pengfu.view.page;

import java.awt.FlowLayout;

import javax.swing.JButton;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Admin;
import com.pengfu.model.Role;
import com.pengfu.view.component.InfoBar;

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
		InfoBar nameInfoBar = new InfoBar("姓名", admin.getName());
		InfoBar usernameInfoBar = new InfoBar("账号", admin.getUsername());
		InfoBar phoneInfoBar = new InfoBar("联系电话", admin.getPhone());
		InfoBar roleInfoBar = new InfoBar("权限", 1 == admin.getRole() ? "超级管理员" : "普通管理员");
		InfoBar buildingInfoBar = new InfoBar("管理楼层", admin.getBids().toString());


		contxtPane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 32));
		contxtPane.add(nameInfoBar);
		contxtPane.add(usernameInfoBar);
		contxtPane.add(phoneInfoBar);
		contxtPane.add(roleInfoBar);
		contxtPane.add(buildingInfoBar);
		
		JButton setPasswordBtn = new JButton("修改密码");
		contxtPane.add(setPasswordBtn);
	}

}
