package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.OrgUSBKeyInfo;

/**
 * 组织结构DAO定义
 *
 * @author crazylion
 */

public interface OrgUSBKeyInfoDao extends JpaRepository<OrgUSBKeyInfo, String> {

	/**
	 * 获取USBKey用户信息
	 * @param orgCode
	 * @return
	 */
	List<OrgUSBKeyInfo>		findByOrginfoCodeLike(String orgCode);
	
	@Query("SELECT usbkey FROM OrgUSBKeyInfo o WHERE o.orginfoCode LIKE :orgcode%")
	List<String>		getUSBKeyByOrg(@Param("orgcode")String orgcode);
	
	
	@Query("SELECT usbkey FROM OrgUSBKeyInfo ORDER BY usbkey ASC")
	List<String>		findAllUSBKey();
	
	
	OrgUSBKeyInfo   findOneByUsbkey(String usbkey);
}
