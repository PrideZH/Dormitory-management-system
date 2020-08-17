package com.pengfu;

import java.awt.EventQueue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.pengfu.view.LoginFrame;

@SpringBootApplication
@MapperScan("com.pengfu.dao")
public class App {
	
	public static void main(String[] args) {
		// 初始化Spring
		new SpringApplicationBuilder(App.class).headless(false).run(args);
		// 显示显示界面
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginFrame().setVisible(true);
			}
		});
		
	}
	
}
