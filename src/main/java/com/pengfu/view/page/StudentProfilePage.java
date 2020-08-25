package com.pengfu.view.page;

import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.service.StudentService;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.StringUtil;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.InfoBar;
import com.pengfu.view.component.TitleInputBox;

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
		
		// 修改密码
		JPanel passwordPane = new JPanel();
		passwordPane.setBackground(Constant.PAGE_COLOR);
		contxtPane.add(passwordPane);
		
		TitleInputBox oldPassword = new TitleInputBox("原密码");
		oldPassword.setBackground(Constant.PAGE_COLOR);
		TitleInputBox newPassword1 = new TitleInputBox("新密码");
		newPassword1.setBackground(Constant.PAGE_COLOR);
		TitleInputBox newPassword2 = new TitleInputBox("确认密码");
		newPassword2.setBackground(Constant.PAGE_COLOR);
		
		passwordPane.add(oldPassword);
		passwordPane.add(newPassword1);
		passwordPane.add(newPassword2);		
		
		AppButton setPasswordBtn = new AppButton("修改密码");
		passwordPane.add(setPasswordBtn);
		
		setPasswordBtn.addActionListener(e -> {
			if(student.getPassword().equals(oldPassword.getText())) {
				if(StringUtil.isEmpty(newPassword1.getText()) || StringUtil.isEmpty(newPassword2.getText())) {
					JOptionPane.showMessageDialog(null, "请输入新密码");
					return;
				} else if(newPassword1.getText().equals(newPassword2.getText())) {
					student.setPassword(newPassword1.getText());
					try {
						SpringContextUtils.getBean(StudentService.class).update(student);
						JOptionPane.showMessageDialog(null, "修改成功");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "密码不同");
				}
			}else {
				JOptionPane.showMessageDialog(null, "密码错误");
			}
			oldPassword.setText("");
			newPassword1.setText("");
			newPassword2.setText("");
		});
	}

}
