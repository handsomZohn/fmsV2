package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 文件密级定义
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_filesecurity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileSecurity extends BaseEntity{
	
	/**
	 * 定密之后的文件有独立的UUID，如果有人重新定密，UUID不能更换，除非是脱管之后再定密。
	 */
	private static final long serialVersionUID = 3738590300644558077L;
	private String clientMac;
	private String fileName;
	private String secruityLevel;
	private String status;
	private String comments;
	private String opertime;
	private String username;
	private String usbkey;
	private String authority;
	
	//定密期限  -1 是无期限  0是没有设置 其它是根据期限单位设定的数值
	private Integer period = 0;  
	//期限单位
	private String periodUnit;
	
	
	/**
	 * 第二次及以后多次定密，使用的是第一次的ID
	 * 
	 */
	private String fileId;
	
	
	public static final String STATUS_APPLY="APPLY";//申请
	public static final String STATUS_NOPASS="NOPASS";//未通过
	public static final String STATUS_PASS="PASS";//审核通过
	
	
	@Column(length=30,nullable=false)
	public String getOpertime() {
		return opertime;
	}
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	
	@Column(length=30,nullable=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(length=1000,nullable=true)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	@Column(length=30,nullable=false)
	public String getSecruityLevel() {
		return secruityLevel;
	}
	public void setSecruityLevel(String secruityLevel) {
		this.secruityLevel = secruityLevel;
	}

	@Column(length=500,nullable=true)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(length=30,nullable=false)
	public String getClientMac() {
		return clientMac;
	}
	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}

	@Column(length=30)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=50)
	public String getUsbkey() {
		return usbkey;
	}
	public void setUsbkey(String usbkey) {
		this.usbkey = usbkey;
	}
	
	
	@Column(length=30,nullable=false)
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	@Column(length=30,nullable=true)
	public String getPeriodUnit() {
		return periodUnit;
	}
	public void setPeriodUnit(String periodUnit) {
		this.periodUnit = periodUnit;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	
	
	
}
