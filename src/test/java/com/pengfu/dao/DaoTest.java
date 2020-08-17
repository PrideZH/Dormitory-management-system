package com.pengfu.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pengfu.entity.Building;
import com.pengfu.entity.Dormitory;
import com.pengfu.entity.Student;
import com.pengfu.service.StudentService;

public class DaoTest {
	
	SqlSession session;

	@Before
	public void init() throws IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory factory = ac.getBean("sqlSessionFactory", SqlSessionFactory.class);
		session = factory.openSession();
	}
	
	@After
	public void destroy() throws IOException {
		session.close();
	}
	
	@Test
	public void testSelect() {
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student = studentMapper.selectById("201910097001");
		System.out.println(student);
		
//		Student student = studentService.getStudentById("201910097001");
//		System.out.println(student);
	}
	
	@Test
	public void testInsert() {
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setClasses("1");
		student.setCollege("1");
		student.setGender("1");
		student.setIdCard("1");
		student.setName("1");
		student.setPassword("1");
		student.setPhone("1");
		student.setSid("1");
		studentMapper.insert(student);
		session.rollback();
		//session.commit();
	}
	
	@Test
	public void testSelectByName() {
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		List<Student> students = studentMapper.selectByName("%张三%");
		for(Student student : students) {
			System.out.println(student);
		}
	}
	
	@Test
	public void testBuildingSelectAll() {
		BuildingMapper buildingMapper = session.getMapper(BuildingMapper.class);
		List<Building> selectAll = buildingMapper.selectAll();
		for(Building building : selectAll) {
			System.out.println(building);
		}
	}
	
	@Test
	public void testSelecDormitoryByBid() {
		 DormitoryMapper mapper = session.getMapper(DormitoryMapper.class);
		 List<Dormitory> dormitorys = mapper.selecAllByBid();
		 for(Dormitory dormitory : dormitorys) {
			System.out.println(dormitory);
		}
	}
}
