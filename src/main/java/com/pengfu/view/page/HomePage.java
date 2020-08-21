package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.stereotype.Component;

/** 首页 */
@Component
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public HomePage() {
		setBorder(BorderFactory.createLineBorder(new Color(65, 113, 156), 1));
		
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		add(contxtPane);
		
		// 标题栏
		JPanel tiltePanel = new JPanel();
		tiltePanel.setPreferredSize(new Dimension(0, 64));
		tiltePanel.setBackground(Color.WHITE);
		contxtPane.add(tiltePanel, BorderLayout.NORTH);
		
		JLabel jLabel = new JLabel("欢迎您使用宿舍信息管理系统");
		jLabel.setFont(new Font("宋体", Font.BOLD, 48));
		tiltePanel.add(jLabel);
	}

}
