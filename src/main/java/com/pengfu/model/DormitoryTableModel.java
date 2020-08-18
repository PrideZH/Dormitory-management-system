package com.pengfu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pengfu.entity.Dormitory;

public class DormitoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] title = {"宿舍号", "状态", "操作"};
	private List<Dormitory> dormitorys = new ArrayList<>();

	@Override
	public int getRowCount() {
		return dormitorys.size();
	}

	@Override
	public int getColumnCount() {
		return title.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Dormitory dormitory = dormitorys.get(rowIndex);
		switch(columnIndex) {
		case 0: return dormitory.getName();
		case 1: return dormitory.getStudents().size();
		case 2: return 0;
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
	
	public List<Dormitory> getDormitorys() {
		return dormitorys;
	}

	public void setDormitorys(List<Dormitory> dormitorys) {
		this.dormitorys = dormitorys;
	}
	
}
