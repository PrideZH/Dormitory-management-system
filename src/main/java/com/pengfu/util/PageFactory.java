package com.pengfu.util;

import java.util.HashMap;
import java.util.Map;

import com.pengfu.view.page.BasePanel;

/** 工厂模式实现页面的懒加载 */
public class PageFactory {
	
	private static Map<String, BasePanel> pageMap = new HashMap<>();

	private PageFactory() {}

	/** 反射创建类 */
	private static BasePanel createInstance(String pageName) {
		BasePanel page = null;
		try {
			page = (BasePanel) Class.forName("com.pengfu.view.page." + pageName).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	    return page;
	}
	
	/** 工厂模式获得Page */
	public static BasePanel getPage(String pageName) {
		BasePanel page = pageMap.get(pageName);
		if(page == null) {
			page = createInstance(pageName);
			pageMap.put(pageName, page);
		}
		return page;
	}
	
}
