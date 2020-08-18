package com.pengfu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.DormitoryMapper;
import com.pengfu.entity.Dormitory;
import com.pengfu.service.DormitoryService;

@Transactional
@Service("dormitoryService")
public class DormitoryServiceImpl implements DormitoryService {

	@Autowired
	private DormitoryMapper dormitoryMapper; 
	
	@Override
	public List<Dormitory> getDormitoryByBid(String bid) {
		return dormitoryMapper.selecAllByBid(bid);
	}
	
}
