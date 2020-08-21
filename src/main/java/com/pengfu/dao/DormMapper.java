package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Dorm;

public interface DormMapper {

	/** 通过楼宇编号查询所有 */
	List<Dorm> selectAllByBid(String bid);
	
	/** 通过楼宇编号查询所有宿舍号 */
	List<String> selectAllIdByBid(String bid);

	/** 添加 */
	void insert(Dorm dorm);
	
	/** 删除 */
	void delete(Dorm dorm);
	
}
