package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.VersionInfo;

/**
 * 版本对应的DAO
 * @author crazylion
 */

public interface VersionInfoDao extends JpaRepository<VersionInfo, String> {

	
	/**
	 * 根据版本获取信息
	 * @param code
	 * @return
	 */
	VersionInfo findOneByCode(String code);
}
