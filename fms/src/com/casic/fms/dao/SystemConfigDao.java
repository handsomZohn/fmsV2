package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.SystemConfig;

public interface SystemConfigDao extends JpaRepository<SystemConfig, String> {

	SystemConfig findOneByCode(String code);


}
