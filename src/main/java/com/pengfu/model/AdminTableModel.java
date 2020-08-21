package com.pengfu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pengfu.entity.Admin;

public class AdminTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private String[] title = {"", "姓名", "联系电话", "权限", "操作"};
	private List<Admin>  managers = new ArrayList<>();

	@Override
	public int getRowCount() {
		return managers.size();
	}

	@Override
	public int getColumnCount() {
		return title.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Admin manager = managers.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return manager.getName();
		case 2: return manager.getPhone();
		case 3: return manager.getRole();
		case 4: return 0;
		}
		return null;
	}
	
	/**列名 */
	@Override
	public String getColumnName(int column) {
		return title[column];
	}
	
	/**不可编辑 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public List<Admin> getManagers() {
		return managers;
	}

	public void setManagers(List<Admin> managers) {
		this.managers = managers;
	}
	
}
