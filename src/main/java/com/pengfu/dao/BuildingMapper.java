package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Building;

public interface BuildingMapper {

	/** 查询所有 */
	List<Building> selectAll();
	
	/** 查询所有编号 */
	List<String> selectAllId();
	
	/** 查询编号是否存在 */
	boolean selectBid(String bid);

	/** 添加 */
	void insert(Building building);
	
	/** 修改 */
	void update(Building building);
	
	/** 通过楼宇编号删除 */
	void delete(String bid);
	
}
