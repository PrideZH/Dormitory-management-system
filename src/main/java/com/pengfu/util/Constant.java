package com.pengfu.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
  * 常量配置
 * @author PrideZH
 */
public class Constant {
	
	private Constant() {}
	
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
	
	/**
	 * 窗口图片
	 */
	// 窗口图标
	public static final ImageIcon LOGO_IMG = Resources.getImageIcon("images/logo/logo_school.png");
	// 最小化
	public static final ImageIcon MINIMIZE_GREY_IMG = Resources.getImageIcon("images/button/minimize_gery.png");
	public static final ImageIcon MINIMIZE_WHITE_IMG = Resources.getImageIcon("images/button/minimize_white.png");
	// 最大化
	public static final ImageIcon MAXIMIZE_GREY_IMG = Resources.getImageIcon("images/button/maximize_gery.png");
	public static final ImageIcon MAXIMIZE_WHITE_IMG = Resources.getImageIcon("images/button/maximize_white.png");
	// 关闭
	public static final ImageIcon CLOSE_GREY_IMG = Resources.getImageIcon("images/button/close_gery.png");
	public static final ImageIcon CLOSE_WHITE_IMG = Resources.getImageIcon("images/button/close_white.png");
	
	/**
	 * 侧边栏图片
	 */
	// 个人中心
	public static final BufferedImage PERSONAL_IMG = Resources.getBufferedImage("images/sidebar/个人中心.png");
	// 宿舍
	public static final BufferedImage DORM_GREY_IMG = Resources.getBufferedImage("images/sidebar/宿舍_灰.png");
	public static final BufferedImage DORM_BLUE_IMG = Resources.getBufferedImage("images/sidebar/宿舍_蓝.png");
	// 个人信息
	public static final BufferedImage PROFILE_GREY_IMG = Resources.getBufferedImage("images/sidebar/个人信息_灰.png");
	public static final BufferedImage PROFILE_BLUE_IMG = Resources.getBufferedImage("images/sidebar/个人信息_蓝.png");
	// 楼宇
	public static final BufferedImage BUILDING_IMG = Resources.getBufferedImage("images/sidebar/building.png");
	// 列表
	public static final BufferedImage LIST_GREY_IMG = Resources.getBufferedImage("images/sidebar/列表_灰.png");
	public static final BufferedImage LIST_BLUE_IMG = Resources.getBufferedImage("images/sidebar/列表_蓝.png");
	// 管理员
	public static final BufferedImage ADMIN_IMG = Resources.getBufferedImage("images/sidebar/admin.png");
	// 学生
	public static final BufferedImage STUDENT_IMG = Resources.getBufferedImage("images/sidebar/student.png");
	// 后勤服务
	public static final BufferedImage LOGISTICS_IMG = Resources.getBufferedImage("images/sidebar/logistics.png");
	// 报修
	public static final BufferedImage WARRANTY_GREY_IMG = Resources.getBufferedImage("images/sidebar/报修_灰.png");
	public static final BufferedImage WARRANTY_BLUE_IMG = Resources.getBufferedImage("images/sidebar/报修_蓝.png");
	// 生活服务
	public static final BufferedImage LIFE_IMG = Resources.getBufferedImage("images/sidebar/life.png");
	// 电费
	public static final BufferedImage ELECTRICITY_GREY_IMG = Resources.getBufferedImage("images/sidebar/电费_灰.png");
	public static final BufferedImage ELECTRICITY_BLUE_IMG = Resources.getBufferedImage("images/sidebar/电费_蓝.png");
	// 网络
	public static final BufferedImage NETWORKT_GREY_IMG = Resources.getBufferedImage("images/sidebar/网络_灰.png");
	public static final BufferedImage NETWORKT_BLUE_IMG = Resources.getBufferedImage("images/sidebar/网络_蓝.png");
	// 卡
	public static final BufferedImage CARD_GREY_IMG = Resources.getBufferedImage("images/sidebar/卡_灰.png");
	public static final BufferedImage CARD_BLUE_IMG = Resources.getBufferedImage("images/sidebar/卡_蓝.png");
	// 侧边栏按钮下拉
	public static final Image DOWN_IMG = Resources.getBufferedImage("images/sidebar/down.png")
		.getScaledInstance(16, 16, Image.SCALE_FAST);
	// 侧边栏按钮上拉
	public static final Image UP_IMG = Resources.getBufferedImage("images/sidebar/up.png")
		.getScaledInstance(16, 16, Image.SCALE_FAST);
	
	/**
	 * 页面按钮图片
	 */
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
	// 上一页
	public static final ImageIcon BACK_BLACK_IMG = Resources.getImageIcon("images/button/back_black.png");
	public static final ImageIcon BACK_BLUE_IMG = Resources.getImageIcon("images/button/back_blue.png");
	// 下一页
	public static final ImageIcon NEXT_BLACK_IMG = Resources.getImageIcon("images/button/next_black.png");
	public static final ImageIcon NEXT_BLUE_IMG = Resources.getImageIcon("images/button/next_blue.png");
}
