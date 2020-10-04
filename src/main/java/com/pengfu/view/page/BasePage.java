package com.pengfu.view.page;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.pengfu.util.Constant;

/**
 * 页面基类
 * @author PrideZH
 */
public abstract class BasePage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// 内容框
	protected JPanel contxtPane; 

	public BasePage() {
		setBackground(Constant.BG_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// 创建内容框
		contxtPane = new JPanel();
		contxtPane.setBackground(Constant.PAGE_COLOR);
		// 设置边框
		contxtPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Constant.PAGE_BORDER_COLOR, 1), //外边框
				BorderFactory.createEmptyBorder(16, 16, 16, 16))); // 内边框
		add(contxtPane);
	}
	
	protected abstract void initComponents();
}
