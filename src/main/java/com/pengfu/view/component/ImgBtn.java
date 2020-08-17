package com.pengfu.view.component;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.pengfu.util.Resources;

public class ImgBtn extends JButton {

	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;

	public ImgBtn(String imgPath, int width, int height) {
		this.width = width;
		this.height = height;
		
		// 按钮透明
		setContentAreaFilled(false); // 关闭内容绘制
		setFocusPainted(false); // 关闭焦点绘制
		setBorderPainted(false); // 关闭边框绘制
		
		// 设置按钮大小
		setPreferredSize(new Dimension(width, height));
		
		// 设置默认图片
		setIcon(getScaledImageByPath(imgPath));
	}
	
	/** 设置当光标移动到按钮上时显示的图像 */
	public void setRolloverIcon(String imgPath) {
		setRolloverIcon(getScaledImageByPath(imgPath));
	}
	
	/** 设置按钮选中时显示的图像 */
	public void setSelectedIcon(String imgPath) {
		setSelectedIcon(getScaledImageByPath(imgPath));
	}
	
	/** 设置按钮按下时显示的图像 */
	public void setPressedIcon(String imgPath) {
		setPressedIcon(getScaledImageByPath(imgPath));
	}
	
	/** 设置按钮不可用时显示的图像 */
	public void setDisabledIcon(String imgPath) {
		setDisabledIcon(getScaledImageByPath(imgPath));
	}
	
	/** 通过图片路径获得固定图片大小的ImageIcon对象 */
	private ImageIcon getScaledImageByPath(String imgPath) {
		ImageIcon image = Resources.getImageIcon(imgPath);
		image.setImage(image.getImage()
				.getScaledInstance(
						this.width, this.height, Image.SCALE_DEFAULT));
		return image;
	}

}

