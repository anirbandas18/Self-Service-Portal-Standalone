package com.teamzex.ssp.backend;

import java.util.ArrayList;


public class IBMEmployee {
	
	private String internetID, employeeName, serialNo, earthID;
	
	public IBMEmployee() {
		internetID = employeeName = serialNo = earthID = "";
	}
	
	public String getEarthID() {
		return earthID;
	}

	public void setEarthID(String earthID) {
		this.earthID = earthID;
	}

	public String getInternetID() {
		return internetID;
	}

	public void setInternetID(String internetID) {
		this.internetID = internetID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	@Override
	public String toString() {
		return "IBMEmployee [internetID=" + internetID + ", employeeName="
				+ employeeName + ", serialNo=" + serialNo
				+ ", earthID=" + earthID + "]";
	}

	public boolean isValid() {
		ArrayList<Boolean> flag = new ArrayList<>();
		flag.add((internetID.length() == 0) ? false : true);
		flag.add((employeeName.length() == 0) ? false : true);
		flag.add((serialNo.length() == 0) ? false : true);
		flag.add((earthID.length() == 0) ? false : true);
		for(boolean x : flag) {
			if(x == false) {
				return false;
			}
		}
		return true;
	}

}
