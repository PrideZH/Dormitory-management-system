package com.pengfu.view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

import com.pengfu.util.ConstantConfig;
import com.pengfu.util.Resources;

public class SidebarBtn extends JRadioButton {

	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height = 48;
	
	private Image image;
	private Image selectedImg;
	private int imgX;
	private int imgY;

	private String text;
	private Font font;
	private int textH;
	private int textX;
	private int textY;
	
	// 设置提示图标位置
	private int tipsY = (height - 16) / 2;
	
	// 鼠标是否在按钮上
	private boolean entered = false; 
	// 是否为父按钮
	private boolean isSuper = true;
	
	private String pageName; // 绑定Page
	private List<SidebarBtn> items = new ArrayList<>(); // 子按钮
	private static ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * 构造只含文本的侧边栏按钮
	 * @param text 文本
	 * @param pageName 绑定的page名
	 * @param width 宽度
	 */
	public SidebarBtn(String text, String pageName, int width) {
		this.text = text;
		this.pageName = pageName;
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
		textX = width / 5;
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
	
	/**
	 * 构造拥有默认图标的侧边栏按钮
	 * @param imgPath 图片路径
	 */
	public SidebarBtn(String imgPath, String text, String pageName, int width) {
		this(text, pageName, width);
		// 设置图标大小、位置
		image = Resources.getBufferedImage(imgPath);
		image = image.getScaledInstance(24, 24, Image.SCALE_FAST);
		imgX = textX - 32;
		imgY = (height - 24) / 2;
	}
	
	/**
	 * 构造含有图标和文本的侧边栏按钮
	 * 图标包含默认状态和选择状态
	 * @param SelectedimgPath 选中状态的图片路径
	 */
	public SidebarBtn(String imgPath, String SelectedImgPath, String text, String pageName, int width) {
		this(imgPath, text, pageName, width);
		selectedImg = Resources.getBufferedImage(SelectedImgPath);
		selectedImg = selectedImg.getScaledInstance(24, 24, Image.SCALE_FAST);
	}
	
	/** 获得绑定的page名 */
	public String getPageName() {
		return pageName;
	}

	/** 设置绑定的page名 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	/** 获得子按钮 */
	public List<SidebarBtn> getItems() {
		return items;
	}
	
	/** 添加子按钮  */
	public void addSideBtnItem(SidebarBtn sideBtnItem) {
		buttonGroup.add(sideBtnItem);
		sideBtnItem.isSuper = false;
		sideBtnItem.imgX += 32;
		sideBtnItem.textX += 32;
		items.add(sideBtnItem);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintBorder(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// 绘制按钮颜色
		if(isSelected() && !isSuper) {
			g2.setColor(new Color(89, 88, 89));
		}else if(entered) {
			g2.setColor(new Color(59, 58, 59));
		}else {
			g2.setColor(new Color(12, 7, 21));
		}
		g2.fillRect(0, 0, width, height);
		
		// 绘制父按钮上拉下拉提示
		if(isSuper && isSelected()) {
			g2.drawImage(ConstantConfig.UP_IMG, width - 32, tipsY, null);
		}else if(isSuper) {
			g2.drawImage(ConstantConfig.DOWN_IMG, width - 32, tipsY, null);
		}
		
		// 绘制图标
		if(selectedImg != null && isSelected()) {
			g2.drawImage(selectedImg, imgX, imgY, null);
		}else if(image != null){
			g2.drawImage(image, imgX, imgY, null);
		}

		// 绘制文本
		if(isSelected() && !isSuper) {
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
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((pageName == null) ? 0 : pageName.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SidebarBtn other = (SidebarBtn) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (pageName == null) {
			if (other.pageName != null)
				return false;
		} else if (!pageName.equals(other.pageName))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
