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
	private DormMapper dormMapper; 
	
	/** 通过楼宇编号查找所有宿舍 */
	public List<Dorm> getDormitoryByBid(String bid) {
//		if(StringUtil.isEmpty(bid)) {
//			//return dormMapper.selectAll(bid)
//		}
		return dormMapper.selectAllByBid(bid);
	}

	/** 通过楼宇编号查找所有宿舍号 */
	public List<String> getAllNumberByBid(String bid) {
		return dormMapper.selectAllNumberByBid(bid);
	}

	/** 添加宿舍 */
	public void addDormitoryt(Dorm dorm) throws Exception {
		// 不为空
		if(StringUtil.isEmpty(dorm.getNumber())) {
			throw new Exception("宿舍号不能为空");
		}else if(StringUtil.isEmpty(dorm.getBid())) {
			throw new Exception("宿舍楼不能为空");
		}
		// 同楼宇里宿舍号唯一
		if(dormMapper.selectNumber(dorm)) {
			throw new Exception("该宿舍号已存在");
		}
		// 添加
		dormMapper.insert(dorm);
	}

	public int delete(Dorm dorm) {
		return dormMapper.delete(dorm);
	}
	
}
