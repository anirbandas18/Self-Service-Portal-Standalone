package com.teamzex.ssp.webapp;

import java.util.ArrayList;
import java.util.HashMap;

import swat.ReturnCode;
import swat.cwa;

import com.ibm.bluepages.BPResults;
import com.ibm.bluepages.BluePages;
import com.teamzex.ssp.utils.Constants;

public class LDAP {
	
	public static BPResults peopleResults;
	private HashMap<String, String> credentials;
	private ArrayList<String> console;
	
	public LDAP(HashMap<String, String> credentials) {
		this.credentials = credentials;
		this.console = new ArrayList<>();
	}

	public ArrayList<String> validate() {
		if(connectBluepage()) {
			authenticateBluepage();
		}
		return console;
	}
	
	private boolean connectBluepage() {
		peopleResults = BluePages.getPersonsByInternet(credentials.get("internet_ID"));
		int replyCode = peopleResults.getStatusCode();
		if(replyCode == -1) {
			console.add(Constants.FAILURE + Constants.LDAP_MSG);
			return false;
		} else {
			console.add(Constants.SUCCESS + Constants.LDAP_MSG);
			return true;
		}
		
	}
	
	private boolean authenticateBluepage() {
		if(connectBluepage()) {
			ReturnCode rc = cwa.authenticate(Constants.SERVER_URL, 
					credentials.get("internet_ID"), credentials.get("password"));
			if(rc.getCode() == 0) {
				console.add(Constants.SUCCESS + Constants.LDAP_AUTHENTICATION_MSG);
				return true;
			} else {
				console.add(Constants.FAILURE + Constants.LDAP_AUTHENTICATION_MSG);
				return false;
			}
		} else {
			return false;
		}
	}
	

}
