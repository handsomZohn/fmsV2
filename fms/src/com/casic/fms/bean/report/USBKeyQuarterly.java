package com.casic.fms.bean.report;

import com.casic.fms.bean.BaseEntity;

/**
 * 根据 USBKey，密级和文件类型统计季度信息
 * @author sean
 *
 */
public class USBKeyQuarterly extends BaseEntity {

	/**
	 * 年份
	 */
	private String year;
	
	/**
	 * 季度
	 */
	private String quarter;
	
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
	
}
