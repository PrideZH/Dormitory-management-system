package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.model.DormitoryTableModel;
import com.pengfu.service.DormitoryService;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;

@Component("dormitoryListPage")
@Lazy
public class DormitoryListPage extends BasePage {

	private static final long serialVersionUID = 1L;

	DormitoryTableModel model = new DormitoryTableModel();
	
	public DormitoryListPage() {
		// 获取Service对象
		DormitoryService dormitoryService = SpringContextUtils.getBean("dormitoryService", DormitoryService.class);
		model.setDormitorys(dormitoryService.getDormitoryByBid("C16"));

		initComponents();
	}
	
	@Override
	protected void initComponents() {
		// 搜索栏
		JPanel topPane = new JPanel();
		topPane.setPreferredSize(new Dimension(0, 64));
		topPane.setBackground(ConstantConfig.PAGE_COLOR);
		topPane.setBorder(BorderFactory.createLineBorder(new Color(65, 113, 156), 1));
		add(topPane, 0);
		
		// 分隔
		add(Box.createVerticalStrut(16), 1);
		
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 宿舍信息列表
		JTable table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(ConstantConfig.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);
	}

}
