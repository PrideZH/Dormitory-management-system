package com.pengfu.model.table;

import com.pengfu.entity.Dorm;

/**
 * 宿舍表格数据模型
 * @author PrideZH
 */
public class DormTableModel extends BaseTableModel<Dorm> {

	private static final long serialVersionUID = 1L;

	public DormTableModel() {
		columnName = new String[] {"", "宿舍号", "状态"};
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Dorm dorm = list.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return dorm.getNumber();
		case 2: return dorm.getStudents().size() + "/4";
		}
		return null;
	}
	
}
