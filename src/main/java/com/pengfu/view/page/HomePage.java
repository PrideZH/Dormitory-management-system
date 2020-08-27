package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import com.pengfu.util.Constant;
import com.pengfu.util.Resources;
import com.pengfu.view.component.ImgBtn;

/** 首页 */
@Component
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private JPanel picture;
	
	public HomePage() {
		setBorder(BorderFactory.createLineBorder(new Color(65, 113, 156), 1));
		
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		add(contxtPane);
		
		// 标题栏
		JPanel tiltePanel = new JPanel();
		tiltePanel.setPreferredSize(new Dimension(0, 64));
		tiltePanel.setBackground(Color.WHITE);
		contxtPane.add(tiltePanel, BorderLayout.NORTH);
		
		JLabel jLabel = new JLabel("欢迎您使用宿舍信息管理系统");
		jLabel.setFont(new Font("宋体", Font.BOLD, 48));
		tiltePanel.add(jLabel);
		
		// 图片展示
		JPanel pictureShow = new JPanel();
		pictureShow.setBackground(Constant.PAGE_COLOR);
		pictureShow.setLayout(new BorderLayout());
		contxtPane.add(pictureShow, BorderLayout.CENTER);

		ImgBtn backBtn = new ImgBtn(Constant.BACK_WHITE_IMG, 180, 400);
		backBtn.setRolloverIcon(Constant.BACK_GREY_IMG);
		pictureShow.add(backBtn, BorderLayout.WEST);
		ImgBtn nextBtn = new ImgBtn(Constant.NEXT_WHITE_IMG, 180, 400);
		nextBtn.setRolloverIcon(Constant.NEXT_GREY_IMG);
		pictureShow.add(nextBtn, BorderLayout.EAST);
		
		picture = new JPanel();
		picture.setBackground(Constant.PAGE_COLOR);
		CardLayout cardLayout = new CardLayout();
		picture.setLayout(cardLayout);
		pictureShow.add(picture, BorderLayout.CENTER);
		
		File[] listFiles = Resources.getListFiles("images/picture");
		for(File f : listFiles) {
			try {
				BufferedImage image = ImageIO.read(f);
				JLabel label = new JLabel();
				picture.add(label);
				picture.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						label.setIcon(getScaledIcon(image));
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		backBtn.addActionListener(e -> {
			cardLayout.previous(picture);
		});
		nextBtn.addActionListener(e -> {
			cardLayout.next(picture);
		});
		
	}
	
	private ImageIcon getScaledIcon(BufferedImage image) {
		int size = picture.getWidth();
		int w = image.getWidth();
		int h = image.getHeight();
		if(w > h) {
			h = h  * size / w; 
			w = size;
		} else {
			w = w  * size/ h; 
			h = size;
		}
		return new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_FAST));
	}

}
