package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 客户端的进程信息定义
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_client_processinfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientProcessInfo extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738590300644558077L;
	
	/**
	 * 进程名称
	 */
	private String name;
	
	/**
	 * 进程的执行文件路径
	 */
	private String fullName;
	
	/**
	 * 进程的MD5数值
	 */
	private String processMD5;
	

	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(length=200)
	public String getProcessMD5() {
		return processMD5;
	}

	public void setProcessMD5(String processMD5Array) {
		this.processMD5 = processMD5Array;
	}

	@Column(length=50,nullable=false)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
