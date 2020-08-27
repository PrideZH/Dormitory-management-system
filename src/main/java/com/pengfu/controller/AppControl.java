package com.pengfu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;

import com.pengfu.entity.Admin;
import com.pengfu.entity.Student;
import com.pengfu.model.Role;
import com.pengfu.service.AdminService;
import com.pengfu.service.StudentService;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.util.StringUtil;
import com.pengfu.view.LoginFrame;
import com.pengfu.view.MainFrame;

@Controller("appControl")
public class AppControl {

	/** 登陆操作  */
	public void Logint(Role role, String username, String password) {
		if(StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "账号不能为空");
			return;
		}else if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		try {
			switch(role) {
			case STUDENT:
				StudentService studentService = SpringContextUtils.getBean(StudentService.class);
				Student student = studentService.loginQuery(username, password);
				Role.setStudent(student);
				break;
			case GENERAL_ADMIN:
				AdminService adminService = SpringContextUtils.getBean(AdminService.class);
				Admin admin = adminService.loginQuery(username, password);
				Role.setAdmin(admin);
				break;
			default:
				return;
			}
			// 显示主窗口
			SpringContextUtils.getBean(MainFrame.class).setVisible(true);
			// 关闭登陆窗口
			SpringContextUtils.getBean(LoginFrame.class).dispose();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	/** 导出学生信息文件 */
	public void exportStudentFile(File file, List<Student> students) {
		FileOutputStream fos = null;
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("students");
			for(int i = 0, size = students.size(); i < size; ++i) {
				Student student = students.get(i);
				XSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue(student.getName());
				row.createCell(1).setCellValue(student.getSid());
				row.createCell(2).setCellValue(student.getIdCard());
				row.createCell(3).setCellValue(student.getGender());
				row.createCell(4).setCellValue(student.getPhone());
				row.createCell(5).setCellValue(student.getCollege());
				row.createCell(6).setCellValue(student.getClasses());
				row.createCell(7).setCellValue(student.getBid());
				row.createCell(8).setCellValue(student.getDormName());
			}
			fos = new FileOutputStream(file + "/students.xlsx");
			wb.write(fos);
			JOptionPane.showMessageDialog(null, "导出文件成功");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			closeFile(wb, fos, null);
		}
	}
	
	/** 导入学生信息文件 */
	public List<Student> exportStudentFile(File file) {
		List<Student> students = new ArrayList<>();
		FileInputStream fis = null;
		XSSFWorkbook wb = null;
		try {
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			for(int i = 0, size = sheet.getPhysicalNumberOfRows(); i < size; ++i) {
				XSSFRow row = sheet.getRow(i);
				if (null == row) {
			        continue;
			    }
				Student student = new Student();
				student.setName(row.getCell(0).getStringCellValue());
				student.setSid(row.getCell(1).getStringCellValue());
				student.setIdCard(row.getCell(2).getStringCellValue());
				student.setGender(row.getCell(3).getStringCellValue());
				student.setPhone(row.getCell(4).getStringCellValue());
				student.setCollege(row.getCell(5).getStringCellValue());
				student.setClasses(row.getCell(6).getStringCellValue());
				XSSFCell cell1 = row.getCell(7);
				student.setBid(null == cell1 ? null : cell1.getStringCellValue());
				XSSFCell cell2 = row.getCell(8);
				student.setDormName(null == cell2 ? null : cell2.getStringCellValue());
				students.add(student);
			}
			return students;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			closeFile(wb, null, fis);
		}
		return null;
	}

	/** 关闭文件资源 */
	public void closeFile(Workbook wb, FileOutputStream fos, FileInputStream fis) {
		if(fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(wb != null) {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
