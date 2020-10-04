package com.pengfu.view.page;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.entity.Student;
import com.pengfu.model.PersonalModel;
import com.pengfu.service.StudentService;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.component.InfoBar;

/**
 * 宿舍成员信息页面
 * @author PrideZH
 */
@Component
@Lazy
public class DormInfoPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public DormInfoPage() {
		initComponents();
	}

	@Override
	protected void initComponents() {
		contxtPane.setLayout(new GridLayout(2, 2, 126, 32));
		
		List<Student> students = SpringContextUtils.getBean(StudentService.class)
				.getStudentByDid(PersonalModel.getInstance().getStudent());
		for(Student student : students) {
			contxtPane.add(createInofCard(student));
		}
	}
	
	private JPanel createInofCard(Student student) {
		JPanel studentInfo = new JPanel();
		studentInfo.setBackground(Constant.PAGE_COLOR);
		studentInfo.setBorder(BorderFactory.createLineBorder(Constant.PAGE_BORDER_COLOR, 3));
		
		InfoBar infoBar1 = new InfoBar("姓名", student.getName());
		InfoBar infoBar2 = new InfoBar("学号", student.getSid());
		InfoBar infoBar3 = new InfoBar("学院", student.getCollege());
		InfoBar infoBar4 = new InfoBar("班级", student.getClasses());
		
		studentInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 32));
		studentInfo.add(infoBar1);
		studentInfo.add(infoBar2);
		studentInfo.add(infoBar3);
		studentInfo.add(infoBar4);
		
		return studentInfo;
	}

}
