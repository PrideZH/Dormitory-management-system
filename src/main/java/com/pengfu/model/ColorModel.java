package com.pengfu.model;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Set;

import com.pengfu.util.Constant;

public class ColorModel {
	
	private static LinkedHashMap<String, Theme> themes = new LinkedHashMap<>();
	
	private static ColorModel colorModel = null;
	
	/**
	 * 主题
	 * @author PrideZH
	 */
	interface Theme {
		void setTheme();
	}
	
	// 主题设置
	static {
		themes.put("默认", new Theme() {
			@Override
			public void setTheme() {
				// 窗口
				Constant.TITLE_COLOR = new Color(36, 38, 48); // 标题栏
				// 侧边栏
				Constant.SIDEBAR_COLOR = new Color(57, 62, 75); // 侧边栏颜色
				Constant.SIDEBAR_PARENT_BTN_COLOR = new Color(57, 62, 75); // 父按钮默认颜色
				Constant.SIDEBAR_BTN_ENTERED_COLOR = new Color(32, 45, 60); // 鼠标悬停按钮颜色
				Constant.SIDEBAR_SUBCLASS_BTN_COLOR = new Color(12, 7, 21); //  子按钮默认颜色
				// 页面
				Constant.BG_COLOR = new Color(234, 237, 242); // 背景颜色
				Constant.PAGE_COLOR = Color.WHITE; // 页面颜色
				Constant.PAGE_FONT_COLOR = Color.BLACK; // 页面字体默认颜色
				Constant.PAGE_BORDER_COLOR = new Color(65, 113, 156); // 边框颜色
				// 按钮
				Constant.BTN_COLOR = new Color(64, 158, 255); // 按钮默认颜色
				Constant.BTN_ENTERED_COLOR = new Color(102, 177, 255); // 鼠标悬停按钮颜色
				Constant.BTN_PRESSED_COLOR =  new Color(58, 142, 230); // 点击按钮颜色
				Constant.BTN_FONT_COLOR =  Color.WHITE; // 按钮字体颜色
				// 表格
				Constant.TABLE_ODD_COLOR =  new Color(251, 250, 251);
				Constant.TABLE_EVEN_COLOR =  Color.WHITE;
				Constant.TABLE_SELECT_COLOR =  new Color(236, 245, 255); // 选中时颜色
			}
		});
		themes.put("深色", new Theme() {
			@Override
			public void setTheme() {
				// 窗口
				Constant.TITLE_COLOR = new Color(24, 24, 24); // 标题栏
				// 侧边栏
				Constant.SIDEBAR_COLOR = new Color(24, 24, 24); // 侧边栏颜色
				Constant.SIDEBAR_PARENT_BTN_COLOR = new Color(24, 24, 24); // 父按钮默认颜色
				Constant.SIDEBAR_BTN_ENTERED_COLOR = new Color(21, 21, 21); // 鼠标悬停按钮颜色
				Constant.SIDEBAR_SUBCLASS_BTN_COLOR = new Color(24, 24, 24); //  子按钮默认颜色
				// 页面
				Constant.BG_COLOR = new Color(16, 16, 16); // 背景颜色
				Constant.PAGE_COLOR = new Color(24, 24, 24); // 页面颜色
				Constant.PAGE_FONT_COLOR = new Color(225, 225, 225); // 页面字体默认颜色
				Constant.PAGE_BORDER_COLOR = Color.WHITE; // 边框颜色
				// 表格
				Constant.TABLE_ODD_COLOR =  new Color(45, 45, 45);
				Constant.TABLE_EVEN_COLOR =  new Color(53, 53, 53);
				Constant.TABLE_SELECT_COLOR =  new Color(64, 64, 64); // 选中时颜色
			}
		});
		themes.put("深蓝", new Theme() {
			@Override
			public void setTheme() {
				// 窗口
				Constant.TITLE_COLOR = new Color(0, 70, 167); // 标题栏
				// 侧边栏
				Constant.SIDEBAR_COLOR = new Color(0, 70, 167); // 侧边栏颜色
				Constant.SIDEBAR_PARENT_BTN_COLOR = new Color(0, 70, 167); // 父按钮默认颜色
				Constant.SIDEBAR_BTN_ENTERED_COLOR = new Color(0, 82, 202); // 鼠标悬停按钮颜色
				Constant.SIDEBAR_SUBCLASS_BTN_COLOR = new Color(0, 52, 123); //  子按钮默认颜色
				// 页面
				Constant.BG_COLOR = Color.WHITE; // 背景颜色
				Constant.PAGE_COLOR = Color.WHITE; // 页面颜色
				Constant.PAGE_FONT_COLOR = Color.BLACK; // 页面字体默认颜色
				Constant.PAGE_BORDER_COLOR = new Color(65, 113, 156); // 边框颜色
				// 表格
				Constant.TABLE_ODD_COLOR =  new Color(251, 250, 251);
				Constant.TABLE_EVEN_COLOR =  Color.WHITE;
				Constant.TABLE_SELECT_COLOR =  new Color(236, 245, 255); // 选中时颜色
			}
		});
	}

	private ColorModel() {}
	
	public static ColorModel getColorModel() {
		if(null == colorModel) {
			colorModel = new ColorModel();
		}
		return colorModel;
	}
	
	/** 获得所有主题名称 */
	public Set<String> getTheme() {
		return themes.keySet();
	}
	
	/** 设置系统主题 */
	public void setTheme(String theme) {
		Theme t = themes.get(theme);
		if(t != null) {
			t.setTheme();
		}
	}

}
