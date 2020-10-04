package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Dorm;
import com.pengfu.model.PersonalModel;
import com.pengfu.model.table.DormTableModel;
import com.pengfu.service.DormService;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.MainFrame;
import com.pengfu.view.PopupFrame;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.TitleComboBox;

/**
 * 宿舍信息管理页面
 * @author PrideZH
 */
@Component
@Lazy
public class DormListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private TitleComboBox bidComboBox;
	
	private DormTableModel dormModel = new DormTableModel();
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
		topPane.setBackground(Constant.PAGE_COLOR);
		topPane.setBorder(BorderFactory.createLineBorder(Constant.PAGE_BORDER_COLOR, 1));
		add(topPane, 0);
		// 楼宇
		bidComboBox = new TitleComboBox("楼宇", 64, 128);
		bidComboBox.setBackground(Constant.PAGE_COLOR);
		bidComboBox.setModel(PersonalModel.getInstance().getAdmin().getBids());
		topPane.add(bidComboBox);
		// 搜索按钮
		AppButton searchBtn = new AppButton("搜索", Constant.SEARCH_IMG);
		topPane.add(searchBtn);
		
		// 分隔
		add(Box.createVerticalStrut(16), 1);
		
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(Constant.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		northPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 16, 5));
		// 操作按钮
		AppButton addBtn = new AppButton("添加", Constant.ADD_IMG);
		northPane.add(addBtn);
//		AppButton setBtn = new AppButton("修改", ConstantConfig.SET_IMG);
//		northPane.add(setBtn);
		AppButton deleteBtn = new AppButton("删除", Constant.DELETE_IMG);
		northPane.add(deleteBtn);
		AppButton updateBtn = new AppButton("刷新", Constant.UPDATE_IMG);
		northPane.add(updateBtn);
		
		// 宿舍信息列表
		dormModel.setList(dormService.getDormitoryByBid(bidComboBox.getText()));
		table = SpringContextUtils.getBean(TableBuilder.class).build(dormModel);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(Constant.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);
		
		// 监听器 
		// 搜索
		searchBtn.addActionListener(e -> {
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
			Dorm dorm = dormModel.get(row);
			if(dorm.getStudents().size() > 0) {
				JOptionPane.showMessageDialog(null, "该宿舍有学生，无法删除");
				return;
			}
			if(JOptionPane.showConfirmDialog(null, "确定删除此宿舍?", "删除", JOptionPane.YES_NO_OPTION) 
					== JOptionPane.YES_OPTION) {
				if(dormService.delete(dorm) > 0) {
					updateTable();
					JOptionPane.showMessageDialog(null, "删除成功");
				}	
			}
		});

		// 表格双击
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//实现双击
				if(e.getClickCount() == 2) { 
					Dorm dorm = dormModel.get(table.rowAtPoint(e.getPoint()));
					if(dorm.getStudents().size() == 0) {
						JOptionPane.showMessageDialog(null, "该宿舍无学生");
						return;
					}
					// 设置学生列表信息为该宿舍成员
	                StudentListPage studentListPage = 
	                		SpringContextUtils.getBean("studentListPage", StudentListPage.class);
	                studentListPage.bidComboBox.setText(dorm.getBid());
	                studentListPage.dormNameBox.setText(dorm.getNumber());
	                studentListPage.updateTable();
	                // 跳转页面
	                SpringContextUtils.getBean(MainFrame.class).goToPage("studentListPage");
	                // 设置侧边栏状态
	                SpringContextUtils.getBean(MainFrame.class).sidebar.clickItem("studentListPage");
				}
			}
		});
	}
	
	/** 更新表格数据 */
	public void updateTable() {
		dormModel.setList(dormService.getDormitoryByBid(bidComboBox.getText()));
		table.updateUI();
	}
}
