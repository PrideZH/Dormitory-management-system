package com.pengfu.view.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

/**
 * 侧边栏
 * @author PrideZH
 */
public class Sidebar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// 父按钮列表
	private List<SidebarBtn> sideBtns = new ArrayList<>();
	// 所有父按钮为一组
	private ButtonGroup buttonGroup = new ButtonGroup();

	public Sidebar() {
		setBackground(new Color(57, 62, 75));
	}
	
	public void addBtn(SidebarBtn sideBtn) {
		buttonGroup.add(sideBtn);
		sideBtns.add(sideBtn);
		add(sideBtn);
	}

	public List<SidebarBtn> getSideBtns() {
		return sideBtns;
	}

}
