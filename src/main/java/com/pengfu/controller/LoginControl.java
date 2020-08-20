package com.pengfu.controller;

public interface LoginControl {

	/** 登陆操作  */
	void Logint(boolean isStudent, String username, String password) throws Exception;
	
	/** 关闭窗口 */
	void close();
	
}
