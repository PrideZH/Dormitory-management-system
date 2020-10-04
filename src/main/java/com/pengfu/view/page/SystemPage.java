package com.pengfu.view.page;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.controller.AppControl;
import com.pengfu.model.ThemeModel;
import com.pengfu.model.PersonalModel;
import com.pengfu.model.Role;
import com.pengfu.util.Constant;
import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.component.ImgBtn;
import com.pengfu.view.component.InfoBar;
import com.pengfu.view.component.TitleComboBox;

/**
 * 系统设置界面
 * @author PrideZH
 */
@Component
@Lazy
public class SystemPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public SystemPage() {
		initComponents();
	}

	
	@Override
	protected void initComponents() {
		// 文件默认导入导出路径设置
		if(PersonalModel.getInstance().getRole() != Role.STUDENT) {
			filePathConfig();
		}
		
		// 背景图片设置
		backgroundConfig();
		
		// 主题设置
		themeConfig();
	}
	
	/** 文件默认导入导出路径设置 */
	private void filePathConfig() {
		String filePath = SpringContextUtils.getBean(AppControl.class).readProperties("filePath");
		InfoBar filePathInfoBar = new InfoBar("导出导入文件默认位置", filePath, 1024);
		contxtPane.add(filePathInfoBar);
		
		// 文件路径选择按钮
		ImgBtn fileBtn = new ImgBtn(Constant.FILE_GREY_IMG, 32, 32);
		fileBtn.setRolloverIcon(Constant.FILE_BLUE_IMG);
		contxtPane.add(fileBtn);
		
		fileBtn.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				// 写入配置文件
				SpringContextUtils.getBean(AppControl.class).writeProperties("filePath", file.getPath());
				filePathInfoBar.setText(file.getPath());
			};
		});
	}
	
	/** 背景图片设置 */
	private void backgroundConfig() {
		InfoBar BackgroundInfoBar = new InfoBar("背景图片设置", "", 1024);
		contxtPane.add(BackgroundInfoBar);
		
		// 文件路径选择按钮
		ImgBtn fileBtn = new ImgBtn(Constant.FILE_GREY_IMG, 32, 32);
		fileBtn.setRolloverIcon(Constant.FILE_BLUE_IMG);
		contxtPane.add(fileBtn);
		
		fileBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "功能尚未实现，敬请期待!");
		});
	}

	/** 主题设置 */
	private void themeConfig() {
		TitleComboBox themeComboBox = new TitleComboBox("主题设置", 64, 256);
		themeComboBox.setBackground(Constant.PAGE_COLOR);
		// 设置主题选项
		themeComboBox.setModel(ThemeModel.getInstance().getTheme());
		// 初始化为当前主题
		themeComboBox.setText(SpringContextUtils.getBean(AppControl.class).readProperties("theme"));
		contxtPane.add(themeComboBox);
		themeComboBox.addActionListener(e -> {
			// 写入配置文件
			SpringContextUtils.getBean(AppControl.class).writeProperties("theme", themeComboBox.getText());
			// 设置颜色主题
			ThemeModel.getInstance().setTheme(themeComboBox.getText());
			// 提醒重新登陆
			if(JOptionPane.OK_OPTION == JOptionPane
					.showConfirmDialog(null, "重新登陆生效", "设置系统颜色", JOptionPane.OK_CANCEL_OPTION)) {
				SpringContextUtils.getBean(AppControl.class).logOut();
			}
		});
	}
	
}
