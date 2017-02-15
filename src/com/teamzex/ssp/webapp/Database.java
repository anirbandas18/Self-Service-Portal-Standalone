package com.teamzex.ssp.webapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.teamzex.ssp.system.Portal.parameters;

import com.teamzex.ssp.utils.Constants;

public class Database {
	
	private String url;
	private String username;
	private String password;
	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private String parameterName;
	private String parameterValue;
	private String earthID;
	private String FLAG;
	private String SQL;
	private ArrayList<String> console;
	private boolean status;
	private Object[] ret;
	private static final String FLAG_N = "N";
	private static final String FLAG_D = "D";
	private static final String FLAG_Y = "Y";
	private static final String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	
	public Database(HashMap<String, String> credentials) {
		this.url = "jdbc:db2://" + parameters.get(Constants.PROPERTY_KEY_MAINFRAME_IP)
				+ ":" + parameters.get(Constants.PROPERTY_KEY_PORT_NO)
				+ "/" + parameters.get(Constants.PROPERTY_KEY_DATABASE_NAME); 
		this.username  = parameters.get(Constants.PROPERTY_KEY_TSO_ID);
		this.password = parameters.get(Constants.PROPERTY_KEY_PASSWORD);
		this.console = new ArrayList<>();
		this.parameterName = credentials.get(credentials.get(Constants.CREDENTIAL_PARAMETER));
		this.parameterValue = credentials.get(credentials.get(parameterName));
		this.earthID = credentials.get(Constants.CREDENTIAL_EARTH_ID);
		this.ret = new Object[2];
	}
	
	public boolean connect(boolean mode) {
		if(mode) {
			try {
				Class.forName(DRIVER);
				console.clear();
				console.add(Constants.DATABASE_CONNECTING_MSG);
				con = DriverManager.getConnection(url, username, password);
				if(con.isValid(1)) {
					ps = con.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					console.add(Constants.SUCCESS + Constants.DATABASE_CONNECTION_MSG);
					return true;
				} else {
					console.add(Constants.FAILURE + Constants.DATABASE_CONNECTION_MSG);
					return false;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				console.add(Constants.FAILURE + Constants.DATABASE_CONNECTING_MSG + "\n" + e.toString());
				return false;
				
			} catch (SQLException e) {
				e.printStackTrace();
				console.add(Constants.FAILURE + Constants.DATABASE_CONNECTING_MSG + "\n" + e.toString());
				return false;
			}
		} else {
			try {
				con.close();
				console.add(Constants.SUCCESS + Constants.DATABASE_DISCONNECTION_MSG);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.add(Constants.FAILURE + Constants.DATABASE_DISCONNECTION_MSG + "\n" + e.toString());
				return false;
			}
		}
	}
	
	public Object[] getUser() {
		try {
			console.clear();
			console.add(Constants.DATABASE_RETRIEVAL_MSG);
			SQL = "select TSO_ID,FLAG from " + parameters.get(Constants.PROPERTY_KEY_DATABASE_TABLE_NAME) + 
					" where " +  parameterName + "  = ?";
			ps.setString(1, parameterValue);
			rs = ps.executeQuery();
			if(rs.first()) {
				console.add(Constants.SUCCESS + Constants.DATABASE_RETRIEVED_MSG);
				status = true;
			} else {
				console.add(Constants.FAILURE + Constants.DATABASE_RETRIEVED_MSG);
				status = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			console.add(Constants.FAILURE + Constants.DATABASE_RETRIEVAL_MSG + "\n" + e.toString());
			status = true;
		}
		ret[0] = console;
		ret[1] = status;
		return ret;
	}
	
	
	
	public Object[] verifyUser() {
		try {
			console.clear();
			console.add(Constants.DATABASE_VERIFIYING_MSG);
			/***/
			if(earthID.equalsIgnoreCase(rs.getString(1))) {
				console.add(Constants.SUCCESS + Constants.DATABASE_VERIFIED_MSG);
				status = true;
			} else {
				console.add(Constants.FAILURE + Constants.DATABASE_VERIFIED_MSG);
				status = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			console.add(Constants.FAILURE + Constants.DATABASE_VERIFIYING_MSG + "\n" + e.toString());
			status = true;
		}
		ret[0] = console;
		ret[1] = status;
		return ret;
	}
	
	public Object[] validateRequest() {
		try {
			console.clear();
			console.add(Constants.DATABASE_REQUESTING_MSG);
			switch(rs.getString(2).toUpperCase()) {
			case FLAG_Y : 
						  break;
			case FLAG_N : console.add(Constants.DATABASE_RESET_NO_MSG);
						  status = true;
						  break;
			case FLAG_D : 
						  break;
		}
		} catch (SQLException e) {
			e.printStackTrace();
			console.add(Constants.FAILURE + Constants.DATABASE_VERIFIYING_MSG + "\n" + e.toString());
			status = true;
		}
		ret[0] = console;
		ret[1] = status;
		return ret;
	}
	
	public Object[] processRequest() {
		console.clear();
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("ALTUSER " + earthID + " PASSWORD(" + earthID + ")");
			p.waitFor();
			BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";

			while ((line = b.readLine()) != null) {
			  console.add(line);
			}
			status = true;
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
			console.add(e.toString());
			status = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			console.add(e.toString());
			status = false;
		}
		ret[0] = console;
		ret[1] = status;
		return ret;
	}
	
	public Object[] submitRequest() {
		try {
			console.clear();
			console.add(Constants.DATABASE_SUBMITTING_MSG);
			SQL = "update " + parameters.get(Constants.PROPERTY_KEY_DATABASE_TABLE_NAME) + " set FLAG = ?"
					+ " where " + parameterName + " = '" + parameterValue + "'";
			ps.setString(1, FLAG);
			int r = ps.executeUpdate(SQL);
			if(r > 0) {
				console.add(Constants.SUCCESS + Constants.DATABASE_SUBMITTED_MSG);
				status = true;
			} else {
				console.add(Constants.FAILURE + Constants.DATABASE_SUBMITTED_MSG);
				status = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			console.add(Constants.FAILURE + Constants.DATABASE_SUBMITTING_MSG);
			status = false;
		}
		ret[0] = console;
		ret[1] = status;
		
		return ret;
	}

}
