package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.model.StudentTableModel;
import com.pengfu.service.StudentService;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.PopupFrame;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.TitleComboBox;
import com.pengfu.view.component.TitleInputBox;

@Component
@Lazy
public class StudentListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private StudentTableModel model = new StudentTableModel();
	private JTable table;
	
	private StudentService studentService;
	
	private TitleComboBox bidComboBox;
	private TitleInputBox sidInputBox;
	private TitleInputBox nameInputBox;
	
	@Autowired
	public StudentListPage(StudentService studentService) {
		this.studentService = studentService;

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
		// 搜索栏
		// 楼宇
		bidComboBox = new TitleComboBox("楼宇");
		ArrayList<String> bidList = new ArrayList<String>(Role.getAdmin().getBids());
		bidList.add(0, null); // 代表所有楼宇编号
		bidComboBox.setModel(bidList);
		topPane.add(bidComboBox);
		// 学号
		sidInputBox = new TitleInputBox("学号");
		topPane.add(sidInputBox);
		// 姓名
		nameInputBox = new TitleInputBox("姓名");
		topPane.add(nameInputBox);
		// 搜索按钮
		AppButton searchBtn = new AppButton("搜索", ConstantConfig.SEARCH_IMG);
		topPane.add(searchBtn);

		// 分隔
		add(Box.createVerticalStrut(16), 1);
		
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(ConstantConfig.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		northPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 16, 5));
		// 操作按钮
		AppButton addBtn = new AppButton("添加", ConstantConfig.ADD_IMG);
		northPane.add(addBtn);
		AppButton setBtn = new AppButton("修改", ConstantConfig.SET_IMG);
		northPane.add(setBtn);
		AppButton deleteBtn = new AppButton("删除", ConstantConfig.DELETE_IMG);
		northPane.add(deleteBtn);
		AppButton updateBtn = new AppButton("刷新", ConstantConfig.UPDATE_IMG);
		northPane.add(updateBtn);
		
		// 学生信息列表
		table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(ConstantConfig.PAGE_COLOR);
		updateTable();
		contxtPane.add(tablePane, BorderLayout.CENTER);

		// 监听器 
		// 搜索
		searchBtn.addActionListener(e -> {
			updateTable();
		});
		addBtn.addActionListener((e) -> {
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showAddPane("addStudent");
			popupFrame.setVisible(true);
		});
		setBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			// 获得欲修改学生信息
			Student student = model.get(row);
			// 显示修改信息面板
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showSetPane("setStudent", student);
			popupFrame.setVisible(true);
		});
		deleteBtn.addActionListener(e -> {
			if(JOptionPane.showConfirmDialog(null, "确定删除此学生?", "删除", JOptionPane.YES_NO_OPTION) 
					== JOptionPane.YES_OPTION) {
				int row = table.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "未选择目标");
					return;
				}
				if(studentService.delete((String) model.getValueAt(row, 2)) > 0) {
					updateTable();
					JOptionPane.showMessageDialog(null, "删除成功");
				}	
			}
		});
		updateBtn.addActionListener(e -> updateTable());
		
	}
	
	/** 更新表格数据 */
	public void updateTable() {
		Student student = new Student(); 
		student.setBid(bidComboBox.getText());
		student.setSid(sidInputBox.getText());
		student.setName(nameInputBox.getText());
		model.setStudents(studentService.search(student));
		table.updateUI();
	}
	
}
