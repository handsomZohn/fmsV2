package com.casic.fms.service;

import java.util.TimerTask;

import com.casic.fms.bean.User;

public class RegisterUserTask extends TimerTask {
	
	private String token;
	private User user;
	
	public RegisterUserTask(String token,User user){
		this.token = token;
		this.user = user;
	}
	

	@Override
	public void run() {
		LoginUserManager.getInstance().removeUserToken(this.token);
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
