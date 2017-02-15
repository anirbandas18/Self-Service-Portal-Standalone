package com.teamzex.ssp.actions;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import com.teamzex.ssp.system.Portal;

public class WindowAction implements WindowListener {

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		int r = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Warning", 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(r == JOptionPane.YES_OPTION) {
			if(Portal.ftpVar.logoff()) {
				Portal.ftpVar.disconnect();
			}
			System.exit(1);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
