package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.SecurityLimit;

/**
 * 监控密级关键字设置
 * 
 * @author crazylion
 */

public interface SecurityLimitDao extends JpaRepository<SecurityLimit, String> {
	
	public SecurityLimit findByKeyword(String keywords);
}
