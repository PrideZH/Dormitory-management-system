package com.pengfu.view.page;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.view.CollectionCode;
import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.TitleComboBox;

/**
 * 电费充值界面
 * @author PrideZH
 */
@Component
@Lazy
public class ElectricityPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public ElectricityPage() {
		initComponents();
	}

	@Override
	protected void initComponents() {
		TitleComboBox denominationComboBox = new TitleComboBox("充值面额", 64, 128);
		denominationComboBox.setModel(new String[] {"10", "20", "50", "100"});
		contxtPane.add(denominationComboBox);
		
		AppButton payBtn = new AppButton("支付");
		contxtPane.add(payBtn);
		
		payBtn.addActionListener(e -> {
			new CollectionCode().setVisible(true);
		});
	}

}
