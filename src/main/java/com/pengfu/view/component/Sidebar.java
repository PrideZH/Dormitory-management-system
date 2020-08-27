package com.pengfu.view.component;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import com.pengfu.util.Constant;

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
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setBackground(Constant.SIDEBAR_COLOR);
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
