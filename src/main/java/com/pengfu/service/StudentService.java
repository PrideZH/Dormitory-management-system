package com.pengfu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.StudentMapper;
import com.pengfu.entity.Student;
import com.pengfu.util.StringUtil;

@Transactional
@Service("studentService")
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	/** 判断登陆信息是否正确 */
	public Student loginQuery(String sid, String password) throws Exception {
		if(StringUtil.isEmpty(sid)) {
			throw new Exception("账号不能为空");
		}else if(StringUtil.isEmpty(password)) {
			throw new Exception("密码不能为空");
		}
		// 验证账号密码
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
	
	/** 根据id获取学生 */
	public Student getStudentById(String id) {
		return studentMapper.selectById(id);
	}

	/** 添加学生 */
	public void addStudent(Student student) throws Exception {
		// 不为空
		if(StringUtil.isEmpty(student.getSid())) {
			throw new Exception("学号不能为空");
		}else if(StringUtil.isEmpty(student.getName())) {
			throw new Exception("姓名不能为空");
		}else if(StringUtil.isEmpty(student.getGender())) {
			throw new Exception("性别不能为空");
		}else if(StringUtil.isEmpty(student.getIdCard())) {
			throw new Exception("身份证号不能为空");
		}else if(StringUtil.isEmpty(student.getCollege())) {
			throw new Exception("学院不能为空");
		}else if(StringUtil.isEmpty(student.getClasses())) {
			throw new Exception("班级不能为空");
		}else if(StringUtil.isEmpty(student.getPhone())) {
			throw new Exception("联系电话不能为空");
		}else if(StringUtil.isEmpty(student.getDormName())) {
			throw new Exception("楼宇号不能为空");
		}
		// 学号唯一
		if(studentMapper.selectSid(student.getSid())) {
			throw new Exception("该学号已存在");
		}
		// 默认密码身份证后6位
		student.setPassword(student.getIdCard().substring(12));
		studentMapper.insert(student);
	}

	/** 查询所有学生 */
	public List<Student> getAll() {
		return studentMapper.selectAll();
	}


}
