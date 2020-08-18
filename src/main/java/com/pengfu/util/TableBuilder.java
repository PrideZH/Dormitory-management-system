package com.pengfu.util;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/** 表格构造者 */
public class TableBuilder {
	
	private static TableBuilder tableBuilder;
	
	private TableBuilder() {}
	
	public static TableBuilder getTableBuilder() {
		if(tableBuilder == null) {
			tableBuilder = new TableBuilder();
		}
		return tableBuilder;
	}

	/** 构造一个表格 */
	public JTable build(TableModel model) {
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // 按比例调整列宽
		table.setRowHeight(32); // 设置单元格高度
		table.getTableHeader().setResizingAllowed(false); // 不可改变大小
		table.getTableHeader().setReorderingAllowed(false); // 不可拖动
	    return table;
	}
	
}
