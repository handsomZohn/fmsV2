package com.casic.fms.bean;

import java.util.ArrayList;
import java.util.List;



/**
 * 文件监控类型定义
 * @author crazylion
 *
 */
public class TestDepart extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738590300644558077L;
	/**
	 * 文件后缀名
	 */
	private String name;
	
	
	private List<TestUser>	listUsers = new ArrayList<TestUser>();


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<TestUser> getListUsers() {
		return listUsers;
	}


	public void setListUsers(List<TestUser> listUsers) {
		this.listUsers = listUsers;
	}
	

	
}
