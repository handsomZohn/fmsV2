package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fms_passwordlog")
public class PasswordLog extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4221211946189285886L;
	private String userid;
	private String operation;//操作类型：默认为修改密码
	private String opertime;//操作时间
	private String username;//当前登陆的用户名
	private String operresult;// 操作结果
	
	
	public PasswordLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(length=30)
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Column(length=30,nullable=false)
	public String getOpertime() {
		return opertime;
	}
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	@Column(length=100)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=30)
	public String getOperresult() {
		return operresult;
	}
	public void setOperresult(String operresult) {
		this.operresult = operresult;
	}
	@Column(length=120,nullable=false)
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}



}
