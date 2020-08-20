package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Building;

public interface BuildingMapper {

	/** 查询所有楼宇 */
	List<Building> selectAll();
	
	/** 查询所有楼宇编号 */
	List<String> selectAllId();

	/** 添加楼宇 */
	void insert(Building building);
	
}
