package com.pengfu.util;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
		// 设置单元格高度
		table.setRowHeight(32); 
		// 设置单元格列宽
		table.getColumnModel().getColumn(0).setMaxWidth(32);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // 按比例调整
		// 不可改变大小
		table.getTableHeader().setResizingAllowed(false); 
		// 不可拖动
		table.getTableHeader().setReorderingAllowed(false); 
		// 只能选单行
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 设置table内容居中
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		table.setDefaultRenderer(Object.class, tcr);

	    return table;
	}

}




