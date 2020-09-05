package com.pengfu.view.component;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	// 实现下拉按钮监听器
	private ActionListener al;
	
	// 父按钮列表
	private List<SidebarBtn> sideBtns = new ArrayList<>();
	// 所有父按钮为一组
	private ButtonGroup buttonGroup = new ButtonGroup();
	// 侧边栏状态
	boolean isOpen = true;
	
	// 父路径
	private String parentPath;

	public Sidebar() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setBackground(Constant.SIDEBAR_COLOR);
		
		// 菜单搜索展开按钮
		SidebarBtn menuBtn = new SidebarBtn(Constant.MENU_IMG, "MENU", Constant.SIDEBAR_WIDTH);
		addBtn(menuBtn);
		
		menuBtn.addActionListener(e -> {
			effect(isOpen);
			isOpen = !isOpen;
			for(SidebarBtn sideBtn : sideBtns) {
				for(SidebarBtn item : sideBtn.getItems()) {
					item.changeForOpen(isOpen);
				}
			}
			updateUI();
		});
		
		// 父级按钮监听
		al = new ActionListener() {
			/** 实现下拉按钮 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获得点击的按钮
				SidebarBtn btn = (SidebarBtn) e.getSource();
				// 无子按钮时不做处理
				List<SidebarBtn> items = btn.getItems();
				if(items.isEmpty()) {
					return;
				}
				// 移除侧边栏所有子按钮
				List<SidebarBtn> sideBtns = getSideBtns();
				for(SidebarBtn sideBtn : sideBtns) {
					for(SidebarBtn item : sideBtn.getItems()) {
						remove(item);
					}
				}
				// 获得该父按钮下标
				int index = 0;
				for (int i = 0; i < sideBtns.size(); ++i){
				     if(sideBtns.get(i).equals(e.getSource())){
				    	 index = i;
				     }
				 }
				// 添加该父按钮的所有子按钮
				for(SidebarBtn item : items) {
					add(item, index + 1);
				}
				updateUI();
				// 保存父路径
				parentPath = btn.getText();
			}
		};
	}
	
	/** 收缩展开动画效果 */
	private void effect(boolean isOpen) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(isOpen) {	
					for(int i = Constant.SIDEBAR_WIDTH; i >= 40; i -= 20) {
						moveEffect(i);
					}	
				}else {
					for(int i = 40; i <= Constant.SIDEBAR_WIDTH; i += 20) {
						moveEffect(i);
					}	
				}
			}
		}).start();
	}
	
	/** 移动效果 */
	private void moveEffect(int w) {
		setPreferredSize(new Dimension(w, 0));
		updateUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** 为所有子按钮设置监听器 */
	public void setActionListener(ActionListener al){
		for(SidebarBtn sideBtn : sideBtns) {
			for(SidebarBtn item : sideBtn.getItems()) {
				item.addActionListener(al);
			}
		}
	}
	
	/** 添加父按钮 */
	public void addBtn(SidebarBtn sideBtn) {
		buttonGroup.add(sideBtn);
		sideBtns.add(sideBtn);
		add(sideBtn);
		sideBtn.addActionListener(al);
	}

	public List<SidebarBtn> getSideBtns() {
		return sideBtns;
	}
	
	public String getParentPath() {
		return parentPath;
	}

}
