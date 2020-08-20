package com.pengfu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pengfu.controller.MoveFrameEvent;
import com.pengfu.util.Resources;
import com.pengfu.view.component.ImgBtn;

/**
 * 基类窗口
 * 用于美化窗口
 * @author PrideZH
 */
public class BaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int TITLE_PANE_WIDTH = 36;
	
	private Container windowPane; // 窗口面板
	private JPanel titlePane; // 标题栏
	private JPanel center; // 内容面板
	
	private JLabel title; // 窗口标题
	private ImgBtn maximize; // 最大化按钮
	
	public BaseFrame() {
		setUndecorated(true);
		// 默认关闭模式为释放资源
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		initComponents();
		
		// 添加监听器
		// - 实现窗口拖动
		MoveFrameEvent mfe = new MoveFrameEvent(this);
		addMouseListener(mfe);
		addMouseMotionListener(mfe);
	}

	private void initComponents() {
		windowPane = super.getContentPane();
		windowPane.setLayout(new BorderLayout());
		
		// 内容面板
		center = new JPanel();
		center.setBorder(BorderFactory.createLineBorder(new Color(36, 38, 48)));
		windowPane.add(center, BorderLayout.CENTER);
		
		// 初始化标题栏
		initTitleBar();
	}
	
	/** 初始化标题栏 */
	private void initTitleBar() {
		titlePane = new JPanel();
		titlePane.setBackground(new Color(36, 38, 48));
		titlePane.setPreferredSize(new Dimension(0, TITLE_PANE_WIDTH));
		windowPane.add(titlePane, BorderLayout.NORTH);
		titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.X_AXIS));
		
		// 窗口图标
		ImageIcon image = Resources.getImageIcon("images/logo/logo_school.png");
		image.setImage(image.getImage()
				.getScaledInstance(
						TITLE_PANE_WIDTH / 2, TITLE_PANE_WIDTH / 2, Image.SCALE_DEFAULT));
		JLabel jLabel = new JLabel(image);
		jLabel.setPreferredSize(new Dimension(TITLE_PANE_WIDTH, TITLE_PANE_WIDTH));
		titlePane.add(jLabel);
		
		// 窗口标题
		title = new JLabel("");
		title.setFont(new Font("宋体", Font.BOLD, TITLE_PANE_WIDTH / 2));
		title.setForeground(Color.WHITE);
		title.setPreferredSize(new Dimension(256, TITLE_PANE_WIDTH));
		titlePane.add(title, BorderLayout.WEST);

		// 添加水平方向胶状不可见组件
		titlePane.add(Box.createHorizontalGlue());
		
		// 标题栏操作面板
		JPanel box = new JPanel();
		box.setPreferredSize(new Dimension(256, 0));
		box.setOpaque(false);
		titlePane.add(box, BorderLayout.EAST);
		box.setLayout(
				new FlowLayout(FlowLayout.RIGHT, 20, (TITLE_PANE_WIDTH - 16) / 2));
		
		// - 最小化按钮
		ImgBtn minimizeBtn = new ImgBtn("images/button/最小化_灰.png", 16, 16);
		minimizeBtn.setRolloverIcon("images/button/最小化_白.png");
		minimizeBtn.setToolTipText("最小化");
		minimizeBtn.addActionListener((e) -> { setExtendedState(JFrame.ICONIFIED); });
		box.add(minimizeBtn);
		
		// - 最大化按钮
		maximize = new ImgBtn("images/button/最大化_灰.png", 16, 16); 
		maximize.setRolloverIcon("images/button/最大化_白.png");
		maximize.setToolTipText("最大化");
		maximize.addActionListener(new ActionListener() {
			int x, y, w, h; // 最大化时窗口的大小及位置
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getExtendedState() != MAXIMIZED_BOTH) {
					x = getX();
					y = getY();
					w = getWidth();
					h = getHeight();
					setExtendedState(JFrame.MAXIMIZED_BOTH); 
				}else {
					setBounds(x, y, w, h);
				}
			}
		});
		box.add(maximize);
		
		// - 关闭按钮
		ImgBtn exitBtn = new ImgBtn("images/button/关闭_灰.png", 16, 16);
		exitBtn.setRolloverIcon("images/button/关闭_白.png");
		exitBtn.setToolTipText("关闭");
		exitBtn.addActionListener((e) -> { 
			if(getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE) {
				dispose(); 
			}else if(getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE) {
				System.exit(0);
			}
		});
		box.add(exitBtn);
	}

	/** 获得内容面板 */
	@Override
	public Container getContentPane() {
		return center;
	}
	
	/** 设置窗口标题 */
	public void setText(String text) {
		title.setText(text);
	}
	
	/** 设置最大化按钮是否可用 */
	public void setMaximizeEnabled(boolean b) {
		maximize.setEnabled(b);
	}
	
}
