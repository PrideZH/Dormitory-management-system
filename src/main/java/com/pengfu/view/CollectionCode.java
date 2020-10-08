package com.pengfu.view;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.pengfu.util.Resources;

/**
 * 收款码弹窗
 * @author PrideZH
 */
public class CollectionCode extends JDialog {

	private static final long serialVersionUID = 1L;

	public CollectionCode() {
		setSize(400, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		JLabel collectionCode = new JLabel(Resources.getScaledIcon("images/alipay.jpg", 400));
		getContentPane().add(collectionCode);
	}	
	
}
