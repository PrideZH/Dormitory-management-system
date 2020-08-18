package com.pengfu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pengfu.entity.Building;

public class BuildingTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] title = {"楼宇编号", "负责人", "联系电话", "操作"};
	private List<Building> buildings = new ArrayList<>();

	@Override
	public int getRowCount() {
		return buildings.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Building building = buildings.get(rowIndex);
		switch(columnIndex) {
		case 0: return building.getBid();
		case 1: return building.getManager().getName();
		case 2: return building.getManager().getPhone();
		case 3: return 0;
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

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
	
}
