package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.USBKeyInfo;

/**
 * USBKey信息
 * 
 * @author crazylion
 */

public interface USBKeyInfoDao extends JpaRepository<USBKeyInfo, String> {
	
	
	public USBKeyInfo findByUsbkey(String usbkey);
	
	/**
	 * 密级信息为空的USBKey
	 * @return
	 */
	public List<USBKeyInfo>	findByReadSecurityIsNullOrSaveSecurityIsNull();
}
