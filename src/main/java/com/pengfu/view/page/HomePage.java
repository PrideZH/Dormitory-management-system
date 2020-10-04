package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.util.Constant;
import com.pengfu.util.Resources;
import com.pengfu.view.component.AppLabel;
import com.pengfu.view.component.ImgBtn;

/**
 * 首页
 * @author PrideZH
 */
@Component
@Lazy
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private JPanel picture;
	
	public HomePage() {
		setBorder(BorderFactory.createLineBorder(Constant.PAGE_BORDER_COLOR, 1));
		
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		add(contxtPane);
		
		// 标题栏
		JPanel tiltePanel = new JPanel();
		tiltePanel.setBackground(Constant.PAGE_COLOR);
		tiltePanel.setPreferredSize(new Dimension(0, 64));
		contxtPane.add(tiltePanel, BorderLayout.NORTH);
		
		AppLabel jLabel = new AppLabel("欢迎您使用宿舍信息管理系统");
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
		
		for(int i = 1; i <= 5; i++) {
			picture.add(new AppLabel(Resources.getScaledIcon("images/picture/" + i +".jpg", 1000)));
		}
		
		backBtn.addActionListener(e -> cardLayout.previous(picture));
		nextBtn.addActionListener(e -> cardLayout.next(picture));
		
	}

}
