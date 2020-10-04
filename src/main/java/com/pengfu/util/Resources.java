package com.pengfu.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
  * 资源管理工具
 * @author PrideZH
 */
public class Resources {
	
	private Resources() {}
	
	/** 获得资源io输入流 */
	public static InputStream getInputStream(String path) {
		return Resources.class.getClassLoader().getResourceAsStream(path);
	}
	
	/** 通过路径获得图片资源 */
	public static ImageIcon getImageIcon(String imgPath) {
		try {
			return new ImageIcon(ImageIO.read(getInputStream(imgPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 通过路径获得图片资源 */
	public static BufferedImage getBufferedImage(String imgPath) {
		try {
			return ImageIO.read(getInputStream(imgPath));
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/** 获得指定大小图片 */
	public static ImageIcon getScaledIcon(String imgPath, int maxSize) {
		BufferedImage image = getBufferedImage(imgPath);
		int w = image.getWidth();
		int h = image.getHeight();
		if(w > h) {
			h = h  * maxSize / w; 
			w = maxSize;
		} else {
			w = w  * maxSize/ h; 
			h = maxSize;
		}
		return new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_FAST));
	}
	
}
