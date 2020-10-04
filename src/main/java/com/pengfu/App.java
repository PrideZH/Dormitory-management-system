package com.pengfu;

import java.awt.EventQueue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.pengfu.util.SpringContextUtils;
import com.pengfu.view.LoginFrame;

@SpringBootApplication
@MapperScan("com.pengfu.dao")
public class App {
	
	public static void main(String[] args) {
		// 初始化Spring
		new SpringApplicationBuilder(App.class).headless(false).run(args);
		// 显示登陆界面
		EventQueue.invokeLater(() -> SpringContextUtils.getBean(LoginFrame.class).setVisible(true));
	}
	
}

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//                佛祖保佑      永无BUG    永不修改                                  //
////////////////////////////////////////////////////////////////////
