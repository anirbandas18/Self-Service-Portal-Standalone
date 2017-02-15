package com.teamzex.ssp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ResetAction implements ActionListener{
	
	private JTextField txtInternetId, txtTsoid;
	private JPasswordField passwordField;
	private JTextArea console;
	
	public ResetAction(JTextField txtInternetId, JTextField txtTsoid, 
			JPasswordField passwordField, JTextArea console) {
		this.txtInternetId = txtInternetId;
		this.txtTsoid = txtTsoid;
		this.passwordField = passwordField;
		this.console = console;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		txtInternetId.setText("");
		txtTsoid.setText("");
		passwordField.setText("");
		console.setText("");
		txtInternetId.requestFocusInWindow();
	}

}
