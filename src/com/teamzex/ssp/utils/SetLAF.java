package com.teamzex.ssp.utils;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class SetLAF {
	public static void setLAF() {
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception ex) 
		{
			UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
	}

}
