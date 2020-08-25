package com.pengfu.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
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
		// 按比例调整列宽
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		// 选中时背景颜色
		table.setSelectionBackground(new Color(236, 245, 255));
		// 只能选单行
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		// 表头设置
		JTableHeader tableHeader = table.getTableHeader();
		// 不可改变大小
		tableHeader.setResizingAllowed(false); 
		// 不可拖动
		tableHeader.setReorderingAllowed(false); 
		// 字体
		tableHeader.setFont(new Font("宋体", Font.BOLD, 16));
		// 颜色
		tableHeader.setBackground(Color.WHITE);
		
		// 单元格渲染器
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
	
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0) {
	                setBackground(new Color(251, 250, 251));
	            } else {
	                setBackground(Color.WHITE);
	            }
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
    	};
    	// 设置table内容居中
    	cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 遍历表格的每一列，分别设置单元格渲染器
        for (int i = 0, lenght = model.getColumnCount(); i < lenght; ++i) {
            // 设置 表格列 的 单元格渲染器
        	table.getColumn(model.getColumnName(i)).setCellRenderer(cellRenderer);
        }

	    return table;
	}

}




