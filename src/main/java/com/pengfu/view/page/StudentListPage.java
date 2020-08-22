package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.model.StudentTableModel;
import com.pengfu.service.StudentService;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.AddStudentFrame;

@Component
@Lazy
public class StudentListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private StudentTableModel model = new StudentTableModel();
	private JTable table;
	private StudentService studentService;

	public StudentListPage() {
		// 获取Service对象
		studentService = SpringContextUtils.getBean("studentService", StudentService.class);
		model.setStudents(studentService.getAll());

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
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(ConstantConfig.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		// 添加按钮
		JButton addBtn = new JButton("添加");
		northPane.add(addBtn);
		// 刷新按钮
		JButton updateBtn = new JButton("刷新");
		northPane.add(updateBtn);
		
		// 学生信息列表
		table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(ConstantConfig.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);

		// 监听器 
		// 添加
		addBtn.addActionListener((e) -> {
			SpringContextUtils.getBean(AddStudentFrame.class).setVisible(true);
		});
		// 刷新
		updateBtn.addActionListener(e -> updateTable());
		
	}
	
	/** 更新表格数据 */
	public void updateTable() {
		System.out.println(2);
		model.setStudents(studentService.getAll());
		table.updateUI();
	}
	
}
