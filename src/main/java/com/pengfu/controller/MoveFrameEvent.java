package com.pengfu.controller;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MoveFrameEvent extends MouseAdapter {

	private JFrame frame;
	private Point origin = new Point();
	
	public MoveFrameEvent(JFrame frame) {
		this.frame = frame;
	}
	
	/** 鼠标按下获得窗口当前位置 */
	@Override
	public void mousePressed(MouseEvent e) {
		// 窗口最大化时不可拖动
		if(frame.getExtendedState() != Frame.MAXIMIZED_BOTH) {
			origin.x = e.getX();
			origin.y = e.getY();
		}
	}
	
	/** 鼠标拖动窗口设置位置 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// 窗口最大化时不可拖动
		if(frame.getExtendedState() != Frame.MAXIMIZED_BOTH) {
			Point p = frame.getLocation();
			// 设置窗口的位置
			frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
		}
	}
	
}
