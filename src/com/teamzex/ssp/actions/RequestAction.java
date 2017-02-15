package com.teamzex.ssp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import swat.ReturnCode;
import swat.cwa;

import com.ibm.bluepages.BPResults;
import com.ibm.bluepages.BluePages;

import com.teamzex.ssp.backend.IBMEmployee;
import com.teamzex.ssp.system.ConfirmBox;
import com.teamzex.ssp.system.Portal;
import com.teamzex.ssp.utils.Constants;

public class RequestAction implements ActionListener {
	
	private String internetID, password, earthID;
	private BPResults peopleResults;
	private IBMEmployee emp;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		internetID = Portal.txtInternetId.getText();
		earthID = Portal.txtTsoid.getText();
		password = new String(Portal.passwordField.getPassword());
		if(authenticateBluepage()) {
			if(extractEmployeeData()) {
				ConfirmBox box = new ConfirmBox(emp);
				box.setVisible(true);
			}
		}
	}

	private boolean connectBluepage() {
		peopleResults = BluePages.getPersonsByInternet(internetID);
		int replyCode = peopleResults.getStatusCode();
		if(replyCode == -1) {
			Portal.writeToConsole(Constants.FAILURE + Constants.LDAP_MSG);
			return false;
		} else {
			Portal.writeToConsole(Constants.SUCCESS + Constants.LDAP_MSG);
			return true;
		}
		
	}
	
	private boolean authenticateBluepage() {
		if(connectBluepage()) {
			ReturnCode rc = cwa.authenticate(Constants.SERVER_URL, internetID, password);
			if(rc.getCode() == 0) {
				Portal.writeToConsole(Constants.SUCCESS + Constants.LDAP_AUTHENTICATION_MSG);
				return true;
			} else {
				Portal.writeToConsole(Constants.FAILURE + Constants.LDAP_AUTHENTICATION_MSG);
				return false;
			}
		} else {
			return false;
		}
	}
	
	private boolean extractEmployeeData() {
		emp = new IBMEmployee();
		Portal.writeToConsole(Constants.LDAP_RETRIEVAL_MSG);
		String firstName = (String) peopleResults.getColumn(Constants.FIRST_NAME).elementAt(0);
		String lastName = (String) peopleResults.getColumn(Constants.LAST_NAME).elementAt(0);
		String serialNo = (String) peopleResults.getColumn(Constants.SERIAL_NO).elementAt(0);
		emp.setInternetID(internetID);
		emp.setEmployeeName(firstName + " " + lastName);
		emp.setSerialNo(serialNo);
		emp.setEarthID(earthID);
		if(emp.isValid()) {
			Portal.writeToConsole(Constants.SUCCESS + Constants.LDAP_RETRIEVED_MSG);
			return true;
		} else {
			Portal.writeToConsole(Constants.FAILURE + Constants.LDAP_RETRIEVED_MSG);
			return false;
		}
	}
		
}
