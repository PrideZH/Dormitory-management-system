package com.pengfu.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pengfu.entity.Dorm;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DormMapperTest {

	@Autowired
	private DormMapper dormMapper;
	
	@Test
	public void testSelectAllByBid() {
		List<Dorm> dorms = dormMapper.selectAllByBid("A1");
		dorms.forEach(System.out::println);
	}
	
	@Test
	public void testSelectAllIdByBid() {
		List<String> dorms = dormMapper.selectAllNumberByBid("C16");
		dorms.forEach(System.out::println);
	}
	
	@Test
	public void testInsert() {
		Dorm dorm = new Dorm();
		dorm.setNumber("233");
		dorm.setBid("C16");
		//dormMapper.insert(dorm);
	}
	
	@Test
	public void testDelete() {
		Dorm dorm = new Dorm();
		dorm.setNumber("233");
		dorm.setBid("C16");
		//dormMapper.delete(dorm);
	}
	
}
