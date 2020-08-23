package com.pengfu.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pengfu.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

	@Autowired
	private StudentMapper studentMapper;
	
	@Test
	public void testSelectAll() {
		List<Student> students = studentMapper.selectAll();
		students.forEach(System.out::println);
	}
	
	@Test
	public void testSelectBy() {
		System.out.println(studentMapper.selectById("201910097001"));
	}
	
	@Test
	public void testSelectByName() {
		List<Student> names = studentMapper.selectByName("%张%");
		names.forEach(System.out::println);
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
