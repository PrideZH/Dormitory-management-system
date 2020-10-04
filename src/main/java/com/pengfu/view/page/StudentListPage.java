package com.pengfu.view.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.pengfu.controller.AppControl;
import com.pengfu.entity.Student;
import com.pengfu.model.PersonalModel;
import com.pengfu.model.table.StudentTableModel;
import com.pengfu.service.DormService;
import com.pengfu.service.StudentService;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.TableBuilder;
import com.pengfu.view.PopupFrame;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.AppLabel;
import com.pengfu.view.component.ImgBtn;
import com.pengfu.view.component.TitleComboBox;
import com.pengfu.view.component.TitleInputBox;

/**
 * 学生信息管理页面
 * @author PrideZH
 */
@Component
@Lazy
public class StudentListPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private StudentTableModel studentModel = new StudentTableModel();
	private JTable table;

	private StudentService studentService;

	// 搜索栏
	public TitleComboBox bidComboBox;
	public TitleComboBox dormNameBox;
	private TitleInputBox sidInputBox;
	private TitleInputBox nameInputBox;

	// 分页栏
	private AppLabel pageTotalLbl;
	private AppLabel pageNumLbl;
	private JSpinner pageSizeSpinner;
	private JTextField gotoPageNumField;
	private PageInfo<Student> pageInfo;

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
		table = SpringContextUtils.getBean(TableBuilder.class).build(studentModel);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.getViewport().setBackground(Constant.PAGE_COLOR);
		contxtPane.add(tablePane, BorderLayout.CENTER);

		// 分页栏
		initPaginationBar();

		// 显示表格数据
		updateTable();

		// 监听器
		// 导出文件
		exportBtn.addActionListener(e -> {
			SpringContextUtils.getBean(AppControl.class).exportStudentInfo(studentModel.getList());
		});
		// 导入文件
		importBtn.addActionListener(e -> {
			SpringContextUtils.getBean(AppControl.class).importStudentInfo();
			// 刷新
			updateTable();
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
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			// 获得欲修改学生信息
			Student student = studentModel.get(row);
			// 显示修改信息面板
			PopupFrame popupFrame = SpringContextUtils.getBean(PopupFrame.class);
			popupFrame.showSetPane("setStudent", student);
			popupFrame.setVisible(true);
		});
		// 删除
		deleteBtn.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "未选择目标");
				return;
			}
			if (JOptionPane.showConfirmDialog(null, "确定删除此学生?", "删除",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if (studentService.delete((String) studentModel.getValueAt(row, 2)) > 0) {
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
		topPane.setPreferredSize(new Dimension(0, 64));
		topPane.setBackground(Constant.PAGE_COLOR);
		topPane.setBorder(BorderFactory.createLineBorder(Constant.PAGE_BORDER_COLOR, 1));
		topPane.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 5));
		add(topPane, 0);
		// 搜索栏
		// 楼宇
		bidComboBox = new TitleComboBox("楼宇", 64, 128);
		bidComboBox.setBackground(Constant.PAGE_COLOR);
		// 获得管理的楼宇列表
		ArrayList<String> bidList = new ArrayList<String>(PersonalModel.getInstance().getAdmin().getBids());
		// 如果为超级管理员则可查询所有学生
		// 包括暂时还无入住宿舍楼的所有学生
		if (1 == PersonalModel.getInstance().getAdmin().getRole()) {
			// 使用null查询代表不以楼宇编号为条件查询
			bidList.add(0, null);
		}
		bidComboBox.setModel(bidList);
		topPane.add(bidComboBox);
		// 宿舍号
		dormNameBox = new TitleComboBox("宿舍号", 64, 128);
		dormNameBox.setBackground(Constant.PAGE_COLOR);
		DormService dormService = SpringContextUtils.getBean(DormService.class);
		ArrayList<String> dormNameList = new ArrayList<String>(dormService.getAllNumberByBid(bidComboBox.getText()));
		dormNameList.add(0, null); // 代表所有宿舍号
		dormNameBox.setModel(dormNameList);
		topPane.add(dormNameBox);
		// 学号
		sidInputBox = new TitleInputBox("学号", 64, 128);
		sidInputBox.setBackground(Constant.PAGE_COLOR);
		topPane.add(sidInputBox);
		// 姓名
		nameInputBox = new TitleInputBox("姓名", 64, 128);
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
		searchBtn.addActionListener(e -> updateTable());
	}

	/** 初始化分页栏 */
	private void initPaginationBar() {
		JPanel southPane = new JPanel();
		southPane.setBackground(Constant.PAGE_COLOR);
		southPane.setPreferredSize(new Dimension(0, 32));
		contxtPane.add(southPane, BorderLayout.SOUTH);
		southPane.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 4));
		// 显示数量
		pageTotalLbl = new AppLabel();
		southPane.add(pageTotalLbl);
		// 每页数量
		pageSizeSpinner = new JSpinner(new SpinnerNumberModel(20, 10, 1000, 5));
		southPane.add(pageSizeSpinner);
		// 上一页
		ImgBtn backBtn = new ImgBtn(Constant.BACK_BLACK_IMG, 32, 32);
		backBtn.setRolloverIcon(Constant.BACK_BLUE_IMG);
		backBtn.setSelectedIcon(Constant.BACK_BLUE_IMG);
		southPane.add(backBtn);
		// 页数显示
		pageNumLbl = new AppLabel("1");
		southPane.add(pageNumLbl);
		// 下一页
		ImgBtn nextBtn = new ImgBtn(Constant.NEXT_BLACK_IMG, 32, 32);
		nextBtn.setRolloverIcon(Constant.NEXT_BLUE_IMG);
		nextBtn.setSelectedIcon(Constant.NEXT_BLUE_IMG);
		southPane.add(nextBtn);
		// 页数跳转
		gotoPageNumField = new JTextField("1", 3);
		southPane.add(gotoPageNumField);

		// 监听器
		backBtn.addActionListener(e -> {
			updateTable();
			// 是否有上一页
			Integer pageNum = Integer.valueOf(pageNumLbl.getText());
			if (pageInfo.isHasPreviousPage()) {
				pageNumLbl.setText(String.valueOf(pageNum - 1));
				updateTable();
			}
			// 由特殊情况超过最大页面时，跳转最后一个页面
			if (pageNum > pageInfo.getNavigateLastPage()) {
				pageNumLbl.setText(String.valueOf(pageInfo.getNavigateLastPage()));
				updateTable();
			}
		});
		nextBtn.addActionListener(e -> {
			updateTable();
			// 是否有下一页
			Integer pageNum = Integer.valueOf(pageNumLbl.getText());
			if (pageInfo.isHasNextPage()) {
				pageNumLbl.setText(String.valueOf(pageNum + 1));
				updateTable();
			} else if (pageNum > pageInfo.getNavigateLastPage()) {
				pageNumLbl.setText(String.valueOf(pageInfo.getNavigateLastPage()));
				updateTable();
			}
		});
		gotoPageNumField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				gotoPageNum();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		gotoPageNumField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				gotoPageNum();
			}
		});
	}

	/** 跳转页面 */
	private void gotoPageNum() {
		if (Pattern.compile("[0-9]*").matcher(gotoPageNumField.getText()).matches()) {
			if (Integer.valueOf(gotoPageNumField.getText()) < pageInfo.getNavigateLastPage()) {
				pageNumLbl.setText(gotoPageNumField.getText());
				updateTable();
			} else {
				// 跳转页面超过最大页面时跳转到最后一页
				pageNumLbl.setText(String.valueOf(pageInfo.getNavigateLastPage()));
				updateTable();
			}
		} else {
			JOptionPane.showMessageDialog(null, "格式错误");
			gotoPageNumField.setText(pageNumLbl.getText());
		}
	}

	/**
	 * 更新表格数据 数据根据搜索栏信息从数据库获得
	 */
	public void updateTable() {
		// 设置查询条件
		Student student = new Student();
		student.setBid(bidComboBox.getText());
		student.setSid(sidInputBox.getText());
		student.setName(nameInputBox.getText());
		student.setDormName(dormNameBox.getText());
		// 分页查询获得结果
		pageInfo = studentService.search(student, Integer.valueOf(pageNumLbl.getText()),
				(int) pageSizeSpinner.getValue());
		pageTotalLbl.setText("共" + pageInfo.getTotal() + "条数据");
		studentModel.setList(pageInfo.getList());
		table.updateUI();
	}

}
