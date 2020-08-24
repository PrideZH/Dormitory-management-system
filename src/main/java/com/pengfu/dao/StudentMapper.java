package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Student;


public interface StudentMapper {

	/** 查询所有学生 */
	List<Student> selectAll();
	
	/** 根据id查 */
	Student selectById(String id);
	
	/** 模糊查询 */
	List<Student> selectByStudent(Student student);
	
	/** 根据身份证号查 */
	List<Student> selectByIdCard(String idCard);
	
	/** 查询学号是否存在 */
	boolean selectSid(String id);
	
	/** 添加 */
	void insert(Student student);
	
	/** 修改 */
	void update(Student student);
	
	/** 删除 */
	int deleteById(String id);

}
