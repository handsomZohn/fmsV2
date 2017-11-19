package com.casic.fms.bean.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.casic.fms.bean.BaseEntity;

/**
 * 月度根据组织机构，密级和文件类型计算统计信息
 * @author sean
 *
 */
@Entity
@Table(name = "fms_org_monthlyreport")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrginfoMonthly extends BaseEntity {


	/**
	 * 年份
	 */
	private String year;
	/**
	 * 月份
	 */
	private String month;
	/**
	 * 机构ID
	 */
	private String orginfoId;
	/**
	 * 机构编码
	 */
	private String orgCode;
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
	public String getOrginfoId() {
		return orginfoId;
	}
	public void setOrginfoId(String orginfoId) {
		this.orginfoId = orginfoId;
	}
	
	@Column(length=30,nullable=false)
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Column(length=10,nullable=true)
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
