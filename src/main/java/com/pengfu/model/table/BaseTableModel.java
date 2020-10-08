package com.pengfu.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 表格模型基类
 * @author PrideZH
 * @param <T> 数据类型
 */
public abstract class BaseTableModel<T> extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	// 表格列名
	protected String[] columnName;
	// 表格元素
	protected List<T> list = new ArrayList<>();

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (0 == columnIndex) {
			return Integer.class;
		}
		return String.class;
	}

	/** 列名 */
	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

	/** 设置是否可编辑 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/** 获得指定行数据 */
	public T get(int rowIndex) {
		return list.get(rowIndex);
	}
}
