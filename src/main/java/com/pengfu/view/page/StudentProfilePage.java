package com.pengfu.view.page;

import java.awt.FlowLayout;

import javax.swing.JButton;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.view.component.InfoBar;

/** 学生个人信息 */
@Component
@Lazy
public class StudentProfilePage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private Student student;
	
	public StudentProfilePage() {
		student = Role.getStudent();
		
		initComponents();
	}

	@Override
	protected void initComponents() {
		InfoBar nameInfoBar = new InfoBar("姓名", student.getName());
		InfoBar genderInfoBar = new InfoBar("性别", student.getGender());
		InfoBar sidInfoBar = new InfoBar("学号", student.getSid());
		InfoBar collegeInfoBar = new InfoBar("学院", student.getCollege());
		InfoBar classesInfoBar = new InfoBar("班级", student.getClasses());
		InfoBar phoneInfoBar = new InfoBar("联系电话", student.getPhone());
		InfoBar buildingInfoBar = new InfoBar("所在楼宇", student.getBid());
		InfoBar DormitoryInfoBar = new InfoBar("宿舍号", student.getDormName());

		contxtPane.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 32));
		contxtPane.add(nameInfoBar);
		contxtPane.add(genderInfoBar);
		contxtPane.add(sidInfoBar);
		contxtPane.add(collegeInfoBar);
		contxtPane.add(classesInfoBar);
		contxtPane.add(phoneInfoBar);
		contxtPane.add(buildingInfoBar);
		contxtPane.add(DormitoryInfoBar);
		
		JButton setPasswordBtn = new JButton("修改密码");
		contxtPane.add(setPasswordBtn);
	}

}
