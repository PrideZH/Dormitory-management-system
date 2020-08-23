package com.pengfu.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pengfu.entity.Admin;
import com.pengfu.entity.Building;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingMapperTest {

	@Autowired
	private BuildingMapper buildingMapper;
	
	@Test
	public void testSelectAll() {
		List<Building> buildings = buildingMapper.selectAll();
		buildings.forEach(System.out::println);
	}
	
	@Test
	public void testSelectAllId() {
		List<String> buildingIds = buildingMapper.selectAllId();
		buildingIds.forEach(System.out::println);
	}
	
	@Test
	public void testSelectBid() {
		System.out.println(buildingMapper.selectBid("C16"));
	}
	
	@Test
	public void testInsert() {
		Building building = new Building();
		building.setBid("test");
		buildingMapper.insert(building);
	}
	
	@Test
	public void testUpdate() {
		Building building = new Building();
		building.setBid("test");
		Admin admin = new Admin();
		admin.setAid(1);
		building.setAdmin(admin);
		buildingMapper.update(building);
	}
	
	@Test
	public void testDelete() {
		buildingMapper.delete("test");
	}
	
}
