package com.casic.fms.service;

import java.util.List;

import com.casic.fms.bean.SecurityLevel;

/**
 * 文件密级信息定义接口
 * @author sean
 *
 */

public interface FileSecurityLevelService {

	public  List<SecurityLevel> getAllLevels();

	
	public  SecurityLevel getOneByCode(String code);
	
	
	public 	SecurityLevel  save(SecurityLevel slmodel);
}