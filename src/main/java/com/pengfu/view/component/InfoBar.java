package com.pengfu.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pengfu.util.ConstantConfig;

public class InfoBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int HEIGHT = 32;
	private static final int WIDTH = 456;
	
	private JLabel titleLab;

	public InfoBar(String title, String text) {
		setPreferredSize(new Dimension(WIDTH + 32, HEIGHT + 16));
		setBackground(ConstantConfig.PAGE_COLOR);
		
		titleLab = new JLabel(title);
		titleLab.setFont(new Font("宋体", Font.BOLD, 24));
		titleLab.setBackground(ConstantConfig.BG_COLOR);
		titleLab.setPreferredSize(new Dimension(32 * title.length(), HEIGHT));
		add(titleLab);
		
		JTextField textField = new JTextField(text);
		textField.setFont(new Font("宋体", Font.BOLD, 24));
		textField.setPreferredSize(new Dimension(WIDTH - 32 * title.length(), HEIGHT));
		textField.setEnabled(false);
		textField.setDisabledTextColor(Color.BLACK);
		add(textField);
	}
	
	public void setText(String text) {
		titleLab.setText(text);
	}
	
	public String getText() {
		return titleLab.getText();
	}
	
}
