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

import com.pengfu.model.DormTableModel;
import com.pengfu.model.Role;
import com.pengfu.service.DormService;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.PopupFrame;
import com.pengfu.view.component.TitleComboBox;

@Component
@Lazy
public class DormListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private TitleComboBox buildingComboBox;
	
	private DormTableModel model = new DormTableModel();
	private JTable table;
	
	DormService dormService;
	
	@Autowired
	public DormListPage(DormService dormService) {
		this.dormService = dormService;

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
		// 楼宇
		buildingComboBox = new TitleComboBox("楼宇");
		buildingComboBox.setModel(Role.getAdmin().getBids());
		topPane.add(buildingComboBox);
		
		// 分隔
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
//		JButton setBtn = new JButton("修改");
//		northPane.add(setBtn);
		JButton deleteBtn = new JButton("删除");
		northPane.add(deleteBtn);
		JButton updateBtn = new JButton("刷新");
		northPane.add(updateBtn);
		
		// 宿舍信息列表
		model.setDorms(dormService.getDormitoryByBid(buildingComboBox.getText()));
		table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(ConstantConfig.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);
		
		// 监听器 
		buildingComboBox.addActionListener(e -> {
			updateTable();
		});
		addBtn.addActionListener((e) ->{
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showAddPane("addDorm");
			popupFrame.setVisible(true);
		});
//		setBtn.addActionListener(e -> {
//			int row = table.getSelectedRow();
//			if(row == -1) {
//				JOptionPane.showMessageDialog(null, "未选择目标");
//				return;
//			}
//			// 获得欲修改宿舍信息
//			Dorm dorm = model.get(row);
//			// 显示修改信息面板
//			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
//			popupFrame.showSetPane("setDorm", dorm);
//			popupFrame.setVisible(true);
//		});
		deleteBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			if(dormService.delete(model.get(row)) > 0) {
				updateTable();
				JOptionPane.showMessageDialog(null, "删除成功");
			}	
		});
		// 刷新
		updateBtn.addActionListener(e -> updateTable());
		
	}
	
	/** 更新表格数据 */
	public void updateTable() {
		model.setDorms(dormService.getDormitoryByBid(buildingComboBox.getText()));
		table.updateUI();
	}
}
