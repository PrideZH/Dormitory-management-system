package com.pengfu.dao;

import java.util.List;

import com.pengfu.entity.Admin;

/** 管理员 */
public interface AdminMapper {

	/** 通过用户名查询 */
	Admin selectByUsername(String username);

	/** 查询所有 */
	List<Admin> selectAll();
	
	/** 查询用户名是否存在 */
	boolean selectUsername(String username);

	/** 添加 */
	void insert(Admin admin);
	
	/** 修改 */
	void update(Admin admin);
	
	/** 通过用户名删除 */
	void delete(String username);
	
}
