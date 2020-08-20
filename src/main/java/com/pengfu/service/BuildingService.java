package com.pengfu.service;

import java.util.List;

import com.pengfu.entity.Building;

public interface BuildingService {

	/** 查询所有 */
	List<Building> getAll();
	
	/** 获取所有楼宇编号 */
	List<String> getAllId();

	/** 添加楼宇 */
	void addBuilding(Building building);
	
}
