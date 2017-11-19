package com.casic.fms.service;

import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.casic.fms.bean.User;

public class LoginUserManager {

	private static final Logger logger = Logger.getLogger(LoginUserManager.class);

	private static final Timer timer = new Timer();

	private static final int DELAY = 30 * 60 * 1000;

	private static final Map<String, TimerTask> tasks = new Hashtable<String, TimerTask>();

	private static final LoginUserManager instance = new LoginUserManager();

	private LoginUserManager() {

	}

	public static LoginUserManager getInstance() {
		return instance;
	}

	public synchronized void updateUserState(String token) {
		logger.info("update user Token:" + token);
		TimerTask task = tasks.get(token);
		if (task != null) {
			task.cancel();
			tasks.remove(token);
		}
		addUserToken(token);
	}

	public synchronized void addUserToken(String token) {
		logger.info("add user Token:" + token);
		LogOutUserTask task = new LogOutUserTask(token);
		tasks.put(token, task);
		timer.schedule(task, DELAY);
	}
	
	public synchronized void addUserTransientToken(String token) {
		logger.info("add user tempToken:" + token);
		LogOutUserTask task = new LogOutUserTask(token);
		tasks.put(token, task);
		timer.schedule(task, DELAY/10);
	}

	public synchronized void logoutUserToken(String token) {
		UserLoginContext.getInstance().removeUser(token);
		TimerTask task = tasks.get(token);
		if (task != null) {
			try {
				task.cancel();
			} catch (Exception e) {
			}
			tasks.remove(token);
		}
	}

	public synchronized void registerUserToken(String token, User user) {
		logger.info("add user Token:" + token);
		TimerTask task = new RegisterUserTask(token, user);
		tasks.put(token, task);
		timer.schedule(task, 24 * 2 * DELAY);
	}

	public synchronized void removeUserToken(String token) {
		UserLoginContext.getInstance().removeUser(token);
		TimerTask task = tasks.get(token);
		if (task != null) {
			tasks.remove(token);
		}
	}

	public TimerTask getTask(String token) {
		return tasks.remove(token);
	}
}
