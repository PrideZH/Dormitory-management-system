package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Admin;
import com.pengfu.model.table.AdminTableModel;
import com.pengfu.service.AdminService;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.PopupFrame;
import com.pengfu.view.component.AppButton;

/**
 * 管理员信息页面
 * @author PrideZH
 */
@Component
@Lazy
public class AdminListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private AdminTableModel adminModel = new AdminTableModel();
	private JTable table;
	
	private AdminService adminService;
	
	@Autowired
	public AdminListPage(AdminService adminService) {
		this.adminService = adminService;
		adminModel.setList(adminService.getAll());
		
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(Constant.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		northPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 16, 5));
		// 操作按钮
		AppButton addBtn = new AppButton("添加", Constant.ADD_IMG);
		northPane.add(addBtn);
		AppButton setBtn = new AppButton("修改", Constant.SET_IMG);
		northPane.add(setBtn);
		AppButton deleteBtn = new AppButton("删除", Constant.DELETE_IMG);
		northPane.add(deleteBtn);
		AppButton updateBtn = new AppButton("刷新", Constant.UPDATE_IMG);
		northPane.add(updateBtn);
		
		// 楼宇信息列表
		table = SpringContextUtils.getBean(TableBuilder.class).build(adminModel);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(Constant.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);
		
		// 监听器 
		addBtn.addActionListener((e) ->{
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showAddPane("addAdmin");
			popupFrame.setVisible(true);
		});
		setBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			// 获得欲修改管理员信息
			Admin admin = adminModel.get(row);
			// 显示修改信息面板
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showSetPane("setAdmin", admin);
			popupFrame.setVisible(true);
		});
		deleteBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			if(JOptionPane.showConfirmDialog(null, "确定删除此管理员?", "删除", JOptionPane.YES_NO_OPTION) 
					== JOptionPane.YES_OPTION) {
				if(adminService.delete((String) adminModel.getValueAt(row, 1)) > 0) {
					updateTable();
					JOptionPane.showMessageDialog(null, "删除成功");
				}	
			}
		});
		// 刷新
		updateBtn.addActionListener(e -> updateTable());
		
	}
	
	/** 更新表格数据 */
	public void updateTable() {
		adminModel.setList(adminService.getAll());
		table.updateUI();
	}
}
