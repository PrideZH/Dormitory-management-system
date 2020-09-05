package com.pengfu.view.component;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pengfu.util.Constant;

public class InfoBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int HEIGHT = 32;
	
	private JTextField textField;

	public InfoBar(String title, String text, int width) {
		setPreferredSize(new Dimension(width + 32, HEIGHT + 16));
		setBackground(Constant.PAGE_COLOR);
		
		AppLabel titleLab = new AppLabel(title);
		titleLab.setFont(new Font("宋体", Font.BOLD, 24));
		titleLab.setBackground(Constant.BG_COLOR);
		titleLab.setPreferredSize(new Dimension(32 * title.length(), HEIGHT));
		add(titleLab);
		
		textField = new JTextField(text);
		textField.setBackground(Constant.PAGE_COLOR);
		textField.setForeground(Constant.PAGE_FONT_COLOR);
		textField.setFont(new Font("宋体", Font.BOLD, 24));
		textField.setPreferredSize(new Dimension(width - 32 * title.length(), HEIGHT));
		textField.setEnabled(false);
		textField.setDisabledTextColor(Constant.PAGE_FONT_COLOR);
		add(textField);
	}
	
	public InfoBar(String title, String text) {
		this(title, text, 456);
	}
	
	public void setText(String text) {
		textField.setText(text);
	}
	
	public String getText() {
		return textField.getText();
	}
	
}
