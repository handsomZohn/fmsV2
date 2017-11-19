package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 组织机构的USBKey用户信息
 * @author sean
 *
 */
@Entity
@Table(name = "fms_orgusbkey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgUSBKeyInfo extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4680696186184591671L;
	/**
	 * 机构角色：机构负责人
	 */
	public final static String ROLE_LEADER="LEADER";
	/**
	 * 机构角色：机构员工
	 */
	public final static String ROLE_EMPLOYEE="EMPLOYEE";
	
	private String usbkey;
	
	/**
	 * 用户所属的部门
	 */
	private String orginfoCode;
	
	private String role;
	
	
	@Column(length=50, nullable=false)
	public String getUsbkey() {
		return usbkey;
	}
	public void setUsbkey(String usbkey) {
		this.usbkey = usbkey;
	}
	
	@Column(length=50, nullable=false)
	public String getOrginfoCode() {
		return orginfoCode;
	}
	public void setOrginfoCode(String orginfoCode) {
		this.orginfoCode = orginfoCode;
	}
	
	@Column(length=50, nullable=false)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	
}
