package com.casic.fms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.SecurityLevel;
import com.casic.fms.dao.SecurityLevelDao;
import com.casic.fms.service.FileSecurityLevelService;

@Component
@Transactional(readOnly = false)
public class FileSecurityLevelServiceImp implements FileSecurityLevelService  {

	
	private static Logger logger = LoggerFactory.getLogger(FileSecurityLevelServiceImp.class);
	
	private SecurityLevelDao 	securityLevelDao;
	
	
	@Autowired
	public void setSecurityLevelDao(SecurityLevelDao securityLevelDao) {
		this.securityLevelDao = securityLevelDao;
	}	
	
	public List<SecurityLevel>	getAllLevels(){
		return this.securityLevelDao.findAll(SecurityLevel.getSortASC("code"));
	}

	public SecurityLevel save(SecurityLevel slmodel) {
		// TODO Auto-generated method stub
		return this.securityLevelDao.save(slmodel);
	}

	public SecurityLevel getOneByCode(String code) {
		// TODO Auto-generated method stub
		return this.securityLevelDao.findOneByCode(code);
	}
	
	
}
