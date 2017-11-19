package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 根据USBKey获取用户的访问随机字符串
 * 
 * @author crazylion
 */
@Entity
@Table(name = "fms_myrandkeys")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MyRandKeys extends BaseEntity {

	//用户的USBKey信息
	private String usbkey;
	
	//访问秘钥
	private String accessKey;

	//访问类型
	private String accessType;

	//过期时间
	private String expireDate;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	//我的台账访问字符串
	public final static String ACCESSTYPE_MYLOG= "MYLOG";
	
	@Column(length=50,nullable=false)	
	public String getUsbkey() {
		return usbkey;
	}
	public void setUsbkey(String usbkey) {
		this.usbkey = usbkey;
	}
	
	@Column(length=50,nullable=false)	
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	@Column(length=50,nullable=false)	
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
	@Column(length=30,nullable=false)	
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Column(length=30,nullable=false)	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}