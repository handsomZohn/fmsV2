package com.casic.fms.bean.query;

import java.util.List;

import com.casic.fms.bean.FileLogHistory;

/**
 * FileLog查询条件Bean
 * @author sean
 *
 */
public class FileLogQueryBean extends  FileLogHistory {
	private String operationStarttime;
	private String operationEndtime;

	private String year;
	private String month;
	private String orginfoId;
	
	/**
	 * USBKey用户列表
	 */
	private List<String>		usbkeys;
	
	
	public String getOperationStarttime() {
		return operationStarttime;
	}
	public void setOperationStarttime(String operationStarttime) {
		this.operationStarttime = operationStarttime;
	}
	public String getOperationEndtime() {
		return operationEndtime;
	}
	public void setOperationEndtime(String operationEndtime) {
		this.operationEndtime = operationEndtime;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getOrginfoId() {
		return orginfoId;
	}
	public void setOrginfoId(String orginfId) {
		this.orginfoId = orginfId;
	}
	public List<String> getUsbkeys() {
		return usbkeys;
	}
	public void setUsbkeys(List<String> usbkeys) {
		this.usbkeys = usbkeys;
	}

}
