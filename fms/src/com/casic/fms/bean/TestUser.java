package com.casic.fms.bean;

import javax.persistence.Column;


/**
 * 文件监控类型定义
 * @author crazylion
 *
 */
public class TestUser extends BaseEntity{
	
	public TestUser(String id,String name) {
		super();
		super.setId(id);
		this.name = name;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738590300644558077L;
	/**
	 * 文件后缀名
	 */
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
}
