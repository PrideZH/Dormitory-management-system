package com.pengfu.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageInfo;
import com.pengfu.entity.Student;
import com.pengfu.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private StudentService studnetService;
	
	@Test
	public void testSelectAll() {
		List<Student> students = studentMapper.selectAll();
		students.forEach(System.out::println);
	}
	
	@Test
	public void testSelect() {
		Student student = new Student();
		PageInfo<Student> info = studnetService.search(student, 1, 10);
		System.out.println("页数:" + info.getSize());
		System.out.println("每页数据:" + info.getPageSize());
		System.out.println("第几页:" + info.getPageNum());
		System.out.println("总数据:" + info.getTotal());
		info.getList().forEach(System.out::println);
	}
	
	@Test
	public void testSelectByName() {
		//List<Student> names = studentMapper.selectByName("%张%");
		//names.forEach(System.out::println);
	}
	
	@Test
	public void testSelectSid() {
		System.out.println(studentMapper.selectSid("201910097001"));
	}
	
	@Test
	public void testInsert() {
		Student student = new Student();
		studentMapper.insert(student);
	}
	
}
