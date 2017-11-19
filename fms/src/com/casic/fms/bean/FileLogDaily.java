package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 日志每日统计表
 * @author sean
 *
 */
@Entity
@Table(name = "fms_filelog_daily")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileLogDaily extends BaseEntity{
	
	private String clientMac;
	private String ipAddress;
	private String clientName;
	private String fileFullname;
	private String operation;//操作类型
	private long operationSummary; //操作次数
	private String starttime;//最早时间
	private String endtime;//最晚时间
	private long   fileLength;
	private String secruityLevel;
	private String usbkey;
	private String suffixName;
	private String fileId;
	//日志发生的日期
	private String logdate;
	
	
	public FileLogDaily() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(length=30,nullable=false)
	public String getLogdate() {
		return logdate;
	}
	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}
	
	@Column(length=30,nullable=true)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(length=30,nullable=true)
	public String getSecruityLevel() {
		return secruityLevel;
	}
	public void setSecruityLevel(String secruityLevel) {
		this.secruityLevel = secruityLevel;
	}
	
	public long getFileLength() {
		return fileLength;
	}
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}
	
	@Column(length=500,nullable=false)
	public String getFileFullname() {
		return fileFullname;
	}
	public void setFileFullname(String fileFullname) {
		this.fileFullname = fileFullname;
	}
	

	
	@Column(length=50,nullable=false)
	public String getClientMac() {
		return clientMac;
	}
	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}
	
	@Column(length=50,nullable=true)
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	@Column(length=30,nullable=false)
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Column(length=100,nullable=false)
	public String getUsbkey() {
		return usbkey;
	}
	public void setUsbkey(String usbkey) {
		this.usbkey = usbkey;
	}
	@Column(length=30)
	public String getSuffixName() {
		return suffixName;
	}
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}
	
	@Column(length=50)
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Column(length=30,nullable=false)
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	@Column(length=30,nullable=false)
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public long getOperationSummary() {
		return operationSummary;
	}
	public void setOperationSummary(long operationSummary) {
		this.operationSummary = operationSummary;
	}
	
	
}
