package com.pengfu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Controller;

import com.pengfu.App;
import com.pengfu.entity.Admin;
import com.pengfu.entity.Student;
import com.pengfu.model.PersonalModel;
import com.pengfu.model.Role;
import com.pengfu.service.AdminService;
import com.pengfu.service.StudentService;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.LoginFrame;
import com.pengfu.view.MainFrame;

@Controller
public class AppControl {
	
	@Autowired
	private StudentService studentService;

	/** 登陆操作  */
	public void Logint(Role role, String username, String password) {
		LoginFrame loginFrame = SpringContextUtils.getBean(LoginFrame.class);
		
		try {
			switch(role) {
			case STUDENT:
				StudentService studentService = SpringContextUtils.getBean(StudentService.class);
				Student student = studentService.loginQuery(username, password);
				// 设置用户信息
				PersonalModel.getInstance().setStudent(student);
				break;
			case GENERAL_ADMIN:
				AdminService adminService = SpringContextUtils.getBean(AdminService.class);
				Admin admin = adminService.loginQuery(username, password);
				// 设置用户信息
				PersonalModel.getInstance().setAdmin(admin);
				break;
			default:
				return;
			}
			// 启动加载动画
			loginFrame.loadAnimation();
			// 显示主窗口
			SpringContextUtils.getBean(MainFrame.class).setVisible(true);
			// 关闭登陆窗口
			loginFrame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			// 关闭动画加载
			loginFrame.stopLoadAnimation();
		}
	}
	
	/** 导出学生信息文件 */
	public void exportStudentInfo(List<Student> students) {
		JFileChooser fileChooser = new JFileChooser(readProperties("filePath"));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			writeStudentFile(file, students);
		};
	}
	
	/** 将学生信息写入文件 */
	public void writeStudentFile(File file, List<Student> students) {
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
	
	/** 导入学生信息文件放入数据库 */
	public void importStudentInfo() {
		// 选择文件
		JFileChooser fileChooser = new JFileChooser(readProperties("filePath"));
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
		// 导入数据库
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			List<Student> students = readStudentFile(file);
			// 导入消息显示
			JDialog dialog = new JDialog();
			dialog.setSize(300, 300);
			dialog.setLocationRelativeTo(null);
			JTextArea message = new JTextArea();
			JScrollPane messageScrollPane = new JScrollPane(message);
			dialog.add(messageScrollPane);
			dialog.setVisible(true);
			// 保存到数据库
			for(int i = 0, size = students.size(); i < size; ++i) {
				try {
					Student student = students.get(i);
					studentService.addStudent(student);
				} catch (Exception e1) {
					message.append("第" + i + "行错误 : " + e1.getMessage() + "\n");
				}
			}
			message.append("导入完成");
		}
	}
	
	/** 从文件读取学生信息 */
	public List<Student> readStudentFile(File file) {
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
	
	/** 读取配置文件信息 */
	public String readProperties(String key) {
		FileReader fr = null;
		try {
			Properties pro = new Properties();
			File file = new File("config.properties");
			// 文件不存在则创建
			if(!file.exists()) {
				file.createNewFile();
			}
			// 读取配置
			fr = new FileReader(file);
			pro.load(fr);
			return pro.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/** 写配置文件信息 */
	public void writeProperties(String key, String value) {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			Properties pro = new Properties();
			File file = new File("config.properties");
			// 读取文件
			fr = new FileReader(file);
			pro.load(fr);
			// 修改配置
			pro.setProperty(key, value);
			// 写入文件
			fw = new FileWriter("config.properties");
			pro.store(fw, null);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/** 登出 */
	public void logOut() {
		SpringContextUtils.getBean(MainFrame.class).dispose();
		new SpringContextUtils()
			.setApplicationContext(new SpringApplicationBuilder(App.class).headless(false).run(""));
		SpringContextUtils.getBean(LoginFrame.class).setVisible(true);
	}

}
