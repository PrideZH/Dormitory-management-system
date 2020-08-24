package com.pengfu.view.component;

import java.awt.Color;
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

import com.pengfu.util.ConstantConfig;

public class AppButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private boolean entered = false; // 在按钮上
	private boolean pressed = false; // 按下

	private String text;
	private Image img;

	public AppButton(String text, Image img) {
		this.text = text;
		this.img = img;
		
		setPreferredSize(new Dimension(text.length() * 12 + 64, 32));
		
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
			g2d.setColor(ConstantConfig.BTN_PRESSED_COLOR);
		}else if(entered) {
			g2d.setColor(ConstantConfig.BTN_ENTERED_COLOR);
		}else {
			g2d.setColor(ConstantConfig.BTN_COLOR);
		}
		
		g2d.fillRect(0, 0, w, h);
		g2d.setClip(clip);
		
		// 绘制图标
		g2d.drawImage(img, 16, (h - 16) / 2, null);
		// 绘制文本
		g2d.setColor(Color.WHITE);
		g2d.drawString(text, 38, (h - 12) / 2 + 10);
	}
	
	
}
