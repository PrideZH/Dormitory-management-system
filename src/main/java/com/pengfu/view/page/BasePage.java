package com.pengfu.view.page;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.pengfu.util.ConstantConfig;

public abstract class BasePage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// 内容框
	protected JPanel contxtPane; 

	public BasePage() {
		setBackground(ConstantConfig.BG_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// 创建内容框
		contxtPane = new JPanel();
		contxtPane.setBackground(ConstantConfig.PAGE_COLOR);
		// 设置边框
		contxtPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(65, 113, 156), 1), //外边框
				BorderFactory.createEmptyBorder(16, 16, 16, 16))); // 内边框
		add(contxtPane);
	}
	
	protected abstract void initComponents();
}
