package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 客户端Key信息
 * @author sean
 *
 */
@Entity
@Table(name = "fms_usbkey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class USBKeyInfo extends BaseEntity{
	
	private final static String  defaultSecurity = "4"; 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1136709926879196480L;
	
	private String username;
	/**
	 * USBKey信息不能重复
	 */
	private String usbkey;
	private String comments;
	
	
	/**
	 * 阅读的最高密级
	 */
	private String readSecurity = defaultSecurity;
	/**
	 * 存储的最高密级
	 */
	private String saveSecurity = defaultSecurity;
	
	@Column(length=500, nullable=true)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(length=30, nullable=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=50, nullable=false)
	public String getUsbkey() {
		return usbkey;
	}
	public void setUsbkey(String usbkey) {
		this.usbkey = usbkey;
	}
	
	@Column(length=30, nullable=true)
	public String getReadSecurity() {
		return readSecurity;
	}
	public void setReadSecurity(String readSecurity) {
		this.readSecurity = readSecurity;
	}
	@Column(length=30, nullable=true)
	public String getSaveSecurity() {
		return saveSecurity;
	}
	public void setSaveSecurity(String saveSecurity) {
		this.saveSecurity = saveSecurity;
	}
	
}
