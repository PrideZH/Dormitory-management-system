package com.pengfu.model.table;

import com.pengfu.entity.Building;

/**
 * 楼宇表格数据模型
 * @author PrideZH
 */
public class BuildingTableModel extends BaseTableModel<Building> {

	private static final long serialVersionUID = 1L;
	
	public BuildingTableModel() {
		columnName = new String[] {"", "楼宇编号", "负责人", "联系电话"};
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Building building = list.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return building.getBid();
		case 2: 
			if(building.getAdmin() == null) return "-";
			return building.getAdmin().getName();
		case 3: 
			if(building.getAdmin() == null) return "-";
			return building.getAdmin().getPhone();
		}
		return null;
	}
	
}
