package com.teamzex.ssp.utils;

public class Constants {
	
	public static final String SERVER_URL = "bluepages.ibm.com";
	public static final String FIRST_NAME = "HRFIRSTNAME";
	public static final String MIDDLE_NAME = "HRMIDDLENAME";
	public static final String LAST_NAME = "HRLASTNAME";
	public static final String SERIAL_NO = "EMPNUM";
	
	public static final String FAILURE = "Failure : ";
	public static final String SUCCESS = "Success : ";
	
	public static final String LDAP_MSG = "Connection to LDAP Server";
	public static final String LDAP_AUTHENTICATION_MSG = "Internet ID and Password";
	public static final String LDAP_RETRIEVAL_MSG = "Retrieving Data from LDAP server";
	public static final String LDAP_RETRIEVED_MSG = "Data retrieval";
	
	public static final String FTP_CONNECTION_MSG = "Connection established with EARTH";
	public static final String FTP_LOGIN_MSG = "EARTH Login Authentication";
	public static final String FTP_DISCONNECTION_MSG = "Disconnection with EARTH";
	public static final String FTP_LOGOFF_MSG = "EARTH Logout";
	public static final String FTP_STILL_CONNECTED_MSG = "Connection persistence";
	public static final String FTP_DIRECTORY_CREATION_MSG = "Directory creation";
	public static final String FTP_DIRECTORY_CHANGE_MSG = "Directory change";
	public static final String FTP_UPLOAD_MSG = "Password reset request submitted";
	
	public static final String DIALOG_HEADER = "Your password reset request will be submitted with these details";
	public static final String FRAME_FOOTER_1 = "Developed by Anirban Das, Piyali Banerjee and Raktim Talukdar"
			+ " of RCCIIT as remote mentoring project under the guidance of ";
	public static final String FRAME_FOOTER_2 = "Sripathi R. Dantuluri, Joydeep Mukherjee and Manas Ghosh";
	public static final String FRAME_FOOTER_3 = "For more details on the tool click";
	public static final String FRAME_FOOTER_4 = "<html><a href=''>here</a></html>";
	
	private static final String SERVICE_NAME = "self.service.portal.";
	
	public static final String CONFIG_FILE_NAME = "config.properties";
	
	public static final String PROPERTY_KEY_LDAP_SERVER = SERVICE_NAME + "bluepages.server";
	public static final String PROPERTY_KEY_TSO_ID = SERVICE_NAME + "tso_id";
	public static final String PROPERTY_KEY_PASSWORD = SERVICE_NAME + "password";
	public static final String PROPERTY_KEY_MAINFRAME_IP = SERVICE_NAME + "mainframe_ip";
	public static final String PROPERTY_KEY_PORT_NO = SERVICE_NAME + "port";
	public static final String PROPERTY_KEY_DATASET = SERVICE_NAME + "dataset";
	public static final String PROPERTY_KEY_PDS = SERVICE_NAME + "pds";
	public static final String PROPERTY_KEY_HELP_LINK = SERVICE_NAME + "link";
	public static final String PROPERTY_KEY_DATABASE_NAME = SERVICE_NAME + "db2.databasename";
	public static final String PROPERTY_KEY_DATABASE_TABLE_NAME = SERVICE_NAME + "db2.tablename";
	
	public static final String CREDENTIAL_PARAMETER = "parameter_name";
	public static final String CREDENTIAL_INTERNET_ID = "internet_ID";
	public static final String CREDENTIAL_SERIAL_NO = "serial_No";
	public static final String CREDENTIAL_EARTH_ID = "tso_id";
	
	public static final String DATABASE_CONNECTING_MSG = "Connecting with DB2";
	public static final String DATABASE_CONNECTION_MSG = "Connection established with DB2";
	public static final String DATABASE_DISCONNECTION_MSG = "Disconnection with DB2";
	public static final String DATABASE_RETRIEVAL_MSG = "Retrieving Data from DB2";
	public static final String DATABASE_RETRIEVED_MSG = "Data retrieval";
	public static final String DATABASE_SUBMITTING_MSG = "Submitting reset request to DB2";
	public static final String DATABASE_SUBMITTED_MSG = "Request submitted";
	public static final String DATABASE_VERIFIYING_MSG = "Verifying EARTH ID of the Employee";
	public static final String DATABASE_VERIFIED_MSG = "EARTH ID belongs to Employee";
	public static final String DATABASE_REQUESTING_MSG = "Checking reset request status";
	public static final String DATABASE_RESET_YES_MSG = "Reset request has been performed";
	public static final String DATABASE_RESET_NO_MSG = "Reset request not performed";
	public static final String DATABASE_RESET_DONE_MSG = "Your EARTH ID is reset and new password is same as"
			+ " your user ID and it need to be changed on 1st log on";
}
