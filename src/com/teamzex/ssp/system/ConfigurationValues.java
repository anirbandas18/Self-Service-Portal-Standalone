package com.teamzex.ssp.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.teamzex.ssp.utils.Constants;

public class ConfigurationValues {
	private static String FILE_NAME;
	private static HashMap<String, String> parameters;
	
	public ConfigurationValues(String FILE_NAME) {
		ConfigurationValues.FILE_NAME = FILE_NAME;
		parameters = new HashMap<>();
	}
	public HashMap<String, String> load() {
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream(FILE_NAME);
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			@SuppressWarnings("rawtypes")
			Enumeration e = prop.propertyNames();

		    while (e.hasMoreElements()) {
		      String key = (String) e.nextElement();
		      parameters.put(key, prop.getProperty(key));
		    }
		    
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return parameters;
	}
	
	@SuppressWarnings("finally")
	public boolean save() {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			
			output = new FileOutputStream(FILE_NAME);
			
			// set the properties value
			prop.setProperty(Constants.PROPERTY_KEY_MAINFRAME_IP,"148.100.80.70");
			prop.setProperty(Constants.PROPERTY_KEY_TSO_ID, "KC03EE6");
			prop.setProperty(Constants.PROPERTY_KEY_PASSWORD, "RCCIIT");
			prop.setProperty(Constants.PROPERTY_KEY_PDS, "SELF.SERVICE.PORTAL");
			prop.setProperty(Constants.PROPERTY_KEY_DATASET, "EMPLOYEE");
			prop.setProperty(Constants.PROPERTY_KEY_HELP_LINK, "w3.tap.ibm.com/medialibrary"
					+ "/media_set_view?id=27731");
			
			// save properties to project root folder
			prop.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
			return false;
		} finally {
			if (output != null) {
				try {
					output.close();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			} else {
				return false;
			}
	 
		}
	}
	
	public boolean check() {
		File config = new File(Constants.CONFIG_FILE_NAME);
		return config.exists();
	}
}
