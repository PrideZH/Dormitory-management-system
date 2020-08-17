package com.pengfu.view.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public HomePanel() {
		setBackground(Color.WHITE);
		
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		
		// 标题栏
		JPanel tiltePanel = new JPanel();
		tiltePanel.setPreferredSize(new Dimension(0, 64));
		tiltePanel.setBackground(Color.WHITE);
		add(tiltePanel, BorderLayout.NORTH);
		
		JLabel jLabel = new JLabel("欢迎您使用宿舍信息管理系统");
		jLabel.setFont(new Font("宋体", Font.BOLD, 48));
		tiltePanel.add(jLabel);
	}

}
