package com.pengfu.service;

import com.pengfu.entity.Manager;

public interface ManagerService {

	/** 判断登陆信息是否正确 */
	Manager loginQuery(String sid, String password) throws Exception;
}
