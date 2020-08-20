package com.pengfu.service.impl;

import java.util.List;

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
	public void addStudent(Student student) throws Exception {
		// 不为空
		if(student.getSid() == null) {
			throw new Exception("学号不能为空");
		}else if(student.getName() == null) {
			throw new Exception("姓名不能为空");
		}else if(student.getGender() == null) {
			throw new Exception("性别不能为空");
		}else if(student.getIdCard() == null) {
			throw new Exception("身份证号不能为空");
		}else if(student.getCollege() == null) {
			throw new Exception("学院不能为空");
		}else if(student.getClasses() == null) {
			throw new Exception("班级不能为空");
		}else if(student.getPhone() == null) {
			throw new Exception("联系电话不能为空");
		}
		// 学号唯一
		if(studentMapper.selectSid(student.getSid())) {
			throw new Exception("该学号已存在");
		}
		// 默认密码身份证后6位
		student.setPassword(student.getIdCard().substring(12));
		studentMapper.insert(student);
	}

	@Override
	public List<Student> getAll() {
		return studentMapper.selectAll();
	}


}
