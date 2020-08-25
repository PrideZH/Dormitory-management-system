package com.pengfu.view.component;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pengfu.util.Constant;

/** 添加界面输入框 */
public class TitleInputBox extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;

	public TitleInputBox(String title) {
		setBackground(Constant.BG_COLOR);
		setPreferredSize(new Dimension(320, 40));
		
		JLabel titleLab = new JLabel(title);
		titleLab.setPreferredSize(new Dimension(70, 32));
		add(titleLab);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 32));
		add(textField);
	}
	
	/** 获取输入框内容 */
	public String getText() {
		return textField.getText();
	}
	
	/** 设置输入框内容 */
	public void setText(String text) {
		textField.setText(text);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		textField.setEnabled(enabled);
	}
	
}
