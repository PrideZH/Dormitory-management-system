package com.pengfu.model.table;

import com.pengfu.entity.Student;
import com.pengfu.util.StringUtil;

/**
 * 学生表格数据模型
 * @author PrideZH
 */
public class StudentTableModel extends BaseTableModel<Student> {

	private static final long serialVersionUID = 1L;

	public StudentTableModel() {
		columnName = new String[] {"", "姓名", "学号", "身份证号", "性别", "联系电话", "学院", "班级", "楼宇号", "宿舍号"};
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = list.get(rowIndex);
		String bid = student.getBid();
		String dormName = student.getDormName();
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return student.getName();
		case 2: return student.getSid();
		case 3: return student.getIdCard();
		case 4: return student.getGender();
		case 5: return student.getPhone();
		case 6: return student.getCollege();
		case 7: return student.getClasses();
		case 8: return StringUtil.isEmpty(bid) ? "-" : bid;
		case 9: return StringUtil.isEmpty(bid) ? "-" : dormName;
		}
		return null;
	}
	
}
