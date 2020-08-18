package com.pengfu.view.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class Sidebar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private List<SidebarBtn> sideBtns = new ArrayList<>();
	private ButtonGroup buttonGroup = new ButtonGroup();

	public Sidebar() {
		setBackground(new Color(57, 62, 75));
		
		initComponents();
	}
	
	private void initComponents() {
		
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
