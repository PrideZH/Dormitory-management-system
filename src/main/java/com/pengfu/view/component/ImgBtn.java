package com.pengfu.view.component;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 图片按钮 
 * @author PrideZH
 */
public class ImgBtn extends JButton {

	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	
	public ImgBtn(ImageIcon img, int width, int height) {
		this.width = width;
		this.height = height;
		
		// 按钮透明
		setContentAreaFilled(false); // 关闭内容绘制
		setFocusPainted(false); // 关闭焦点绘制
		setBorderPainted(false); // 关闭边框绘制
		
		// 设置按钮大小
		setPreferredSize(new Dimension(width, height));
				
		// 设置默认图片
		setIcon(getScaledImage(img));
	}

	/** 设置当光标移动到按钮上时显示的图像 */
	public void setRolloverIcon(ImageIcon img) {
		super.setRolloverIcon(getScaledImage(img));
	}
	
	/** 通获得固定图片大小的ImageIcon对象 */
	private ImageIcon getScaledImage(ImageIcon img) {
		return new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

}

