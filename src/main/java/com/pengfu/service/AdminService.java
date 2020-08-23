package com.pengfu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.AdminMapper;
import com.pengfu.dao.BuildingMapper;
import com.pengfu.entity.Admin;
import com.pengfu.util.StringUtil;

@Transactional
@Service("managerService")
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private BuildingMapper buildingMapper;
	
	/** 判断登陆信息是否正确 */
	public Admin loginQuery(String username, String password) throws Exception {
		if(StringUtil.isEmpty(username)) {
			throw new Exception("账号不能为空");
		}else if(StringUtil.isEmpty(password)) {
			throw new Exception("密码不能为空");
		}
		// 验证账号密码
		Admin admin = adminMapper.selectByUsername(username);
		if(admin != null) {
			if(admin.getPassword().equals(password)) {
				if(1 == admin.getRole()) {
					// 超级管理员管理所有楼宇id
					admin.setBids(buildingMapper.selectAllId());
				}else {
					// 获得本权限管理的所有楼宇id
					admin.setBids(buildingMapper.selectAllIdByAid(admin.getAid()));
				}
				return admin;
			}else {
				throw new Exception("密码错误");
			}
		}else {
			throw new Exception("账号不存在");
		}
	}

	/** 获得所有管理者 */
	public List<Admin> getAll() {
		List<Admin> admins = adminMapper.selectAll();
		for(Admin admin : admins) {
			admin.setBids(buildingMapper.selectAllIdByAid(admin.getAid()));
		}
		return admins;
	}

	/** 添加管理员 */
	public void addManager(Admin admin) throws Exception {
		// 不为空
		if(admin.getUsername() == null) {
			throw new Exception("用户名不能为空");
		}else if(admin.getPassword() == null) {
			throw new Exception("密码不能为空");
		}else if(admin.getName() == null) {
			throw new Exception("姓名不能为空");
		}
		// 添加
		adminMapper.insert(admin);
	}

	/** 移除 */
	public int delete(String username) {
		return adminMapper.delete(username);
	}

	

}
