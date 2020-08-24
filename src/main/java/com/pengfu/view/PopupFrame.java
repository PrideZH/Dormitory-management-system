package com.pengfu.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.pengfu.entity.Admin;
import com.pengfu.entity.Building;
import com.pengfu.entity.Dorm;
import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.service.AdminService;
import com.pengfu.service.BuildingService;
import com.pengfu.service.DormService;
import com.pengfu.service.StudentService;
import com.pengfu.util.ConstantConfig;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.TitleComboBox;
import com.pengfu.view.component.TitleInputBox;
import com.pengfu.view.page.AdminListPage;
import com.pengfu.view.page.BuildingListPage;
import com.pengfu.view.page.DormListPage;
import com.pengfu.view.page.StudentListPage;

/**
 * 弹窗界面
 * 用于添加、修改信息
* @author PrideZH
*/
@Controller
@Lazy
public class PopupFrame extends BaseFrame {

	private static final long serialVersionUID = 1L;
	
	private CardLayout cardLayout = new CardLayout();
	
	private Map<String, JPanel> panels = new HashMap<>();
	
	private StudentService studentService;
	private AdminService adminService;
	private DormService dormService;
	private BuildingService buildingService;
	
	// 学生信息
	private String studntPassword;
	private TitleInputBox nameInputBox;
	private TitleComboBox genderComboBox;
	private TitleInputBox idCardInputBox;
	private TitleInputBox sidInputBox;
	private TitleInputBox phoneInputBox;
	
	// 楼宇信息
	private TitleInputBox buildingIdInputBox;
	
	// 管理员信息
	private int aid;
	private String password;
	private TitleInputBox adminName;
	private TitleInputBox adminUsername;
	private TitleInputBox adminPhone;
	private TitleComboBox adminRole;

	@Autowired
	public PopupFrame(StudentService studentService, AdminService adminService, DormService dormService, 
			BuildingService buildingService) {
		this.studentService = studentService;
		this.adminService = adminService;
		this.dormService = dormService;
		this.buildingService = buildingService;
		
		setText("添加学生");
		setSize(750, 300);
		setLocationRelativeTo(null);

		initComponents();
	}
	
