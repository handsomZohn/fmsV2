package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 文件监控历史日志，每天晚上自动将当天的日志做整理，备份到这个数据表。
 * @author sean
 *
 */

@Entity
@Table(name = "fms_filelog_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileLogHistory extends BaseEntity{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2017022911695569270L;
	private String clientMac;
	private String ipAddress;
	private String clientName;
	private String fileFullname;
	private String operation;
	private String opertime;
	private long   fileLength;
	private String secruityLevel;
	private String username;
	private String usbkey;

	private String suffixName;
	private String identifyCode;
	private String fileId;
	/**
	 * 访问进程的MD5值
	 */
	private String processMD5;
	/**
	 * 访问进程的执行文件路径
	 */
	private String processPath;

	public FileLogHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileLogHistory(FileLog fileLog){
		this.setId(fileLog.getId());
		this.setTs(fileLog.getTs());
		
		this.setClientMac(fileLog.getClientMac());
		this.setClientName(fileLog.getClientName());
		this.setFileFullname(fileLog.getFileFullname());
		this.setFileId(fileLog.getFileId());
		this.setFileLength(fileLog.getFileLength());
		this.setIdentifyCode(fileLog.getIdentifyCode());
		this.setIpAddress(fileLog.getIpAddress());
		this.setOperation(fileLog.getOperation());
		this.setOpertime(fileLog.getOpertime());
		this.setSecruityLevel(fileLog.getSecruityLevel());
		this.setSuffixName(fileLog.getSuffixName());
		this.setUsbkey(fileLog.getUsbkey());
		this.setUsername(fileLog.getUsername());
		this.setProcessMD5(fileLog.getProcessMD5());
		this.setProcessPath(fileLog.getProcessPath());
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
	
	@Column(length=30,nullable=false)
	public String getOpertime() {
		return opertime;
	}
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	
	@Column(length=100)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Column(length=50,nullable=false)
	public String getIdentifyCode() {
		return identifyCode;
	}
	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}
	
	@Column(length=50)
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getProcessMD5() {
		return processMD5;
	}

	public void setProcessMD5(String processMD5) {
		this.processMD5 = processMD5;
	}

	public String getProcessPath() {
		return processPath;
	}

	public void setProcessPath(String processPath) {
		this.processPath = processPath;
	}
	
	
}
