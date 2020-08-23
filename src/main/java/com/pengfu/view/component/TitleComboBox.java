package com.pengfu.view.component;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pengfu.util.ConstantConfig;

public class TitleComboBox extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;

	public TitleComboBox(String title) {
		setBackground(ConstantConfig.BG_COLOR);
		setPreferredSize(new Dimension(320, 40));

		JLabel titleLab = new JLabel(title);
		titleLab.setPreferredSize(new Dimension(70, 32));
		add(titleLab);

		comboBox = new JComboBox<String>();
		comboBox.setPreferredSize(new Dimension(200, 32));
		add(comboBox);
	}
	
	public void setModel(String[] list) {
		comboBox.setModel(new DefaultComboBoxModel<String>(list));
	}
	
	public void setModel(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		comboBox.setModel(new DefaultComboBoxModel<String>(strings));
	}
	
	public void setModel(String item) {
		comboBox.addItem(item);
	}

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
