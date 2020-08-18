package com.pengfu.view.page;

import javax.swing.JPanel;

import com.pengfu.util.ColorConfig;

public abstract class BasePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BasePanel() {
		setBackground(ColorConfig.BG_COLOR);
	}
	
	protected abstract void initComponents();
}
