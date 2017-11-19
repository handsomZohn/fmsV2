package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "fms_system_config")
public class SystemConfig extends BaseEntity{

	private static final long serialVersionUID = -6861235553383599512L;
	
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 说明
	 */
	private String comments;
	/**
	 * 密码期限
	 */
	private String userday;
	/**
	 * 是否启用
	 */
	private boolean isUsed;
	
	public SystemConfig() {
		// TODO Auto-generated constructor stub
	}
	@Column(length=30,nullable=false)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(length=500,nullable=false)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@Column(length=50,nullable=false)
	public String getUserday() {
		return userday;
	}

	public void setUserday(String userday) {
		this.userday = userday;
	}
	@Column( nullable=false)
	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	

}
