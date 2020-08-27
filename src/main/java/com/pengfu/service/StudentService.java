package com.pengfu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	
	/** 根据宿舍id获取学生 */
	public List<Student> getStudentByDid(Student student) {
		return studentMapper.selectByDid(student);
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
		}
		// 格式
		int idCardSize = student.getIdCard().length();
		if(idCardSize != 15 && idCardSize != 18) {
			throw new Exception("身份证号错误(" + student.getIdCard() + ")");
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
	
	/**
	 * 模糊搜索
	 * @param student 多条件查询
	 * @param page 第几页
	 * @param pageNum 每页数据
	 * @return PageInfo
	 */
	public PageInfo<Student> search(Student student, int pageNum, int pageSize) {
		// 楼宇编号
		if(StringUtil.isEmpty(student.getBid())) {
			student.setBid(null);
		}
		// 宿舍号
		if(StringUtil.isEmpty(student.getDormName())) {
			student.setDormName(null);
		}
		// 学号
		if(StringUtil.isEmpty(student.getSid())) {
			student.setSid(null);
		}else {
			student.setSid("%" +student.getSid() + "%");
		}
		// 姓名
		if(StringUtil.isEmpty(student.getName())) {
			student.setName(null);
		}else {
			student.setName("%" + student.getName() + "%");
		}
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<Student> pageInfo = new PageInfo<>(studentMapper.selectByStudent(student));
		return pageInfo;
	}
	
	public long getNumber() {
		return studentMapper.selectNumber();
	}

	/** 修改 */
	public void update(Student student) {
		studentMapper.update(student);
	}

	public int delete(String id) {
		return studentMapper.deleteById(id);
	}


}
