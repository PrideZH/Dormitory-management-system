package com.pengfu.model.table;

import com.pengfu.entity.Admin;
import com.pengfu.model.Role;

/**
 * 管理员表格数据模型
 * @author PrideZH
 */
public class AdminTableModel extends BaseTableModel<Admin> {
	
	private static final long serialVersionUID = 1L;

	public AdminTableModel() {
		columnName = new String[] {"", "用户名", "姓名", "联系电话", "权限"};
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Admin admin = list.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return admin.getUsername();
		case 2: return admin.getName();
		case 3: return admin.getPhone();
		case 4: return Role.getNameByCode(admin.getRole());
		}
		return null;
	}
	
}
