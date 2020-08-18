package com.pengfu.view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class SidebarBtn extends JRadioButton {

	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height = 48;

	private String text;
	private Font font;
	private int textH;
	private int textX;
	private int textY;
	
	// 鼠标是否在按钮上
	private boolean entered = false; 
	
	private List<SidebarBtn> items = new ArrayList<>();
	private static ButtonGroup buttonGroup = new ButtonGroup();

	public SidebarBtn(String imgPath, String text, int width) {
		this.text = text;
		this.width = width;
		
		// 设置按钮大小
		setPreferredSize(new Dimension(width, height));
		
		// 设置按钮透明
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		
		// 设置鼠标样式
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// 文本字体、大小、位置处理
		setText(text);
		font = new Font("Microsoft YaHei", Font.BOLD, 16);
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		Rectangle res = font.getStringBounds(text, frc).getBounds();
		textH = res.height;
		textX = width / 6;
		textY = (height - textH) / 2 + textH * 8 / 10;
		
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
		});
	}
	
	public List<SidebarBtn> getItems() {
		return items;
	}
	
	public void setTextX(int textX) {
		this.textX = textX;
	}

	public int getTextX() {
		return textX;
	}
	
	/** 添加子按钮  */
	public void addSideBtnItem(SidebarBtn sideBtnItem) {
		buttonGroup.add(sideBtnItem);
		sideBtnItem.setTextX(sideBtnItem.getTextX() + 20);
		items.add(sideBtnItem);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintBorder(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// 绘制按钮颜色
		if(isSelected()) {
			g2.setColor(new Color(89, 88, 89));
		}else if(entered) {
			g2.setColor(new Color(59, 58, 59));
		}else {
			g2.setColor(new Color(12, 7, 21));
		}
		g2.fillRect(0, 0, width, height);
		
		// 绘制文本
		if(isSelected()) {
			g2.setColor(new Color(56, 156, 255));
		}else if(entered) {
			g2.setColor(new Color(56, 156, 255));
		}else {
			g2.setColor(Color.WHITE);
		}
		g2.setFont(font);
		g2.drawString(text, textX, textY);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buttonGroup == null) ? 0 : buttonGroup.hashCode());
		result = prime * result + (entered ? 1231 : 1237);
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + height;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + textH;
		result = prime * result + textX;
		result = prime * result + textY;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SidebarBtn other = (SidebarBtn) obj;
		if (entered != other.entered)
			return false;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
			return false;
		if (height != other.height)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (textH != other.textH)
			return false;
		if (textX != other.textX)
			return false;
		if (textY != other.textY)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

}
