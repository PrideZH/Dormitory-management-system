package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.controller.AppControl;
import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.model.StudentTableModel;
import com.pengfu.service.DormService;
import com.pengfu.service.StudentService;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.PopupFrame;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.ImgBtn;
import com.pengfu.view.component.TitleComboBox;
import com.pengfu.view.component.TitleInputBox;

@Component
@Lazy
public class StudentListPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private StudentTableModel model = new StudentTableModel();
	private JTable table;
	
	private StudentService studentService;
	
	// 搜索栏
	public TitleComboBox bidComboBox;
	public TitleComboBox dormNameBox;
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
		initSearchBar();

		// 分隔
		add(Box.createVerticalStrut(16), 1);
		
		// 内容栏
		contxtPane.setLayout(new BorderLayout());
		
		// 列表操作
		JPanel northPane = new JPanel();
		northPane.setBackground(Constant.PAGE_COLOR);
		northPane.setPreferredSize(new Dimension(0, 64));	
		contxtPane.add(northPane, BorderLayout.NORTH);
		northPane.setLayout(new BoxLayout(northPane, BoxLayout.X_AXIS));
		JPanel leftPane = new JPanel();
		leftPane.setBackground(Constant.PAGE_COLOR);
		leftPane.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 5));
		northPane.add(leftPane);
		northPane.add(Box.createHorizontalGlue());
		JPanel rightPane = new JPanel();
		rightPane.setBackground(Constant.PAGE_COLOR);
		rightPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 16, 5));
		northPane.add(rightPane);
		// 操作按钮
		AppButton exportBtn = new AppButton("导出文件");
		leftPane.add(exportBtn);
		AppButton importBtn = new AppButton("导入文件");
		leftPane.add(importBtn);
		AppButton addBtn = new AppButton("添加", Constant.ADD_IMG);
		rightPane.add(addBtn);
		AppButton setBtn = new AppButton("修改", Constant.SET_IMG);
		rightPane.add(setBtn);
		AppButton deleteBtn = new AppButton("删除", Constant.DELETE_IMG);
		rightPane.add(deleteBtn);
		AppButton updateBtn = new AppButton("刷新", Constant.UPDATE_IMG);
		rightPane.add(updateBtn);
		
		// 学生信息列表
		table = TableBuilder.getTableBuilder().build(model);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(Constant.PAGE_COLOR);
		updateTable();
		contxtPane.add(tablePane, BorderLayout.CENTER);
		
		// 分页栏
		initPaginationBar();
		
		// 监听器 
		// 导出文件
		exportBtn.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				SpringContextUtils.getBean(AppControl.class).exportStudentFile(file, model.getStudents());
			};
		});
		// 导入文件
		importBtn.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return "xlsx文件(*.xlsx)";
				}
				
				@Override
				public boolean accept(File f) {
			        if(f.isDirectory()) { 
			            return true;  
			        } 
			        if(f.getName().endsWith(".xlsx")) return true;  
			        else return false;  
				}
			});
			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				List<Student> students = SpringContextUtils.getBean(AppControl.class).exportStudentFile(file);
				// 保存数据库
				for(int i = 0, size = students.size(); i < size; ++i) {
					try {
						Student student = students.get(i);
						studentService.addStudent(student);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "错误-行" + i, JOptionPane.OK_OPTION);
					}
				}
				// 刷新
				updateTable();
				JOptionPane.showMessageDialog(null, "导入完成");
			};
		});
		// 添加
		addBtn.addActionListener((e) -> {
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showAddPane("addStudent");
			popupFrame.setVisible(true);
		});
		// 修改
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
		// 删除
		deleteBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			if(JOptionPane.showConfirmDialog(null, "确定删除此学生?", "删除", JOptionPane.YES_NO_OPTION) 
					== JOptionPane.YES_OPTION) {
				if(studentService.delete((String) model.getValueAt(row, 2)) > 0) {
					updateTable();
					JOptionPane.showMessageDialog(null, "删除成功");
				}	
			}
		});
		// 刷新
		updateBtn.addActionListener(e -> updateTable());
	}
	
	/** 初始化搜索栏 */
	private void initSearchBar() {
		JPanel topPane = new JPanel();
		topPane.setPreferredSize(new Dimension(0, 96));
		topPane.setBackground(Constant.PAGE_COLOR);
		topPane.setBorder(BorderFactory.createLineBorder(new Color(65, 113, 156), 1));
		topPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 16, 5));
		add(topPane, 0);
		// 搜索栏
		// 楼宇
		bidComboBox = new TitleComboBox("楼宇");
		bidComboBox.setBackground(Constant.PAGE_COLOR);
		ArrayList<String> bidList = new ArrayList<String>(Role.getAdmin().getBids());
		bidList.add(0, null); // 代表所有楼宇编号
		bidComboBox.setModel(bidList);
		topPane.add(bidComboBox);
		// 宿舍号
		dormNameBox = new TitleComboBox("宿舍号");
		dormNameBox.setBackground(Constant.PAGE_COLOR);
		DormService dormService = SpringContextUtils.getBean(DormService.class);
		ArrayList<String> dormNameList = new ArrayList<String>(dormService.getAllNumberByBid(bidComboBox.getText()));
		dormNameList.add(0, null); // 代表所有宿舍号
		dormNameBox.setModel(dormNameList);
		topPane.add(dormNameBox);
		// 学号
		sidInputBox = new TitleInputBox("学号");
		sidInputBox.setBackground(Constant.PAGE_COLOR);
		topPane.add(sidInputBox);
		// 姓名
		nameInputBox = new TitleInputBox("姓名");
		nameInputBox.setBackground(Constant.PAGE_COLOR);
		topPane.add(nameInputBox);
		// 搜索按钮
		AppButton searchBtn = new AppButton("搜索", Constant.SEARCH_IMG);
		topPane.add(searchBtn);
		
		// 监听器 
		// 楼宇下拉列表
		bidComboBox.addActionListener(e -> {
			// 根据楼宇编号修改宿舍号下拉列表数据
			ArrayList<String> dormName = new ArrayList<String>(dormService.getAllNumberByBid(bidComboBox.getText()));
			dormName.add(0, null); // 代表所有宿舍号
			dormNameBox.setModel(dormName);
		});
		// 搜索
		searchBtn.addActionListener(e -> {
			updateTable();
		});
	}
	
	/** 初始化分页栏 */
	private void initPaginationBar() {
		JPanel southPane = new JPanel();
		southPane.setBackground(Constant.PAGE_COLOR);
		southPane.setPreferredSize(new Dimension(0, 32));	
		contxtPane.add(southPane, BorderLayout.SOUTH);
		southPane.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 4));
		// 显示数量
		JLabel dataNumLbl = new JLabel("共" + studentService.getNumber() + "条数据");
		southPane.add(dataNumLbl);
		// 每页数量
		JSpinner pageNumSpinner = new JSpinner(new SpinnerNumberModel(20, 10, 50, 5));
		southPane.add(pageNumSpinner);
		// 上一页
		ImgBtn backBtn = new ImgBtn(Constant.BACK_BLACK_IMG, 32, 32);
		backBtn.setRolloverIcon(Constant.BACK_BLUE_IMG);
		backBtn.setSelectedIcon(Constant.BACK_BLUE_IMG);
		southPane.add(backBtn);
		// 页数显示
		JLabel pageNumLbl = new JLabel("1");
		southPane.add(pageNumLbl);
		// 下一页
		ImgBtn nextBtn = new ImgBtn(Constant.NEXT_BLACK_IMG, 32, 32);
		nextBtn.setRolloverIcon(Constant.NEXT_BLUE_IMG);
		nextBtn.setSelectedIcon(Constant.NEXT_BLUE_IMG);
		southPane.add(nextBtn);
		// 页数跳转
		JTextField pageNumField = new JTextField(2);
		southPane.add(pageNumField);
		
		// 监听器
		backBtn.addActionListener(e ->{
			
		});
		nextBtn.addActionListener(e ->{
			
		});
		pageNumField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(Pattern.compile("[0-9]*").matcher(pageNumField.getText()).matches()) {
					
				} else {
					JOptionPane.showMessageDialog(null, "格式错误");
					pageNumField.setText(pageNumLbl.getText());
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
		});
	}
	
	/**
	 * 更新表格数据
	 * 数据根据搜索栏信息从数据库获得
	 */
	public void updateTable() {
		Student student = new Student(); 
		student.setBid(bidComboBox.getText());
		student.setSid(sidInputBox.getText());
		student.setName(nameInputBox.getText());
		student.setDormName(dormNameBox.getText());
		model.setStudents(studentService.search(student));
		table.updateUI();
	}
	
}
