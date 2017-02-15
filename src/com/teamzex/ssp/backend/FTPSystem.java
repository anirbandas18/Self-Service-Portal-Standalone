package com.teamzex.ssp.backend;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.teamzex.ssp.system.Portal;
import com.teamzex.ssp.utils.Constants;


public class FTPSystem {
	
	private FTPClient ftp;
	private static String TSO_ID;
	private static String MAINFRAME_IP;
	private static String PASSWORD;
	private static String DATASET;
	private static String PDS;
	
	public FTPSystem() {
		this.ftp = new FTPClient();
		MAINFRAME_IP = Portal.parameters.get(Constants.PROPERTY_KEY_MAINFRAME_IP);
		TSO_ID = Portal.parameters.get(Constants.PROPERTY_KEY_TSO_ID);
		PASSWORD = Portal.parameters.get(Constants.PROPERTY_KEY_PASSWORD);
		DATASET = Portal.parameters.get(Constants.PROPERTY_KEY_DATASET);
		PDS = Portal.parameters.get(Constants.PROPERTY_KEY_PDS);
	}
	
	public boolean connect() {
		try {
			ftp.connect(MAINFRAME_IP);
			if(ftp.getReplyCode() == 220) {
				Portal.writeToConsole(Constants.SUCCESS + Constants.FTP_CONNECTION_MSG);
				return true;
			} else {
				Portal.writeToConsole(Constants.FAILURE + Constants.FTP_CONNECTION_MSG);
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_CONNECTION_MSG);
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean login() {
		try {
			if(ftp.login(TSO_ID,PASSWORD)) {
				int replyCode = ftp.getReplyCode();
				ftp.enterLocalPassiveMode();
				ftp.setFileType(FTP.ASCII_FILE_TYPE);
					if (FTPReply.isPositiveCompletion(replyCode)) {
						Portal.writeToConsole(Constants.SUCCESS + Constants.FTP_LOGIN_MSG);
						ftp.setSoLinger(true, 0);
						return true;
					} else {
						Portal.writeToConsole(Constants.FAILURE + Constants.FTP_LOGIN_MSG);
						return false;
					}
			} else {
				Portal.writeToConsole(Constants.FAILURE + Constants.FTP_LOGIN_MSG);
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_LOGIN_MSG);
			return false;
		}
	}
	
	public boolean logoff() {
		if(ftp.isConnected()){
			try {
				if(ftp.logout()) {
					Portal.writeToConsole(Constants.SUCCESS + Constants.FTP_LOGOFF_MSG);
					return true;
				} else {
					Portal.writeToConsole(Constants.FAILURE + Constants.FTP_LOGOFF_MSG);
					return false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Portal.writeToConsole(Constants.FAILURE + Constants.FTP_LOGOFF_MSG);
				return false;
			}
		} else {
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_STILL_CONNECTED_MSG);
			return false;
		}
	}
	
	public boolean disconnect() {
		if(ftp.isConnected()){
			try {
				ftp.disconnect();
				Portal.writeToConsole(Constants.SUCCESS + Constants.FTP_DISCONNECTION_MSG);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Portal.writeToConsole(Constants.FAILURE + Constants.FTP_DISCONNECTION_MSG);
				return false;
			}
		} else {
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_STILL_CONNECTED_MSG);
			return false;
		}
	}
	
	public boolean transferData(IBMEmployee e) {
		try {
			OutputStream os = ftp.appendFileStream(DATASET);
			PrintWriter out = new PrintWriter(os);
			out.println(e.toString());
			out.close();
			if(ftp.completePendingCommand()) {
				Portal.writeToConsole(Constants.SUCCESS + Constants.FTP_UPLOAD_MSG);
				return true;
			} else {
				Portal.writeToConsole(Constants.FAILURE + Constants.FTP_UPLOAD_MSG);
				return false;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_UPLOAD_MSG);
			return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_UPLOAD_MSG);
			return false;
		} 
		
	}
	
	
	public boolean checkParentDirectory() {
		try {
			FTPFile dir[] = ftp.listDirectories();
			boolean f = false;
			for(FTPFile x:dir) {
				if(x.getName().equalsIgnoreCase(PDS)) {
					f = true;
					break;
				}
			}
			if(!f) {
				if(ftp.makeDirectory(PDS)) {
					return true;
				} else {
					Portal.writeToConsole(Constants.FAILURE + Constants.FTP_DIRECTORY_CREATION_MSG);
					return false;
				}
			} else {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_DIRECTORY_CREATION_MSG);
			return false;
		}
	}
	
	
	public boolean changeDirectory() {
		try {
			if(ftp.changeWorkingDirectory("'" + TSO_ID + "." + PDS + "'")) {
				return true;
			} else {
				Portal.writeToConsole(Constants.FAILURE + Constants.FTP_DIRECTORY_CHANGE_MSG);
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Portal.writeToConsole(Constants.FAILURE + Constants.FTP_DIRECTORY_CHANGE_MSG);
			return false;
		}
		
	}
	
	public void keepAlive() {
		final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	    service.scheduleWithFixedDelay(new Runnable() {
	        @Override
	        public void run() {
	        	try {
	        		ftp.sendNoOp();
	        	} catch(Exception e) {
	        		Portal.writeToConsole(Constants.FAILURE + Constants.FTP_STILL_CONNECTED_MSG);
	        		e.printStackTrace();
	        	}
	        }
	      }, 0, 1, TimeUnit.MINUTES);
	  }
}

