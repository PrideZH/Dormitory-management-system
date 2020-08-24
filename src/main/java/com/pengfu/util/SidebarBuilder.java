package com.pengfu.util;

import static com.pengfu.util.ConstantConfig.SIDEBAR_WIDTH;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengfu.view.component.Sidebar;
import com.pengfu.view.component.SidebarBtn;

public class SidebarBuilder {
	
	private static SidebarBuilder sidebarBuilder;
	
	private Map<String, SidebarBtn> sidebarBtns = new HashMap<>();
	
	private SidebarBuilder() {
		// 个人中心
		sidebarBtns.put("personal", new SidebarBtn("images/sidebar/个人中心.png", "个人中心", null, SIDEBAR_WIDTH));
		sidebarBtns.put("dormitoryInfo", new SidebarBtn("images/sidebar/宿舍_灰.png", "images/sidebar/宿舍_蓝.png", 
				"宿舍信息", "dormInfoPage", SIDEBAR_WIDTH));
		sidebarBtns.put("studentProfile", new SidebarBtn("images/sidebar/个人信息_灰.png", "images/sidebar/个人信息_蓝.png", 
				"个人信息", "studentProfilePage", SIDEBAR_WIDTH));
		sidebarBtns.put("adminProfile", new SidebarBtn("images/sidebar/个人信息_灰.png", "images/sidebar/个人信息_蓝.png",
				"个人信息", "adminProfilePage", SIDEBAR_WIDTH));
		
		// 楼宇管理
		sidebarBtns.put("building", new SidebarBtn("images/sidebar/楼_灰.png", "楼宇管理", null, SIDEBAR_WIDTH));	
		sidebarBtns.put("buildingList", new SidebarBtn("images/sidebar/列表_灰.png", "images/sidebar/列表_蓝.png", 
						"楼宇列表", "buildingListPage", SIDEBAR_WIDTH));
		
		// 宿舍管理
		sidebarBtns.put("dormitory", new SidebarBtn("images/sidebar/宿舍_灰.png", "宿舍管理", null, SIDEBAR_WIDTH));
		sidebarBtns.put("dormitoryList", new SidebarBtn("images/sidebar/列表_灰.png", "images/sidebar/列表_蓝.png", 
						"宿舍列表", "dormListPage", SIDEBAR_WIDTH));
		
		// 用户权限管理
		sidebarBtns.put("manager", new SidebarBtn("images/sidebar/admin.png", "用户权限管理", null, SIDEBAR_WIDTH));
		sidebarBtns.put("managerList", new SidebarBtn("images/sidebar/列表_灰.png", "images/sidebar/列表_蓝.png", 
						"用户权限列表", "adminListPage", SIDEBAR_WIDTH));
		
		// 学生管理
		sidebarBtns.put("student", new SidebarBtn("images/sidebar/student.png", "学生管理", null, SIDEBAR_WIDTH));
		sidebarBtns.put("studentList", new SidebarBtn("images/sidebar/列表_灰.png", "images/sidebar/列表_蓝.png", 
						"学生列表", "studentListPage", SIDEBAR_WIDTH));
		
		// 后勤服务
		sidebarBtns.put("logistics", new SidebarBtn("images/sidebar/logistics.png", "后勤服务", null, SIDEBAR_WIDTH));
		sidebarBtns.put("damageWarranty", new SidebarBtn("images/sidebar/报修_灰.png", "images/sidebar/报修_蓝.png",
				"损坏报修", "damageWarrantyPage", SIDEBAR_WIDTH));
		sidebarBtns.put("damageList", new SidebarBtn("images/sidebar/列表_灰.png", "images/sidebar/列表_蓝.png", 
						"损坏列表", "damageListPage", SIDEBAR_WIDTH));
		
		// 生活服务
		sidebarBtns.put("life", new SidebarBtn("images/sidebar/life.png", "生活服务", null, SIDEBAR_WIDTH));
		sidebarBtns.put("electricity", new SidebarBtn("images/sidebar/电费_灰.png", "images/sidebar/电费_蓝.png",
				"电费充值", "electricityPage", SIDEBAR_WIDTH));
		sidebarBtns.put("networkt", new SidebarBtn("images/sidebar/网络_灰.png", "images/sidebar/网络_蓝.png",
				"校园网充值", "networkPage", SIDEBAR_WIDTH));
		sidebarBtns.put("card", new SidebarBtn("images/sidebar/卡_灰.png", "images/sidebar/卡_蓝.png",
				"校园一卡通", "cardPage", SIDEBAR_WIDTH));
	}
	
	public static SidebarBuilder getSidebarBuilder() {
		if(sidebarBuilder == null) {
			sidebarBuilder = new SidebarBuilder();
		}
		return sidebarBuilder;
	}
	

	/** 构造侧边栏 */
	public Sidebar build(Map<String, List<String>> permissionList) {
		Sidebar sidebar = new Sidebar();
		sidebar.setPreferredSize(new Dimension(ConstantConfig.SIDEBAR_WIDTH, 0));
		
		// 单据权限管理列表为添加侧边栏添加按钮
		for (Map.Entry<String, List<String>> entry : permissionList.entrySet()) { 
			SidebarBtn parentBtn = sidebarBtns.get(entry.getKey());
			// 添加父按钮
			sidebar.addBtn(parentBtn);
			for(String item : entry.getValue()) {
				// 添加子按钮
				parentBtn.addSideBtnItem(sidebarBtns.get(item));
			}
		}
		
		return sidebar;
	}

}
