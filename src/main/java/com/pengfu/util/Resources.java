package com.pengfu.util;

import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;

/**
  * 资源管理工具
 * @author PrideZH
 */
public class Resources {
	
	/** 通过路径获得图片资源 */
	public static ImageIcon getImageIcon(String imgPath) {
		String resource = Resources.class.getClassLoader().getResource(imgPath).getPath();
		try {
			resource = java.net.URLDecoder.decode(resource,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return new ImageIcon(resource);
	}
}
