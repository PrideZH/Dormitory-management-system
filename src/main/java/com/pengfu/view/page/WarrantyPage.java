package com.pengfu.view.page;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pengfu.view.component.AppButton;
import com.pengfu.view.component.TitleInputBox;

/**
 * 报修界面
 * @author PrideZH
 */
@Component
@Lazy
public class WarrantyPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public WarrantyPage() {
		initComponents();
	}

	@Override
	protected void initComponents() {
		TitleInputBox describeInputBox = new TitleInputBox("问题描述", 64, 512);
		contxtPane.add(describeInputBox);
		
		AppButton warrantyBtn = new AppButton("报修");
		contxtPane.add(warrantyBtn);
	}

}
