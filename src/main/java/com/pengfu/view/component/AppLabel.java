package com.pengfu.view.component;

import javax.swing.Icon;
import javax.swing.JLabel;

import com.pengfu.util.Constant;

/**
 * 统一标签字体颜色
 * @author PrideZH
 */
public class AppLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	
	public AppLabel() {
		this("");
	}
	
	public AppLabel(String text) {
		super(text);
		setForeground(Constant.PAGE_FONT_COLOR);
	}

	public AppLabel(Icon icon) {
		super(icon);
		setForeground(Constant.PAGE_FONT_COLOR);
	}
	
}
