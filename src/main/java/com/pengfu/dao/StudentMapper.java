package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Student;

/** 学生 */
public interface StudentMapper {

	/** 根据id查 */
	Student selectById(String id);
	
	/** 根据宿舍编号查 */
	List<Student> selectByDid(Student student);
	
	/** 模糊查询 */
	List<Student> selectByStudent(Student student);

	/** 查询学号是否存在 */
	boolean selectSid(String id);

	/** 添加 */
	void insert(Student student);
	
	/** 修改 */
	void update(Student student);
	
	/** 删除 */
	int deleteById(String id);

}
