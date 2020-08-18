package com.pengfu.service;

import java.util.List;

import com.pengfu.entity.Dormitory;

public interface DormitoryService {

	/** 通过楼宇编号查找所有宿舍 */
	List<Dormitory> getDormitoryByBid(String bid);
	
}
