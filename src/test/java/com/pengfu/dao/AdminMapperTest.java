package com.pengfu.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pengfu.entity.Admin;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest {

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Test
	public void testSelectByUsername() {
		Admin admin = adminMapper.selectByUsername("admin2");
		admin.setBids(buildingMapper.selectAllIdByAid(admin.getAid()));
		System.out.println(admin);
	}
	
	@Test
	public void testSelectAll() {
		List<Admin> admins = adminMapper.selectAll();
		admins.forEach(System.out::println);
	}
	
	@Test
	public void testSelectUsername() {
		System.out.println(adminMapper.selectByUsername("123456"));
	}
	
	@Test
	public void testInsert() {
		Admin admin = new Admin();
		//admin.setBids("C16");
		admin.setName("test");
		admin.setPassword("test");
		admin.setRole(0);
		admin.setUsername("test");
		//adminMapper.insert(admin);
	}
	
	@Test
	public void testUpdate() {
		Admin admin = adminMapper.selectByUsername("test");
		admin.setPassword("123456");
		//adminMapper.update(admin);
	}
	
	@Test
	public void testDelete() {
		//adminMapper.delete("test");
	}
}
