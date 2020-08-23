package com.pengfu.view.page;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.util.ConstantConfig;
import com.pengfu.view.component.InfoBar;

@Component
@Lazy
public class DormInfoPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public DormInfoPage() {
		initComponents();
	}

	@Override
	protected void initComponents() {
		contxtPane.setLayout(new GridLayout(2, 2, 126, 32));

		contxtPane.add(createInofCard("123"));
		contxtPane.add(createInofCard("222"));
		contxtPane.add(createInofCard("333"));
		contxtPane.add(createInofCard("12443"));
	}
	
	private JPanel createInofCard(String text) {
		JPanel studentInfo = new JPanel();
		studentInfo.setBackground(ConstantConfig.PAGE_COLOR);
		studentInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		InfoBar infoBar1 = new InfoBar("姓名", text);
		InfoBar infoBar2 = new InfoBar("学号", text);
		InfoBar infoBar3 = new InfoBar("学院", text);
		InfoBar infoBar4 = new InfoBar("班级", text);
		
		studentInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 32));
		studentInfo.add(infoBar1);
		studentInfo.add(infoBar2);
		studentInfo.add(infoBar3);
		studentInfo.add(infoBar4);
		
		return studentInfo;
	}

}
