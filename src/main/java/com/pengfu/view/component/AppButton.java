package com.pengfu.view.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import com.pengfu.util.Constant;

/**
 * 自定义样式按钮 
 * @author PrideZH
 */
public class AppButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private boolean entered = false; // 在按钮上
	private boolean pressed = false; // 按下

	private String text;
	private int testX;
	
	private Image img;
	private int imgSize = 16;
	
	public AppButton(String text) {
		this.text = text;
		
		testX = 16;
		
		Dimension dimension = new Dimension(text.length() * 22, 32);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		// 初始化监听器
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				entered = false;
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				entered = true;
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pressed = true;
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pressed = false;
				repaint();
			}
		});
	}

	public AppButton(String text, Image img) {
		this(text);
		this.img = img.getScaledInstance(imgSize, imgSize, Image.SCALE_FAST);;
		
		testX = 38;
		
		setPreferredSize(new Dimension(text.length() * 12 + 64, 32));
	}
	
	public AppButton(String text, int width) {
		this(text);
		Dimension dimension = new Dimension(width, 32);
		setPreferredSize(dimension);
		setSize(dimension);
		testX = (width - text.length() * 12) / 2;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int h = getHeight();
		int w = getWidth();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1, h - 1, 10, 10);
		Shape clip = g2d.getClip();
		g2d.clip(r2d);
		
		if(pressed) {
			g2d.setColor(Constant.BTN_PRESSED_COLOR);
		}else if(entered) {
			g2d.setColor(Constant.BTN_ENTERED_COLOR);
		}else {
			g2d.setColor(Constant.BTN_COLOR);
		}
		
		g2d.fillRect(0, 0, w, h);
		g2d.setClip(clip);
		
		// 绘制图标
		g2d.drawImage(img, 16, (h - imgSize) / 2, null);
		// 绘制文本
		g2d.setColor(Constant.BTN_FONT_COLOR);
		g2d.drawString(text, testX, (h - 12) / 2 + 10);
	}
	
	
}
