package com.pengfu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.BuildingMapper;
import com.pengfu.entity.Building;

@Transactional
@Service("buildingService")
public class BuildingService {

	@Autowired
	private BuildingMapper buildingMapper; 
	
	/** 查询所有 */
	public List<Building> getAll() {
		return buildingMapper.selectAll();
	}

	/** 获取所有楼宇编号 */
	public List<String> getAllId() {
		return buildingMapper.selectAllId();
	}

	/** 添加楼宇 */
	public void addBuilding(Building building) throws Exception {
		// 不为空
		if(building.getBid() == null) {
			throw new Exception("楼宇编号不能为空");
		}
		// 楼宇编号唯一
		if(buildingMapper.selectBid(building.getBid())) {
			throw new Exception("该楼宇编号已存在");
		}
		buildingMapper.insert(building);
	}

}
