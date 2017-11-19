package com.casic.fms.service;

import java.util.Hashtable;
import java.util.Map;

import com.casic.fms.bean.User;

public class UserLoginContext {

	private static final UserLoginContext instance = new UserLoginContext();
	
	private Map<String,User> users = new Hashtable<String,User>();
	
	private UserLoginContext(){
		
	}
	
	public static UserLoginContext getInstance(){
		return instance;
	}
	
	public synchronized void  putUser(String token,User user){
		if(token != null && user != null){
			users.put(token, user);
			LoginUserManager.getInstance().addUserToken(token);
		}
	}
	
	public synchronized void  putUserTransient(String token,User user){
		if(token != null && user != null){
			users.put(token, user);
			LoginUserManager.getInstance().addUserTransientToken(token);
		}
	}
	
	public User getUser(String token){
		return users.get(token);
	}
	
	public synchronized void removeUser(String token){
		users.remove(token);
	}
}
