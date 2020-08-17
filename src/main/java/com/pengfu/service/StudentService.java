package com.pengfu.service;

import com.pengfu.entity.Student;

public interface StudentService {

	/** 判断登陆信息是否正确 */
	Student loginQuery(String sid, String password) throws Exception;
	
	/** 根据id获取学生 */
	Student getStudentById(String id);
	
	/** 添加学生 */
	void addStudent(Student student);
}
