package com.pengfu.service;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pengfu.entity.Student;

public class ServiceTest {
	
	private StudentService studentService;
	
	@Before
	public void init() throws IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		studentService = ac.getBean("studentService", StudentService.class);
	}
	
	@Test
	public void testInsert() {
		Student student = new Student();
		student.setClasses("1");
		student.setCollege("1");
		student.setGender("1");
		student.setIdCard("1");
		student.setName("1");
		student.setPassword("1");
		student.setPhone("1");
		student.setSid("1");
		studentService.addStudent(student);
	}
}
