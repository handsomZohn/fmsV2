package com.casic.fms.service;

import java.util.TimerTask;

import org.apache.log4j.Logger;

public class LogOutUserTask extends TimerTask {
	
	Logger logger = Logger.getLogger(LogOutUserTask.class);
	
	private final String  token;
	
	public LogOutUserTask(String token){
		this.token = token;
	}

	@Override
	public void run() {
		logger.info("log out token:"+ this.token);
		LoginUserManager.getInstance().removeUserToken(this.token);
	}

}
