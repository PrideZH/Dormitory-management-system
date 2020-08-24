package com.pengfu.util;

import java.awt.Color;
import java.awt.Image;

/**
  * 颜色配置
 * @author PrideZH
 */
public class ConstantConfig {
	
	private ConstantConfig() {}
	
	// 大小
	public static final int SIDEBAR_WIDTH = 200; // 侧边栏宽度

	// 颜色
	public static final Color BG_COLOR = new Color(234, 237, 242); // 背景颜色
	public static final Color PAGE_COLOR = Color.WHITE; // 页面颜色
	
	public static final Color BTN_COLOR = new Color(64, 158, 255); // 按钮默认颜色
	public static final Color BTN_ENTERED_COLOR = new Color(102, 177, 255); // 鼠标悬停按钮颜色
	public static final Color BTN_PRESSED_COLOR =  new Color(58, 142, 230); // 点击按钮颜色

	// 字体
	//public static final Font font = new Font("宋体", style, size);
	
	// 图片
	// 侧边栏按钮下拉
	public static final Image DOWN_IMG = Resources.getBufferedImage("images/sidebar/下拉.png")
		.getScaledInstance(16, 16, Image.SCALE_FAST);
	// 侧边栏按钮上拉
	public static final Image UP_IMG = Resources.getBufferedImage("images/sidebar/上拉.png")
		.getScaledInstance(16, 16, Image.SCALE_FAST);
	// 添加
	public static final Image ADD_IMG = Resources.getBufferedImage("images/button/add.png")
			.getScaledInstance(16, 16, Image.SCALE_FAST);
	// 删除
	public static final Image DELETE_IMG = Resources.getBufferedImage("images/button/delete.png")
			.getScaledInstance(16, 16, Image.SCALE_FAST);
	// 查询
	public static final Image SEARCH_IMG = Resources.getBufferedImage("images/button/search.png")
			.getScaledInstance(16, 16, Image.SCALE_FAST);
	// 修改
	public static final Image SET_IMG = Resources.getBufferedImage("images/button/set.png")
			.getScaledInstance(16, 16, Image.SCALE_FAST);
	// 刷新
	public static final Image UPDATE_IMG = Resources.getBufferedImage("images/button/update.png")
		.getScaledInstance(16, 16, Image.SCALE_FAST);
}
