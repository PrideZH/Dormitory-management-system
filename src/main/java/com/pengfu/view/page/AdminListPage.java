package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.model.AdminTableModel;
import com.pengfu.service.AdminService;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.AddAdminFrame;

@Component
@Lazy
public class AdminListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	AdminTableModel model = new AdminTableModel();

	public AdminListPage() {
		// 获取Service对象
		AdminService managerService = SpringContextUtils.getBean("managerService", AdminService.class);
		model.setManagers(managerService.getAll());
		
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(ConstantConfig.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		
		// 添加按钮
		JButton addBtn = new JButton("添加");
		northPane.add(addBtn);
		
		// 楼宇信息列表
		JTable table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(ConstantConfig.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);
		
		// 分页
		JPanel southPane = new JPanel();
		southPane.setBackground(ConstantConfig.PAGE_COLOR);
		southPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(southPane, BorderLayout.SOUTH);
		
		// 监听器 
		addBtn.addActionListener((e) ->{
			SpringContextUtils.getBean(AddAdminFrame.class).setVisible(true);
		});
	}
}