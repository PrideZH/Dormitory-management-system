package com.pengfu.util;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengfu.view.component.Sidebar;
import com.pengfu.view.component.SidebarBtn;

/**
 * 侧边栏构造
 * @author pengzihao
 */
public class SidebarBuilder {
	
	private static SidebarBuilder sidebarBuilder;
	
	private Map<String, SidebarBtn> sidebarBtns = new HashMap<>();
	
	private SidebarBuilder() {
		// 其他
		sidebarBtns.put("other", new SidebarBtn(Constant.OTHER_IMG, "其他", null, Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("home", new SidebarBtn(Constant.HOME_GREY_IMG, Constant.HOME_BLUE_IMG, 
				"首页", "homePage", Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("system", new SidebarBtn(Constant.SYSTEM_GREY_IMG, Constant.SYSTEM_BLUE_IMG, 
				"系统设置", "systemPage", Constant.SIDEBAR_WIDTH));
		
		// 个人中心
		sidebarBtns.put("personal", new SidebarBtn(Constant.PERSONAL_IMG, "个人中心", null, Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("dormInfo", new SidebarBtn(Constant.DORM_GREY_IMG, Constant.DORM_BLUE_IMG, 
				"宿舍信息", "dormInfoPage", Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("studentProfile", new SidebarBtn(Constant.PROFILE_GREY_IMG, Constant.PROFILE_BLUE_IMG, 
				"个人信息", "studentProfilePage", Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("adminProfile", new SidebarBtn(Constant.PROFILE_GREY_IMG, Constant.PROFILE_BLUE_IMG, 
				"个人信息", "adminProfilePage", Constant.SIDEBAR_WIDTH));
		
		// 楼宇管理
		sidebarBtns.put("building", new SidebarBtn(Constant.BUILDING_IMG, "楼宇管理", null, Constant.SIDEBAR_WIDTH));	
		sidebarBtns.put("buildingList", new SidebarBtn(Constant.LIST_GREY_IMG, Constant.LIST_BLUE_IMG, 
						"楼宇列表", "buildingListPage", Constant.SIDEBAR_WIDTH));
		
		// 宿舍管理
		sidebarBtns.put("dorm", new SidebarBtn(Constant.DORM_GREY_IMG, "宿舍管理", null, Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("dormList", new SidebarBtn(Constant.LIST_GREY_IMG, Constant.LIST_BLUE_IMG, 
						"宿舍列表", "dormListPage", Constant.SIDEBAR_WIDTH));
		
		// 用户权限管理
		sidebarBtns.put("admin", new SidebarBtn(Constant.ADMIN_IMG, "用户权限管理", null, Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("adminList", new SidebarBtn(Constant.LIST_GREY_IMG, Constant.LIST_BLUE_IMG, 
						"用户权限列表", "adminListPage", Constant.SIDEBAR_WIDTH));
		
		// 学生管理
		sidebarBtns.put("student", new SidebarBtn(Constant.STUDENT_IMG, "学生管理", null, Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("studentList", new SidebarBtn(Constant.LIST_GREY_IMG, Constant.LIST_BLUE_IMG, 
						"学生列表", "studentListPage", Constant.SIDEBAR_WIDTH));
		
		// 后勤服务
		sidebarBtns.put("logistics", new SidebarBtn(Constant.LOGISTICS_IMG, "后勤服务", null, Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("damageWarranty", new SidebarBtn(Constant.WARRANTY_GREY_IMG, Constant.WARRANTY_BLUE_IMG,
				"损坏报修", "damageWarrantyPage", Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("damageList", new SidebarBtn(Constant.LIST_GREY_IMG, Constant.LIST_BLUE_IMG, 
						"损坏列表", "damageListPage", Constant.SIDEBAR_WIDTH));
		
		// 生活服务
		sidebarBtns.put("life", new SidebarBtn(Constant.LIFE_IMG, "生活服务", null, Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("electricity", new SidebarBtn(Constant.ELECTRICITY_GREY_IMG, Constant.ELECTRICITY_BLUE_IMG,
				"电费充值", "electricityPage", Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("networkt", new SidebarBtn(Constant.NETWORKT_GREY_IMG, Constant.NETWORKT_BLUE_IMG,
				"校园网充值", "networkPage", Constant.SIDEBAR_WIDTH));
		sidebarBtns.put("card", new SidebarBtn(Constant.CARD_GREY_IMG, Constant.CARD_BLUE_IMG,
				"校园一卡通", "cardPage", Constant.SIDEBAR_WIDTH));
	}
	
	public static SidebarBuilder getSidebarBuilder() {
		if(sidebarBuilder == null) {
			sidebarBuilder = new SidebarBuilder();
		}
		return sidebarBuilder;
	}
	

	/**
	 * 构造侧边栏
	 * @param permissionList 添加的按钮列表
	 */
	public Sidebar build(Map<String, List<String>> permissionList) {
		Sidebar sidebar = new Sidebar();
		sidebar.setPreferredSize(new Dimension(Constant.SIDEBAR_WIDTH, 0));
		
		// 根据按钮列表为侧边栏添加按钮
		for (Map.Entry<String, List<String>> entry : permissionList.entrySet()) { 
			SidebarBtn parentBtn = sidebarBtns.get(entry.getKey());
			if(parentBtn == null) {
			System.out.println(entry.getKey());
			}
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
