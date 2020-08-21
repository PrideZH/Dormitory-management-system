package com.pengfu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengfu.dao.DormMapper;
import com.pengfu.entity.Dorm;
import com.pengfu.util.StringUtil;

@Transactional
@Service("dormitoryService")
public class DormService {

	@Autowired
	private DormMapper dormitoryMapper; 
	
	/** 通过楼宇编号查找所有宿舍 */
	public List<Dorm> getDormitoryByBid(String bid) {
		return dormitoryMapper.selectAllByBid(bid);
	}

	/** 通过楼宇编号查找所有宿舍号 */
	public List<String> getAllIdByBid(String bid) {
		return dormitoryMapper.selectAllIdByBid(bid);
	}

	/** 添加宿舍 */
	public void addDormitoryt(Dorm dorm) throws Exception {
		// 不为空
		if(StringUtil.isEmpty(dorm.getName())) {
			throw new Exception("宿舍号不能为空");
		}else if(StringUtil.isEmpty(dorm.getBid())) {
			throw new Exception("宿舍楼不能为空");
		}
		// 添加
		dormitoryMapper.insert(dorm);
	}
	
}
