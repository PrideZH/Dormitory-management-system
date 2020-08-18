package com.pengfu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.ManagerMapper;
import com.pengfu.entity.Manager;
import com.pengfu.service.ManagerService;

@Transactional
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public Manager loginQuery(String username, String password) throws Exception {
		Manager manager = managerMapper.selectByUsername(username);
		if(manager != null) {
			if(manager.getPassword().equals(password)) {
				return manager;
			}else {
				throw new Exception("密码错误");
			}
		}else {
			throw new Exception("账号不存在");
		}
	}

	@Override
	public List<Manager> getAll() {
		return managerMapper.selectAll();
	}

}
