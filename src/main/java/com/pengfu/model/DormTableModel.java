package com.pengfu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pengfu.entity.Dorm;

public class DormTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] title = {"", "宿舍号", "状态"};
	private List<Dorm> dorms = new ArrayList<>();

	@Override
	public int getRowCount() {
		return dorms.size();
	}

	@Override
	public int getColumnCount() {
		return title.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Dorm dorm = dorms.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return dorm.getNumber();
		case 2: return dorm.getStudents().size() + "/4";
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
	
	public List<Dorm> getDorms() {
		return dorms;
	}

	public void setDorms(List<Dorm> dorms) {
		this.dorms = dorms;
	}
	
	/** 获得指定行数据 */
	public Dorm get(int rowIndex) {
		return dorms.get(rowIndex);
	}
	
}
