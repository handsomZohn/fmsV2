package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.SecurityLevel;

/**
 * 文档密级定义
 *
 * @author crazylion
 */

public interface SecurityLevelDao extends JpaRepository<SecurityLevel, String> {

	SecurityLevel findOneByCode(String code);
}
