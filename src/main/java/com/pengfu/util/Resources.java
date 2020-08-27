package com.pengfu.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
  * 资源管理工具
 * @author PrideZH
 */
public class Resources {
	
	private Resources() {}
	
	/** 通过路径获得图片资源 */
	public static ImageIcon getImageIcon(String imgPath) {
		try {
			String resource = Resources.class.getClassLoader().getResource(imgPath).getPath();
			resource = java.net.URLDecoder.decode(resource,"utf-8");
			return new ImageIcon(resource);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/** 通过路径获得图片资源 */
	public static BufferedImage getBufferedImage(String imgPath) {
		try {
			String resource = Resources.class.getClassLoader().getResource(imgPath).getPath();
			resource = java.net.URLDecoder.decode(resource,"utf-8");
			File file = new File(resource);
			return ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/** 获得指定文件夹的所有文件 */
	public static File[] getListFiles(String imgPath) {
		try {
			String resource = Resources.class.getClassLoader().getResource(imgPath).getPath();
			resource = java.net.URLDecoder.decode(resource,"utf-8");
			return new File(resource).listFiles();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
