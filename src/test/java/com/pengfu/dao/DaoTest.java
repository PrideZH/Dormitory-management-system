package com.pengfu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pengfu.entity.Building;
import com.pengfu.entity.Manager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Test
	public void testSelectSid() {
		System.out.println(studentMapper.selectSid("20"));
	}
	
	@Test
	public void testInsertBuilding() {
		Building building = new Building();
		building.setBid("A16");
		Manager manager = new Manager();
		manager.setMid("3");
		building.setManager(manager);
		buildingMapper.insert(building);
	}
		
}
