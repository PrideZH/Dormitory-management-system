package com.pengfu.service;

import java.util.List;

import com.pengfu.entity.Manager;

public interface ManagerService {

	/** 判断登陆信息是否正确 */
	Manager loginQuery(String sid, String password) throws Exception;
	
	/** 获得所有管理者所有 */
	List<Manager> getAll();
}
