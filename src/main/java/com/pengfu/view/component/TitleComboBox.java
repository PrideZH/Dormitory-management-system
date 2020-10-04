package com.pengfu.view.component;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.pengfu.util.Constant;

/**
 * 带标题的选择框
 * @author PrideZH
 */
public class TitleComboBox extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;

	public TitleComboBox(String title, int titleSize, int textSize) {
		setBackground(Constant.BG_COLOR);
		setPreferredSize(new Dimension(titleSize + textSize + 20, 40));

		AppLabel titleLab = new AppLabel(title);
		titleLab.setPreferredSize(new Dimension(titleSize, 32));
		add(titleLab);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(Constant.PAGE_FONT_COLOR);
		comboBox.setPreferredSize(new Dimension(textSize, 32));
		comboBox.setBackground(Constant.PAGE_COLOR);
		add(comboBox);
	}
	
	/** 设置数据 */
	public void setModel(String[] list) {
		comboBox.setModel(new DefaultComboBoxModel<String>(list));
	}
	
	/** 设置数据 */
	public void setModel(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		comboBox.setModel(new DefaultComboBoxModel<String>(strings));
	}
	
	/** 设置数据 */
	public void setModel(Set<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		comboBox.setModel(new DefaultComboBoxModel<String>(strings));
	}
	
	/** 添加数据 */
	public void addItem(String item) {
		comboBox.addItem(item);
	}

	/** 添加事件 */
	public void addActionListener(ActionListener l) {
		comboBox.addActionListener(l);
	}
	
	/** 获取输入框内容 */
	public String getText() {
		String text = (String) comboBox.getSelectedItem();
		if("".equals(text)) {
			return null;
		}
		return text;
	}
	
	/** 获得当前选中索引 */
	public int getSelectedIndex() {
		return comboBox.getSelectedIndex();
	}
	
	/** 设置下拉框选项 */
	public void setText(String text) {
		if(text == null) {
			return;
		}
		for(int index = 0; index < comboBox.getItemCount(); ++index) {
			if(text.equals(comboBox.getModel().getElementAt(index))) {
				comboBox.setSelectedIndex(index);
				return;
			}
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		comboBox.setEnabled(enabled);
	}
	
}
