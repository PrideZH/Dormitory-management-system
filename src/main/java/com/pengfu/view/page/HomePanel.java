package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 首页 */
public class HomePanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	public HomePanel() {
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(new Color(65, 113, 156), 1));
		
		initComponents();
	}
	
	@Override
	protected void initComponents() {
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
