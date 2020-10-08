package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.model.table.WarrantyTableModel;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.component.AppButton;

/**
 * 损坏报修信息管理页面
 * @author PrideZH
 */
@Component
@Lazy
public class WarrantyListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private WarrantyTableModel model = new WarrantyTableModel();
	private JTable table;
	
	public WarrantyListPage() {
		initComponents();
	}

	@Override
	protected void initComponents() {
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(Constant.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		northPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 16, 5));
		// 操作按钮
		AppButton completeBtn = new AppButton("完成", Constant.SET_IMG);
		northPane.add(completeBtn);
		AppButton deleteBtn = new AppButton("删除", Constant.DELETE_IMG);
		northPane.add(deleteBtn);
		AppButton updateBtn = new AppButton("刷新", Constant.UPDATE_IMG);
		northPane.add(updateBtn);
		
		// 损坏报修信息列表
		table = SpringContextUtils.getBean(TableBuilder.class).build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(Constant.PAGE_COLOR);	
		contxtPane.add(tablePane, BorderLayout.CENTER);
	}

}
