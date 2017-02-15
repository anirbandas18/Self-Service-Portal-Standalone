package com.teamzex.ssp.system;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;

import com.teamzex.ssp.backend.IBMEmployee;
import com.teamzex.ssp.utils.Constants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmBox extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 147664862866206143L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ConfirmBox(IBMEmployee x) {
		setBounds(100, 100, 352, 204);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel(Constants.DIALOG_HEADER);
			lblNewLabel.setBounds(10, 10, 317, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblName = new JLabel("Name : ");
			lblName.setBounds(10, 35, 44, 14);
			contentPanel.add(lblName);
		}
		{
			JLabel lblSerialNo = new JLabel("Employee No. : ");
			lblSerialNo.setBounds(10, 60, 82, 14);
			contentPanel.add(lblSerialNo);
		}
		{
			JLabel lblIbmInternetId = new JLabel("IBM Internet ID : ");
			lblIbmInternetId.setBounds(10, 85, 91, 14);
			contentPanel.add(lblIbmInternetId);
		}
		{
			JLabel lblTsoId = new JLabel("EARTH ID : ");
			lblTsoId.setBounds(10, 107, 64, 14);
			contentPanel.add(lblTsoId);
		}
		{
			JLabel lblEmployeename = new JLabel(x.getEmployeeName());
			lblEmployeename.setBounds(52, 35, 275, 14);
			contentPanel.add(lblEmployeename);
		}
		{
			JLabel lblEmployeeno = new JLabel(x.getSerialNo());
			lblEmployeeno.setBounds(89, 60, 237, 14);
			contentPanel.add(lblEmployeeno);
		}
		{
			JLabel lblInternetid = new JLabel(x.getInternetID());
			lblInternetid.setBounds(99, 85, 227, 14);
			contentPanel.add(lblInternetid);
		}
		{
			JLabel lblEmployeetsoid = new JLabel(x.getEarthID());
			lblEmployeetsoid.setBounds(71, 107, 256, 14);
			contentPanel.add(lblEmployeetsoid);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Submit");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Portal.ftpVar.connect()) {
							if(Portal.ftpVar.login()) {
								Portal.ftpVar.keepAlive();
								if(Portal.ftpVar.checkParentDirectory()) {
									if(Portal.ftpVar.changeDirectory()) {
										Portal.ftpVar.transferData(x);
										dispose();
									}
								}
							} 
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
