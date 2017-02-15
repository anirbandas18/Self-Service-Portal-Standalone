package com.teamzex.ssp.system;

import java.awt.EventQueue;

import com.teamzex.ssp.utils.SetLAF;

public class Begin {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SetLAF.setLAF();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Portal window = new Portal();
					window.startService();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
