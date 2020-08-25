package com.pengfu.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollegeList {
	
	private static CollegeList collegeList;
	private Map<String, List<String>> colleges;
	
	public static CollegeList getCollegeList() {
		if(collegeList == null) {
			collegeList = new CollegeList();
		}
		return collegeList;
	}

	private CollegeList() {
		colleges = new HashMap<>();
		colleges.put("计算机工程学院", Arrays.asList(
				"网络工程1班", "网络工程2班",
				"软件工程1班",
				"计算机科学与技术1班",
				"数据科学与大数据技术1班"));
		colleges.put("计算机汽车与交通工程学院", Arrays.asList(
				"车辆工程1班", "车辆工程2班",
				"物流工程1班",
				"交通工程1班"));
		colleges.put("机械工程学院", Arrays.asList(
				"机械工程1班", "机械工程2班",
				"机械电子工程1班",
				"工业设计1班"));
		colleges.put("电子信息工程学院", Arrays.asList(
				"电子信息工程1班", "电子信息工程2班",
				"自动化1班"));
		colleges.put("通信工程学院", Arrays.asList(
				"通信工程1班", "通信工程2班"));
		colleges.put("电气工程学院", Arrays.asList(
				"电气工程及其自动化1班", "电气工程及其自动化2班",
				"新能源科学与工程1班"));
		colleges.put("建筑学院", Arrays.asList(
				"建筑学1班", "建筑学2班"));
		colleges.put("土木工程学院", Arrays.asList(
				"土木工程1班", "土木工程2班"));
		colleges.put("管理学院", Arrays.asList(
				"工商1班", "工商2班",
				"人力资源管理1班",
				"会计学1班",
				"财务管理1班",
				"市场管理1班",
				"电子管理1班",
				"大数据管理与应用管理1班",
				"供应链管理1班"));
		colleges.put("经济学院", Arrays.asList(
				"国际经济与贸易1班", "国际经济与贸易2班",
				"金融工程1班",
				"经济统计学1班",
				"税收学1班",
				"经济学1班"));
		colleges.put("外国语学院", Arrays.asList(
				"英语1班", "英语2班",
				"日语1班",
				"商务英语1班"));
		colleges.put("珠宝学院", Arrays.asList(
				"珠宝及材料工艺学1班", "珠宝及材料工艺学2班",
				"产品设计1班",
				"服装及服饰设计1班"));
		colleges.put("机器人工程学院", Arrays.asList(
				"机器人工程1班", "机器人工程2班"));
	}
	
	public Set<String> getColleges() {
		 return colleges.keySet();
	}
	
	public List<String> getClasses(String college){
		return colleges.get(college);
	}
	
}
