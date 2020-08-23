package com.pengfu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pengfu.entity.Student;

public class StudentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] title = {"", "姓名", "学号","身份证号", "性别", "联系电话", "学院", "班级", "楼宇号", "宿舍号"};
	private List<Student> students = new ArrayList<>();
	
	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public int getColumnCount() {
		return title.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = students.get(rowIndex);
		switch(columnIndex) {
		case 0: return rowIndex + 1;
		case 1: return student.getName();
		case 2: return student.getSid();
		case 3: return student.getIdCard();
		case 4: return student.getGender();
		case 5: return student.getPhone();
		case 6: return student.getCollege();
		case 7: return student.getClasses();
		case 8: return student.getBid();
		case 9: return student.getDormName();
		}
		return null;
	}
	
	/**列名 */
	@Override
	public String getColumnName(int column) {
		return title[column];
	}
	
	/** 设置是否可编辑 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	/** 获得指定行数据 */
	public Student get(int rowIndex) {
		return students.get(rowIndex);
	}

}
