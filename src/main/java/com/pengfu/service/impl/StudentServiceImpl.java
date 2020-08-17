package com.pengfu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.StudentMapper;
import com.pengfu.entity.Student;
import com.pengfu.service.StudentService;

@Transactional
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public Student loginQuery(String sid, String password) throws Exception {
		Student student = studentMapper.selectById(sid);
		if(student != null) {
			if(student.getPassword().equals(password)) {
				return student;
			}else {
				throw new Exception("密码错误");
			}
		}else {
			throw new Exception("账号不存在");
		}
	}
	
	@Override
	public Student getStudentById(String id) {
		return studentMapper.selectById(id);
	}

	@Override
	public void addStudent(Student student) {
		studentMapper.insert(student);
	}


}
