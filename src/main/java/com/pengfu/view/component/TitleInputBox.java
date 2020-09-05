package com.pengfu.view.component;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pengfu.util.Constant;

/** 添加界面输入框 */
public class TitleInputBox extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;

	public TitleInputBox(String title, int titleSize, int textSize) {
		setBackground(Constant.BG_COLOR);
		setPreferredSize(new Dimension(titleSize + textSize + 20, 40));
		
		AppLabel titleLab = new AppLabel(title);
		titleLab.setPreferredSize(new Dimension(titleSize, 32));
		add(titleLab);
		
		textField = new JTextField();
		textField.setForeground(Constant.PAGE_FONT_COLOR);
		textField.setPreferredSize(new Dimension(textSize, 32));
		textField.setBackground(Constant.PAGE_COLOR);
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
