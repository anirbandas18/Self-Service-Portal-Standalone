package com.teamzex.ssp.system;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import com.teamzex.ssp.actions.RequestAction;
import com.teamzex.ssp.actions.ResetAction;
import com.teamzex.ssp.actions.WindowAction;
import com.teamzex.ssp.backend.FTPSystem;
import com.teamzex.ssp.utils.Constants;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.HashMap;

public class Portal {

	private JFrame frmSelfServicePortal;
	private JLabel lblForMoreDetails;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblIbmInternetId;
	private JLabel lblPassword;
	private JLabel lblTsoId;
	private JLabel lblHere;
	private JButton btnRequestReset;
	private JButton btnResetFields;
	private static JTextArea console;
	
	public static JTextField txtInternetId;
	public static JPasswordField passwordField;
	public static JTextField txtTsoid;
	public static HashMap<String, String> parameters;
	public static FTPSystem ftpVar;

	/**
	 * Create the application.
	 */
	public Portal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frmSelfServicePortal = new JFrame();
		frmSelfServicePortal.setResizable(false);
		frmSelfServicePortal.setTitle("Mainframe Password Reset Request");
		frmSelfServicePortal.setBounds(100, 100, 662, 413);
		frmSelfServicePortal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSelfServicePortal.getContentPane().setLayout(null);
		frmSelfServicePortal.setLocationRelativeTo(null);
		frmSelfServicePortal.addWindowListener(new WindowAction());
		
		txtInternetId = new JTextField();
		txtInternetId.setBounds(266, 11, 203, 20);
		frmSelfServicePortal.getContentPane().add(txtInternetId);
		txtInternetId.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(266, 42, 203, 20);
		frmSelfServicePortal.getContentPane().add(passwordField);
		
		lblForMoreDetails = new JLabel(Constants.FRAME_FOOTER_3);
		lblForMoreDetails.setBounds(10, 357, 162, 20);
		frmSelfServicePortal.getContentPane().add(lblForMoreDetails);
		
		lblNewLabel = new JLabel(Constants.FRAME_FOOTER_1);
		lblNewLabel.setBounds(10, 323, 643, 14);
		frmSelfServicePortal.getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Status Messages", TitledBorder.LEADING, 
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 160, 636, 152);
		frmSelfServicePortal.getContentPane().add(panel);
		panel.setLayout(null);
		
		console = new JTextArea();
		console.setFont(new Font("Tahoma", Font.PLAIN, 11));
		console.setBackground(SystemColor.menu);
		
		JScrollPane sp = new JScrollPane(console);
		sp.setBounds(10, 22, 616, 119);
		panel.add(sp);
		
		
		txtTsoid = new JTextField();
		txtTsoid.setBounds(266, 76, 203, 20);
		frmSelfServicePortal.getContentPane().add(txtTsoid);
		txtTsoid.setColumns(10);
		
		lblIbmInternetId = new JLabel("IBM Internet ID");
		lblIbmInternetId.setHorizontalAlignment(SwingConstants.LEFT);
		lblIbmInternetId.setBounds(178, 11, 78, 20);
		frmSelfServicePortal.getContentPane().add(lblIbmInternetId);
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(179, 42, 78, 20);
		frmSelfServicePortal.getContentPane().add(lblPassword);
		
		lblTsoId = new JLabel("EARTH ID");
		lblTsoId.setHorizontalAlignment(SwingConstants.LEFT);
		lblTsoId.setBounds(178, 76, 78, 20);
		frmSelfServicePortal.getContentPane().add(lblTsoId);
		
		btnRequestReset = new JButton("Validate & Process");
		btnRequestReset.addActionListener(new RequestAction());
		btnRequestReset.setBounds(83, 126, 153, 23);
		frmSelfServicePortal.getContentPane().add(btnRequestReset);
		
		btnResetFields = new JButton("Reset Fields");
		btnResetFields.addActionListener(new ResetAction(txtInternetId, txtTsoid,
				passwordField, console));
		btnResetFields.setBounds(410, 126, 153, 23);
		frmSelfServicePortal.getContentPane().add(btnResetFields);
		
		JLabel label = new JLabel(Constants.FRAME_FOOTER_2);
		label.setBounds(10, 337, 291, 14);
		frmSelfServicePortal.getContentPane().add(label);
		
		lblHere = new JLabel(Constants.FRAME_FOOTER_4);
		lblHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Desktop.getDesktop()
							.browse(new URI(parameters.get(Constants.PROPERTY_KEY_HELP_LINK)));
				} catch (Exception ex) {
					Portal.writeToConsole(Constants.FAILURE + "Opening link!");
				}
			}
		});
		lblHere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHere.setBounds(167, 360, 22, 14);
		frmSelfServicePortal.getContentPane().add(lblHere);
	}
	
	public void startService() {
		ConfigurationValues config = new ConfigurationValues(Constants.CONFIG_FILE_NAME);
		if(!config.check()) {
			config.save();
		}
		parameters = config.load();
		ftpVar = new FTPSystem();
		frmSelfServicePortal.setVisible(true);
		txtInternetId.requestFocusInWindow();
	}
	
	public static void writeToConsole(String text) {
		console.setEditable(true);
		console.setText(console.getText() + text + "\n");
		console.setEditable(false);
	}
	
}
