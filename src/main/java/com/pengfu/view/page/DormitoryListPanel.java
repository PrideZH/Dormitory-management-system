package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pengfu.model.DormitoryTableModel;
import com.pengfu.service.DormitoryService;
import com.pengfu.util.ColorConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;

public class DormitoryListPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	DormitoryTableModel model = new DormitoryTableModel();
	
	public DormitoryListPanel() {
		// 获取Service对象
		DormitoryService dormitoryService = SpringContextUtils.getBean("dormitoryService", DormitoryService.class);
		model.setDormitorys(dormitoryService.getDormitoryByBid("C16"));
		
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// 搜索栏
		JPanel topPane = new JPanel();
		topPane.setPreferredSize(new Dimension(0, 64));
		topPane.setBackground(ColorConfig.PAGE_COLOR);
		topPane.setBorder(BorderFactory.createLineBorder(new Color(65, 113, 156), 1));
		add(topPane);
		
		add(Box.createVerticalStrut(16));
		// 内容栏
		JPanel contxtPane = new JPanel();
		contxtPane.setBackground(ColorConfig.PAGE_COLOR);
		contxtPane.setBorder(BorderFactory.createLineBorder(new Color(65, 113, 156), 1));
		contxtPane.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		add(contxtPane);
		
		contxtPane.setLayout(new BorderLayout());
		
		// 宿舍信息列表
		JTable table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(ColorConfig.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);
	}

}
