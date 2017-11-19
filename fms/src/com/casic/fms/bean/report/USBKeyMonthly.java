package com.casic.fms.bean.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.casic.fms.bean.BaseEntity;

/**
 * 根据 USBKey，密级和文件类型统计月度信息
 * @author sean
 *
 */
@Entity
@Table(name = "fms_user_monthlyreport")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class USBKeyMonthly extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3347786346157282726L;

	/**
	 * 年份
	 */
	private String year;
	
	/**
	 * 月份
	 */
	private String month;
	
	/**
	 * USBKey
	 */
	private String usbkey;

	/**
	 * 文档密级
	 */
	private String securityCode;
	
	/**
	 * 文件类型
	 */
	private String fileType;
	
	/**
	 * 定密数量
	 */
	private long settingNumber;
	
	/**
	 * 日志数量
	 */
	private long logNumber;
	
	
	@Column(length=10,nullable=false)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(length=10,nullable=false)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(length=50,nullable=false)
	public String getUsbkey() {
		return usbkey;
	}

	public void setUsbkey(String usbkey) {
		this.usbkey = usbkey;
	}

	@Column(length=10,nullable=false)
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Column(length=10,nullable=false)
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSettingNumber() {
		return settingNumber;
	}

	public void setSettingNumber(long settingNumber) {
		this.settingNumber = settingNumber;
	}

	public long getLogNumber() {
		return logNumber;
	}

	public void setLogNumber(long logNumber) {
		this.logNumber = logNumber;
	}

}
