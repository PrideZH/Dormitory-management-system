package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Student;


public interface StudentMapper {

	/** 根据id查询学生 */
	Student selectById(String id);
	
	/** 查询所有学生 */
	List<Student> selectAll();
	
	/** 查询学号 */
	boolean selectSid(String id);
	
	/** 添加学生信息 */
	void insert(Student student);
	
	/** 更新学生信息 */
	void update(Student student);
	
	/** 删除学生信息 */
	void deleteById(String id);
	
	/** 根据名称模糊查询 */
	List<Student> selectByName(String name);
}