	/** 初始化组件 */
	private void initComponents() {
		Container contentPane = getContentPane();
		contentPane.setBackground(ConstantConfig.BG_COLOR);
			
		panels.put("addStudent", getAddStudentPanel("添加学生信息"));
		panels.put("setStudent", getSetStudentPanel("设置学生信息"));
		panels.put("addAdmin", getAddAdminPanel("添加管理员信息"));
		panels.put("setAdmin", getSetAdminPanel("设置管理员信息"));
		panels.put("addDorm", getAddDormPanel("添加宿舍信息"));
		//panels.put("setDorm", getSetDormPanel("设置宿舍信息"));
		panels.put("addBuilding", getAddBuildingPanel("添加楼宇信息"));
		panels.put("setBuilding", getSetBuildingPanel("设置楼宇信息"));
	
		// 添加面板于卡片布局中
		contentPane.setLayout(cardLayout);
		for (Entry<String, JPanel> entry : panels.entrySet()) { 
			contentPane.add(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * 显示添加界面
	 * @param name 界面名
	 */
	public void showAddPane(String name) {
		setText(panels.get(name).getName());
		cardLayout.show(getContentPane(), name);
	}
	
	/**
	 * 显示学生信息设置界面
	 * @param name 界面名
	 * @param student 欲设置信息
	 */
	public void showSetPane(String name, Student student) {
		showAddPane(name);
		studntPassword = student.getPassword();
		nameInputBox.setText(student.getName());
		genderComboBox.setText(student.getGender());
		idCardInputBox.setText(student.getIdCard());
		sidInputBox.setText(student.getSid());
		phoneInputBox.setText(student.getPhone());
	}
	
	/**
	 * 显示管理员信息设置界面
	 * @param name 界面名
	 * @param student 欲设置信息
	 */
	public void showSetPane(String name, Admin admin) {
		aid = admin.getAid();
		password = admin.getPassword();
		adminName.setText(admin.getName());
		adminUsername.setText(admin.getUsername());
		adminPhone.setText(admin.getPhone());
		adminRole.setText(admin.getRole() == 1 ? "超级管理员" : "普通管理员");
		showAddPane(name);
	}
	
//	/**
//	 * 显示宿舍信息设置界面
//	 * @param name 界面名
//	 * @param student 欲设置信息
//	 */
//	public void showSetPane(String name, Dorm dorm) {
//		showAddPane(name);
//	}
	
	/**
	 * 显示楼宇信息设置界面
	 * @param name 界面名
	 * @param student 欲设置信息
	 */
	public void showSetPane(String name, Building building) {
		buildingIdInputBox.setText(building.getBid());
		showAddPane(name);
	}
	
	/**
	 * 初始化添加学生信息界面
	 * @param title 界面标题
	 * @return 界面面板
	 */
	private JPanel getAddStudentPanel(String title) {
		JPanel pane = new JPanel();
		pane.setBackground(ConstantConfig.BG_COLOR);
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 5));
		pane.setName(title);
		
		// 姓名
		TitleInputBox nameInputBox = new TitleInputBox("姓名");
		pane.add(nameInputBox);
		
		// 性别
		String[] genderList = new String[] {"男", "女"};
		TitleComboBox genderComboBox = new TitleComboBox("性别");
		genderComboBox.setModel(genderList);
		pane.add(genderComboBox);	
				
		// 身份证号
		TitleInputBox idCardInputBox = new TitleInputBox("身份证号");
		pane.add(idCardInputBox);
		
		// 学号
		TitleInputBox sidInputBox = new TitleInputBox("学号");
		pane.add(sidInputBox);
		
		// 联系电话
		TitleInputBox phoneInputBox = new TitleInputBox("联系电话");
		pane.add(phoneInputBox);
		
		// 学院
		String[] collegeList = new String[] {"计算机工程学院", "土木工程学院"};
		TitleComboBox collegeComboBox = new TitleComboBox("学院");
		collegeComboBox.setModel(collegeList);
		pane.add(collegeComboBox);	
		
		// 班级
		String[] classesList = new String[] {"网络工程一班", "网络工程二班"};
		TitleComboBox classesComboBox = new TitleComboBox("班级");
		classesComboBox.setModel(classesList);
		pane.add(classesComboBox);		
		
		// 楼宇列表
		TitleComboBox buildingIdComboBox = new TitleComboBox("楼宇编号");
		// 超级管理员可选择任一楼宇
		if(Role.getRole() == Role.SuperManage) {
			buildingIdComboBox.setModel(buildingService.getAllId());
		}else {
			buildingIdComboBox.setModel(Role.getAdmin().getBids());
		}
		pane.add(buildingIdComboBox);
		
		// 宿舍号
		TitleComboBox dormComboBox = new TitleComboBox("宿舍");
		// 根据楼宇编号修改宿舍号下拉列表数据
		dormComboBox.setModel(dormService.getAllNumberByBid(buildingIdComboBox.getText()));
		pane.add(dormComboBox);		
		
		// 添加按钮
		AppButton addBtn = new AppButton("添加", ConstantConfig.ADD_IMG);
		pane.add(addBtn);
		
		// 监听器
		// 楼宇下拉列表
		buildingIdComboBox.addActionListener((e) -> {
			// 根据楼宇编号修改宿舍号下拉列表数据
			dormComboBox.setModel(dormService.getAllNumberByBid(buildingIdComboBox.getText()));
		});
		// 添加按钮
		addBtn.addActionListener((e) -> {
			// 创建学生对象
			Student student = new Student();
			student.setName(nameInputBox.getText());
			student.setGender(genderComboBox.getText());
			student.setIdCard(idCardInputBox.getText());
			student.setSid(sidInputBox.getText());
			student.setPhone(phoneInputBox.getText());
			student.setCollege(collegeComboBox.getText());
			student.setClasses(classesComboBox.getText());
			student.setBid(buildingIdComboBox.getText());
			student.setDormName(dormComboBox.getText());
			// 添加到数据库
			try {
				studentService.addStudent(student);
				SpringContextUtils.getBean(StudentListPage.class).updateTable();
				JOptionPane.showMessageDialog(null, "添加成功");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		return pane;
	}

	/** 添加管理员信息界面 */
	private JPanel getAddAdminPanel(String title) {
		JPanel pane = new JPanel();
		pane.setBackground(ConstantConfig.BG_COLOR);
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 5));
		pane.setName(title);
		
		// 姓名
		TitleInputBox nameInputBox = new TitleInputBox("姓名");
		pane.add(nameInputBox);
		
		// 用户名
		TitleInputBox usernameBox = new TitleInputBox("用户名");
		pane.add(usernameBox);
		
		// 联系电话
		TitleInputBox phoneInputBox = new TitleInputBox("联系电话");
		pane.add(phoneInputBox);
		
		// 权限
		String[] roleList = new String[] {"普通管理员", "超级管理员"};
		TitleComboBox roleComboBox = new TitleComboBox("权限");
		roleComboBox.setModel(roleList);
		pane.add(roleComboBox);	
		
		// 添加按钮
		AppButton addBtn = new AppButton("添加", ConstantConfig.ADD_IMG);
		pane.add(addBtn);

		// 添加按钮
		addBtn.addActionListener((e) -> {
			// 创建管理员对象
			Admin admin = new Admin();
			admin.setUsername(usernameBox.getText());
			admin.setPassword(usernameBox.getText());
			admin.setName(nameInputBox.getText());
			admin.setPhone(phoneInputBox.getText());
			String role = roleComboBox.getText();
			admin.setRole(role.equals("超级管理员") ? 1 : 0);
			// 添加到数据库
			try {
				adminService.addAdmin(admin);
				SpringContextUtils.getBean(AdminListPage.class).updateTable();
				JOptionPane.showMessageDialog(null, "添加成功");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		return pane;
	}
	
	/** 添加宿舍信息界面 */
	private JPanel getAddDormPanel(String title) {
		JPanel pane = new JPanel();
		pane.setBackground(ConstantConfig.BG_COLOR);
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 5));
		pane.setName(title);
		
		// 楼宇编号
		TitleComboBox buildingIdComboBox = new TitleComboBox("楼宇编号");
		buildingIdComboBox.setModel(Role.getAdmin().getBids());
		pane.add(buildingIdComboBox);
		
		// 宿舍号
		TitleInputBox DormNumberInputBox = new TitleInputBox("宿舍号");
		pane.add(DormNumberInputBox);
		
		// 添加按钮
		AppButton addBtn = new AppButton("添加", ConstantConfig.ADD_IMG);
		pane.add(addBtn);

		// 添加按钮
		addBtn.addActionListener((e) -> {
			// 创建宿舍对象
			Dorm dorm = new Dorm();
			dorm.setBid(buildingIdComboBox.getText());
			dorm.setNumber(DormNumberInputBox.getText());
			// 添加到数据库
			try {
				dormService.addDormitoryt(dorm);
				SpringContextUtils.getBean(DormListPage.class).updateTable();
				JOptionPane.showMessageDialog(null, "添加成功");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		return pane;
	}
	
	/** 添加楼宇信息界面 */
	private JPanel getAddBuildingPanel(String title) {
		JPanel pane = new JPanel();
		pane.setBackground(ConstantConfig.BG_COLOR);
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 5));
		pane.setName(title);
		
		// 楼宇编号
		TitleInputBox buildingIdInputBox = new TitleInputBox("楼宇编号");
		pane.add(buildingIdInputBox);
		
		// 管理员
		TitleComboBox adminComboBox = new TitleComboBox("管理员");
		// 获得所有管理员
		List<Admin> admins = adminService.getAll();
		// 获得所有管理员姓名
		List<String> adminNames = new ArrayList<>();
		for(Admin admin : admins) {
			adminNames.add(admin.getName());
		}
		adminComboBox.setModel(adminNames);
		pane.add(adminComboBox);	
		
		// 添加按钮
		AppButton addBtn = new AppButton("添加", ConstantConfig.ADD_IMG);
		pane.add(addBtn);
		
		// 按钮监听
		// 添加按钮
		addBtn.addActionListener((e) -> {
			// 创建楼宇对象
			Building building = new Building();
			building.setBid(buildingIdInputBox.getText());
			for(Admin admin : admins) {
				if(admin.getName().equals(adminComboBox.getText())) {
					building.setAdmin(admin);
				}
			}
			// 添加到数据库
			try {
				SpringContextUtils.getBean(BuildingService.class).addBuilding(building);
				SpringContextUtils.getBean(BuildingListPage.class).updateTable();
				JOptionPane.showMessageDialog(null, "添加成功");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		return pane;
	}
	
	/** 设置学生信息界面 */
	private JPanel getSetStudentPanel(String title) {
		JPanel pane = new JPanel();
		pane.setBackground(ConstantConfig.BG_COLOR);
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 5));
		pane.setName(title);

		// 姓名
		nameInputBox = new TitleInputBox("姓名");
		nameInputBox.setEnabled(false);
		pane.add(nameInputBox);
		
		// 性别
		String[] genderList = new String[] {"男", "女"};
		genderComboBox = new TitleComboBox("性别");
		genderComboBox.setModel(genderList);
		genderComboBox.setEnabled(false);
		pane.add(genderComboBox);	
				
		// 身份证号
		idCardInputBox = new TitleInputBox("身份证号");
		idCardInputBox.setEnabled(false);
		pane.add(idCardInputBox);
		
		// 学号
		sidInputBox = new TitleInputBox("学号");
		sidInputBox.setEnabled(false);
		pane.add(sidInputBox);
		
		// 联系电话
		phoneInputBox = new TitleInputBox("联系电话");
		phoneInputBox.setEnabled(false);
		pane.add(phoneInputBox);
	
		// 学院
		String[] collegeList = new String[] {"计算机工程学院", "土木工程学院"};
		TitleComboBox collegeComboBox = new TitleComboBox("学院");
		collegeComboBox.setModel(collegeList);
		pane.add(collegeComboBox);	
				
		// 班级
		String[] classesList = new String[] {"网络工程一班", "网络工程二班"};
		TitleComboBox classesComboBox = new TitleComboBox("班级");
		classesComboBox.setModel(classesList);
		pane.add(classesComboBox);	
				
		// 楼宇列表
		TitleComboBox buildingIdComboBox = new TitleComboBox("楼宇编号");
		buildingIdComboBox.setModel(buildingService.getAllId());
		pane.add(buildingIdComboBox);
		
		// 宿舍号
		TitleComboBox dormComboBox = new TitleComboBox("宿舍");
		// 根据楼宇编号修改宿舍号下拉列表数据
		dormComboBox.setModel(dormService.getAllNumberByBid(buildingIdComboBox.getText()));
		pane.add(dormComboBox);	
		
		// 修改按钮
		AppButton setBtn = new AppButton("修改", ConstantConfig.SET_IMG);
		pane.add(setBtn);
		
		// 监听器
		// 楼宇下拉列表
		buildingIdComboBox.addActionListener((e) -> {
			// 根据楼宇编号修改宿舍号下拉列表数据
			dormComboBox.setModel(dormService.getAllNumberByBid(buildingIdComboBox.getText()));
		});
		
		// 修改按钮
		setBtn.addActionListener((e) -> {
			// 创建学生对象
			Student student = new Student();
			student.setPassword(studntPassword);
			student.setName(nameInputBox.getText());
			student.setGender(genderComboBox.getText());
			student.setIdCard(idCardInputBox.getText());
			student.setSid(sidInputBox.getText());
			student.setPhone(phoneInputBox.getText());
			student.setCollege(collegeComboBox.getText());
			student.setClasses(classesComboBox.getText());
			student.setBid(buildingIdComboBox.getText());
			student.setDormName(dormComboBox.getText());
			// 添加到数据库
			try {
				studentService.update(student);
				SpringContextUtils.getBean(StudentListPage.class).updateTable();
				JOptionPane.showMessageDialog(null, "修改成功");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		return pane;
	}
	
	/** 设置管理员信息界面 */
	private JPanel getSetAdminPanel(String title) {
		JPanel pane = new JPanel();
		pane.setBackground(ConstantConfig.BG_COLOR);
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 5));
		pane.setName(title);
		
		// 姓名
		adminName = new TitleInputBox("姓名");
		pane.add(adminName);
		
		// 用户名
		adminUsername = new TitleInputBox("用户名");
		adminUsername.setEnabled(false);
		pane.add(adminUsername);
		
		// 联系电话
		adminPhone = new TitleInputBox("联系电话");
		pane.add(adminPhone);
		
		// 权限
		String[] roleList = new String[] {"普通管理员", "超级管理员"};
		adminRole = new TitleComboBox("权限");
		adminRole.setModel(roleList);
		pane.add(adminRole);	
		
		// 修改按钮
		AppButton setBtn = new AppButton("修改", ConstantConfig.SET_IMG);
		pane.add(setBtn);

		// 修改按钮
		setBtn.addActionListener((e) -> {
			// 创建管理员对象
			Admin admin = new Admin();
			admin.setAid(aid);
			admin.setPassword(password);
			admin.setUsername(adminUsername.getText());
			admin.setPhone(adminPhone.getText());
			admin.setName(adminName.getText());
			admin.setRole(adminRole.getText().equals("超级管理员") ? 1 : 0);
			try {
				adminService.update(admin);
				SpringContextUtils.getBean(AdminListPage.class).updateTable();
				JOptionPane.showMessageDialog(null, "修改成功");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		return pane;
	}
	
	private JPanel getSetBuildingPanel(String title) {
		JPanel pane = new JPanel();
		pane.setBackground(ConstantConfig.BG_COLOR);
		pane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 5));
		pane.setName(title);
		
		// 楼宇编号
		buildingIdInputBox = new TitleInputBox("楼宇编号");
		buildingIdInputBox.setEnabled(false);
		pane.add(buildingIdInputBox);
		
		// 管理员
		TitleComboBox adminComboBox = new TitleComboBox("管理员");
		// 获得所有管理员
		List<Admin> admins = adminService.getAll();
		// 获得所有管理员姓名
		List<String> adminNames = new ArrayList<>();
		for(Admin admin : admins) {
			adminNames.add(admin.getName());
		}
		adminComboBox.setModel(adminNames);
		pane.add(adminComboBox);	
		
		// 修改按钮
		AppButton setBtn = new AppButton("修改", ConstantConfig.SET_IMG);
		pane.add(setBtn);
		
		// 按钮监听
		setBtn.addActionListener((e) -> {
			// 创建楼宇对象
			Building building = new Building();
			building.setBid(buildingIdInputBox.getText());
			for(Admin admin : admins) {
				if(admin.getName().equals(adminComboBox.getText())) {
					building.setAdmin(admin);
				}
			}
			// 修改
			try {
				SpringContextUtils.getBean(BuildingService.class).update(building);
				SpringContextUtils.getBean(BuildingListPage.class).updateTable();
				JOptionPane.showMessageDialog(null, "修改成功");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		return pane;
	}
	
}
