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
	
	/**
	 * 侧边栏大小
	 */
	public static final int SIDEBAR_WIDTH = 200; // 侧边栏宽度

	/**
	 * 颜色
	 */
	// 窗口
	public static Color TITLE_COLOR = new Color(36, 38, 48); // 标题栏
	// 侧边栏
	public static Color SIDEBAR_COLOR = new Color(57, 62, 75); // 侧边栏颜色
	public static Color SIDEBAR_PARENT_BTN_COLOR = new Color(57, 62, 75); // 父按钮默认颜色
	public static Color SIDEBAR_BTN_ENTERED_COLOR = new Color(32, 45, 60); // 鼠标悬停按钮颜色
	public static Color SIDEBAR_SUBCLASS_BTN_COLOR = new Color(12, 7, 21); //  子按钮默认颜色
	public static Color SIDEBAR_FONT_COLOR = Color.WHITE; // 侧边栏按钮字体默认颜色
	public static Color SIDEBAR_FONT_ENTERED_COLOR = new Color(56, 156, 255); // 侧边栏按钮字体悬停颜色
	// 页面
	public static Color BG_COLOR = new Color(234, 237, 242); // 背景颜色
	public static Color PAGE_COLOR = Color.WHITE; // 页面颜色
	public static Color PAGE_FONT_COLOR = Color.BLACK; // 页面字体默认颜色
	public static Color PAGE_BORDER_COLOR = new Color(65, 113, 156); // 边框颜色
	// 按钮
	public static Color BTN_COLOR = new Color(64, 158, 255); // 按钮默认颜色
	public static Color BTN_ENTERED_COLOR = new Color(102, 177, 255); // 鼠标悬停按钮颜色
	public static Color BTN_PRESSED_COLOR =  new Color(58, 142, 230); // 点击按钮颜色
	public static Color BTN_FONT_COLOR =  Color.WHITE; // 按钮字体颜色
	// 表格
	public static Color TABLE_ODD_COLOR =  new Color(251, 250, 251);
	public static Color TABLE_EVEN_COLOR =  Color.WHITE;
	public static Color TABLE_SELECT_COLOR =  new Color(236, 245, 255); // 选中时颜色

	// 字体
	//public static Font font = new Font("宋体", style, size);
	
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
	// 其他
	public static final BufferedImage MENU_IMG = Resources.getBufferedImage("images/sidebar/menu.png");
	// 其他
	public static final BufferedImage OTHER_IMG = Resources.getBufferedImage("images/sidebar/other.png");
	// 首页
	public static final BufferedImage HOME_GREY_IMG = Resources.getBufferedImage("images/sidebar/home_grey.png");
	public static final BufferedImage HOME_BLUE_IMG = Resources.getBufferedImage("images/sidebar/home_blue.png");
	// 系统设置
	public static final BufferedImage SYSTEM_GREY_IMG = Resources.getBufferedImage("images/sidebar/system_grey.png");
	public static final BufferedImage SYSTEM_BLUE_IMG = Resources.getBufferedImage("images/sidebar/system_blue.png");
	// 个人中心
	public static final BufferedImage PERSONAL_IMG = Resources.getBufferedImage("images/sidebar/personal.png");
	// 宿舍
	public static final BufferedImage DORM_GREY_IMG = Resources.getBufferedImage("images/sidebar/dorm_grey.png");
	public static final BufferedImage DORM_BLUE_IMG = Resources.getBufferedImage("images/sidebar/dorm_blue.png");
	// 个人信息
	public static final BufferedImage PROFILE_GREY_IMG = Resources.getBufferedImage("images/sidebar/profile_grey.png");
	public static final BufferedImage PROFILE_BLUE_IMG = Resources.getBufferedImage("images/sidebar/profile_blue.png");
	// 楼宇
	public static final BufferedImage BUILDING_IMG = Resources.getBufferedImage("images/sidebar/building.png");
	// 列表
	public static final BufferedImage LIST_GREY_IMG = Resources.getBufferedImage("images/sidebar/list_grey.png");
	public static final BufferedImage LIST_BLUE_IMG = Resources.getBufferedImage("images/sidebar/list_blue.png");
	// 管理员
	public static final BufferedImage ADMIN_IMG = Resources.getBufferedImage("images/sidebar/admin.png");
	// 学生
	public static final BufferedImage STUDENT_IMG = Resources.getBufferedImage("images/sidebar/student.png");
	// 后勤服务
	public static final BufferedImage LOGISTICS_IMG = Resources.getBufferedImage("images/sidebar/logistics.png");
	// 报修
	public static final BufferedImage WARRANTY_GREY_IMG = Resources.getBufferedImage("images/sidebar/warranty_grey.png");
	public static final BufferedImage WARRANTY_BLUE_IMG = Resources.getBufferedImage("images/sidebar/warranty_blue.png");
	// 生活服务
	public static final BufferedImage LIFE_IMG = Resources.getBufferedImage("images/sidebar/life.png");
	// 电费
	public static final BufferedImage ELECTRICITY_GREY_IMG = Resources.getBufferedImage("images/sidebar/electricity_grey.png");
	public static final BufferedImage ELECTRICITY_BLUE_IMG = Resources.getBufferedImage("images/sidebar/electricity_blue.png");
	// 网络
	public static final BufferedImage NETWORKT_GREY_IMG = Resources.getBufferedImage("images/sidebar/networkt_grey.png");
	public static final BufferedImage NETWORKT_BLUE_IMG = Resources.getBufferedImage("images/sidebar/networkt_blue.png");
	// 卡
	public static final BufferedImage CARD_GREY_IMG = Resources.getBufferedImage("images/sidebar/card_grey.png");
	public static final BufferedImage CARD_BLUE_IMG = Resources.getBufferedImage("images/sidebar/card_blue.png");
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
	public static final Image ADD_IMG = Resources.getBufferedImage("images/button/add.png");
	// 删除
	public static final Image DELETE_IMG = Resources.getBufferedImage("images/button/delete.png");
	// 查询
	public static final Image SEARCH_IMG = Resources.getBufferedImage("images/button/search.png");
	// 修改
	public static final Image SET_IMG = Resources.getBufferedImage("images/button/set.png");
	// 刷新
	public static final Image UPDATE_IMG = Resources.getBufferedImage("images/button/update.png");
	// 上一页
	public static final ImageIcon BACK_BLACK_IMG = Resources.getImageIcon("images/button/back_black.png");
	public static final ImageIcon BACK_BLUE_IMG = Resources.getImageIcon("images/button/back_blue.png");
	public static final ImageIcon BACK_GREY_IMG = Resources.getImageIcon("images/button/back_grey.png");
	public static final ImageIcon BACK_WHITE_IMG = Resources.getImageIcon("images/button/back_white.png");
	// 下一页
	public static final ImageIcon NEXT_BLACK_IMG = Resources.getImageIcon("images/button/next_black.png");
	public static final ImageIcon NEXT_BLUE_IMG = Resources.getImageIcon("images/button/next_blue.png");
	public static final ImageIcon NEXT_GREY_IMG = Resources.getImageIcon("images/button/next_grey.png");
	public static final ImageIcon NEXT_WHITE_IMG = Resources.getImageIcon("images/button/next_white.png");
	// 文件
	public static final ImageIcon FILE_GREY_IMG = Resources.getImageIcon("images/button/file_grey.png");
	public static final ImageIcon FILE_BLUE_IMG = Resources.getImageIcon("images/button/file_blue.png");
}
