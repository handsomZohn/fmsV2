package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "fms_filelog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileLog extends BaseEntity{
	
	
	public final static String OPERATION_PRINT="PRINT";
	public final static String OPERATION_SAVE= "SAVE";
	public final static String OPERATION_READ= "READ";
	public final static String OPERATION_REMOVE= "REMOVE";
	public final static String OPERATION_SECURITY= "SECURITY";
	public final static String OPERATION_UNSECURITY= "UNSECURITY";
	public final static String OPERATION_TOUSB= "TOUSB";
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6106086497156725613L;
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
	
	public FileLog() {
		super();
		// TODO Auto-generated constructor stub
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
	@Column(length=30,nullable=true)
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
	
	@Column(length=50,nullable=false)
	public String getProcessMD5() {
		return processMD5;
	}
	public void setProcessMD5(String processMD5) {
		this.processMD5 = processMD5;
	}
	
	@Column(length=500,nullable=false)
	public String getProcessPath() {
		return processPath;
	}
	public void setProcessPath(String processPath) {
		this.processPath = processPath;
	}
	
	
}
