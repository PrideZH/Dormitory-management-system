package com.pengfu.model.table;

import com.pengfu.entity.Warranty;

/**
 * 损坏报修表格数据模型
 * @author PrideZH
 */
public class WarrantyTableModel extends BaseTableModel<Warranty> {

	private static final long serialVersionUID = 1L;

	public WarrantyTableModel() {
		columnName = new String[] {"", "宿舍号", "姓名", "日期", "问题描述", "联系电话", "状态"};
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Warranty warranty = list.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return warranty.getDid();
		case 2: return warranty.getName();
		case 3: return warranty.getDate();
		case 4: return warranty.getDescribe();
		case 5: return warranty.getPhone();
		case 6: return warranty.getState();
		}
		return null;
	}

}
