package com.pengfu.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InputBox extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int FONTSIZE = 24;
	
	JTextField jTextField = null;
	JLabel jLabel = null;

	public InputBox(String text, int columns, boolean showText) {
		// 设置大小
		Dimension dimension = new Dimension(columns * FONTSIZE, FONTSIZE * 5 / 2);
		setSize(dimension);
		setPreferredSize(dimension);
		// 设置背景透明
		setOpaque(false);
		
		setLayout(null);
		
		// 设置字体
		Font font = new Font("宋体", Font.BOLD, FONTSIZE);
		
		// 创建输入框
		if(showText) {
			jTextField = new JTextField();
		}else {
			jTextField = new JPasswordField();
		}
		jTextField.setOpaque(false);
		jTextField.setFont(font);
		jTextField.setBounds(0, FONTSIZE * 3 / 2, columns * FONTSIZE, FONTSIZE);
		jTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		add(jTextField);
		
		// 创建输入框内字体
		jLabel = new JLabel(text);
		jLabel.setFont(font);
		jLabel.setBounds(4, FONTSIZE * 3 / 2, text.length() * FONTSIZE, FONTSIZE);
		add(jLabel);
		
		// 添加监听器
		jTextField.addFocusListener(new FocusListener() {
			
			/** 有焦点时字体向上移动 */
			@Override
			public void focusLost(FocusEvent e) {
				// 内容为空时才产生动画效果
				if(jTextField.getText().equals("")) {
					effect(true);
				}
			}
			
			/** 无焦点时字体向下移动 */
			@Override
			public void focusGained(FocusEvent e) {
				// 内容为空时才产生动画效果
				if(jTextField.getText().equals("")) {
					effect(false);
				}
			}
		});
	}
	
	/**
	 * 平移效果
	 * @param flag 指定平移方向 true-向上 false-向下
	 */
	private void effect(boolean flag) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(flag) {	
					for(int i = 2; i < FONTSIZE * 3 / 2; i += 2) {
						moveEffect(i);
					}	
				}else {
					for(int i = FONTSIZE * 3 / 2; i > 1; i -= 2) {
						moveEffect(i);
					}	
				}
			}
		}).start();
	}
	
	/**
	 * 移动输入框字体位置
	 * @param y
	 */
	private void moveEffect(int y) {
		jLabel.setLocation(4, y);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** 设置边框和字体颜色 */
	public void setColor(Color color) {
		jTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
		jLabel.setForeground(color);
		
	}
	
	/** 获得输入框文本 */
	public String getText() {
		return jTextField.getText();
	}
	
	/** 添加键盘监听 */
	public void addKeyListener(KeyListener l) {
		jTextField.addKeyListener(l);
	}
	
}
