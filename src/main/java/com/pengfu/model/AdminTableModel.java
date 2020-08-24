package com.pengfu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pengfu.entity.Admin;

public class AdminTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private String[] title = {"", "用户名", "姓名", "联系电话", "权限"};
	private List<Admin> admins = new ArrayList<>();

	@Override
	public int getRowCount() {
		return admins.size();
	}

	@Override
	public int getColumnCount() {
		return title.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Admin admin = admins.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return admin.getUsername();
		case 2: return admin.getName();
		case 3: return admin.getPhone();
		case 4: return admin.getRole() == 1 ? "超级管理员" : "普通管理员";
		}
		return null;
	}
	
	/**列名 */
	@Override
	public String getColumnName(int column) {
		return title[column];
	}
	
	/** 设置是否可编辑 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}
	
	/** 获得指定行数据 */
	public Admin get(int rowIndex) {
		return admins.get(rowIndex);
	}
	
}
