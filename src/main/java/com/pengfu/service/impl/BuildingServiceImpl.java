package com.pengfu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.BuildingMapper;
import com.pengfu.entity.Building;
import com.pengfu.service.BuildingService;

@Transactional
@Service("buildingService")
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingMapper buildingMapper; 
	
	@Override
	public List<Building> getAll() {
		return buildingMapper.selectAll();
	}

}
