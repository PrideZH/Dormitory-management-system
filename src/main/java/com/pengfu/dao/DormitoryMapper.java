package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Dormitory;

public interface DormitoryMapper {

	/** 通过楼宇编号查询所有宿舍 */
	List<Dormitory> selecAllByBid(String bid);
	
	/** 通过楼宇编号查询所有宿舍号 */
	List<String> selecAllIdByBid(String bid);

	/** 添加宿舍 */
	void insert(Dormitory dormitory);
}
