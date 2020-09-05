package com.pengfu.util;

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
	
}
