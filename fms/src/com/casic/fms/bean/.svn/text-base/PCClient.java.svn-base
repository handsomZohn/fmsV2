package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 客户端主机信息
 * @author sean
 *
 */
@Entity
@Table(name = "fms_pcclient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PCClient extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1136709926879196480L;

	private String clientMac;
	private String ipAddress;
	private String clientName;
	private String comments;
	private String username;
	private String usbkey;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	
	public String getClientMac() {
		return clientMac;
	}
	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	
}
