package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.PCClient;

/**
 * 监控终端信息
 * 
 * @author crazylion
 */

public interface PCClientDao extends JpaRepository<PCClient, String> {
	
	
	public PCClient findByClientMac(String clientmac);
}
