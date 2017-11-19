package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 文件监控类型定义
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_filetype")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileType extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738590300644558077L;
	/**
	 * 文件后缀名
	 */
	private String suffixName;
	
	/**
	 * 文件识别码
	 */
	private String code;
	
	
	/**
	 * 文件类型名称
	 */
	private String name;
	
	
	/**
	 * 是否启用监控
	 */
	private boolean isMonitor;
	
	/**
	 * 是否启用定密
	 */
	private boolean isSecurityLevel;

	/**
	 * 安全进程的MD5数值,多个进程MD5用分号区别
	 */
	private String processMD5;
	
	@Column(length=50,nullable=false)
	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	

	@Column(nullable=false)
	public boolean isMonitor() {
		return isMonitor;
	}

	public void setMonitor(boolean isMonitor) {
		this.isMonitor = isMonitor;
	}

	@Column(nullable=false)
	public boolean isSecurityLevel() {
		return isSecurityLevel;
	}

	public void setSecurityLevel(boolean isSecurityLevel) {
		this.isSecurityLevel = isSecurityLevel;
	}

	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=50,nullable=false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length=2000)
	public String getProcessMD5() {
		return processMD5;
	}

	public void setProcessMD5(String processMD5Array) {
		this.processMD5 = processMD5Array;
	}
	
	
}
