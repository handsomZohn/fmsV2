package com.casic.fms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.SystemConfig;
import com.casic.fms.dao.SystemConfigDao;
import com.casic.fms.service.SystemConfigService;
@Component
@Transactional(readOnly = false)
public class SystemConfigServiceImpl implements SystemConfigService {

	private static Logger logger = LoggerFactory.getLogger(FileSecurityLevelServiceImp.class);
	
	private SystemConfigDao systemConfigDao;
	@Autowired
	public void setsystemConfigDao(SystemConfigDao systemConfigDao) {
		this.systemConfigDao = systemConfigDao;
	}
	public List<SystemConfig> getAllLevels() {
		return this.systemConfigDao.findAll(SystemConfig.getSortASC("code"));
	}

	public SystemConfig getOneByCode(String code) {
		return this.systemConfigDao.findOneByCode(code);
	}

	public SystemConfig save(SystemConfig pmodel) {
		return this.systemConfigDao.save(pmodel);
	}
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.systemConfigDao.delete(id);
	}

}
