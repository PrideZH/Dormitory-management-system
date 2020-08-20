package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Manager;

public interface ManagerMapper {

	Manager selectByUsername(String username);

	/** 查询所有 */
	List<Manager> selectAll();

	/** 添加管理员 */
	void insert(ManagerMapper managerMapper);
}
