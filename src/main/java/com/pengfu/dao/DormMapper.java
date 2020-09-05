package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Dorm;

/** 宿舍 */
public interface DormMapper {

	/** 通过楼宇编号查询所有 */
	List<Dorm> selectAllByBid(String bid);
	
	/** 通过楼宇编号查询所有宿舍号 */
	List<String> selectAllNumberByBid(String bid);
	
	/** 查询 宿舍号是否存在 */
	boolean selectNumber(Dorm dorm);

	/** 添加 */
	void insert(Dorm dorm);
	
	/** 删除 */
	int delete(Dorm dorm);

}
