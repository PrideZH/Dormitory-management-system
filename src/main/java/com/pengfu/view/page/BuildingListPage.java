package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Building;
import com.pengfu.model.BuildingTableModel;
import com.pengfu.service.BuildingService;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.PopupFrame;

@Component
@Lazy
public class BuildingListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private BuildingTableModel model = new BuildingTableModel();
	private JTable table;
	
	private BuildingService buildingService;
	
	@Autowired
	public BuildingListPage(BuildingService buildingService) {
		this.buildingService = buildingService;
		model.setBuildings(buildingService.getAll());

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
		
		// 分割
		add(Box.createVerticalStrut(16), 1);
		
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(ConstantConfig.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		
		// 操作按钮
		JButton addBtn = new JButton("添加");
		northPane.add(addBtn);
		JButton setBtn = new JButton("修改");
		northPane.add(setBtn);
		JButton deleteBtn = new JButton("删除");
		northPane.add(deleteBtn);
		JButton updateBtn = new JButton("刷新");
		northPane.add(updateBtn);
		
		// 楼宇信息列表
		table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(ConstantConfig.PAGE_COLOR);	
		contxtPane.add(tablePane, BorderLayout.CENTER);
		
		// 分页栏
		JPanel southPane = new JPanel();
		southPane.setBackground(ConstantConfig.PAGE_COLOR);
		southPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(southPane, BorderLayout.SOUTH);
		
		// 监听器 
		addBtn.addActionListener((e) ->{
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showAddPane("addBuilding");
			popupFrame.setVisible(true);
		});
		setBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			// 获得欲修改楼宇信息
			Building building = model.get(row);
			// 显示修改信息面板
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showSetPane("setBuilding", building);
			popupFrame.setVisible(true);
		});
		deleteBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			if(buildingService.delete((String) model.getValueAt(row, 1)) > 0) {
				updateTable();
				JOptionPane.showMessageDialog(null, "删除成功");
			}	
		});
		// 刷新
		updateBtn.addActionListener(e -> updateTable());
		
	}
	
	/** 更新表格数据 */
	public void updateTable() {
		model.setBuildings(buildingService.getAll());
		table.updateUI();
	}
}
