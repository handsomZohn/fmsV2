package com.casic.fms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.common.SimpleUtils;
import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.PasswordLog;
import com.casic.fms.bean.SystemConfig;
import com.casic.fms.dao.PasswordLogDao;
import com.casic.fms.dao.SystemConfigDao;
import com.casic.fms.service.PasswordLogService;

@Component
@Transactional(readOnly = false)
public class PasswordLogServiceImp implements PasswordLogService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(PasswordLogServiceImp.class);
	
	private SystemConfigDao  systemConfigDao;

	private PasswordLogDao passwordLogDao;
	@Autowired
	public void setPasswordLogDao(PasswordLogDao passwordLogDao) {
		this.passwordLogDao = passwordLogDao;
	}
	@Autowired
	public void setsystemConfigDao(SystemConfigDao systemConfigDao) {
		this.systemConfigDao = systemConfigDao;
	}
	
	public Page<PasswordLog> getLogs(Integer pageIndex, Integer pageSize) {
		PageRequest page = new PageRequest(pageIndex, pageSize,
				FileLog.getSortDESC("ts"));
		return this.passwordLogDao.findAll(page);
	}
	
	public static void  main(String[] args){
		String str = "2015-10-10 12:11:09";
		System.out.println(str.substring(0,str.lastIndexOf("-")+3).trim());
	}
	public String checkPasswordNeedUpdate(String userid) {
		if (userid != null) {
			//passwordlog获取该用户最后一次修改日志，但是因为旧版本在运营，有可能出现用户日志没有的情况，需要直接提示用户修改密码
			List<PasswordLog> passwordLogs=passwordLogDao.findByUseridOrderByOpertimeDesc(userid);
			if (passwordLogs.isEmpty()) {
				
				return "第一次登陆，请立即修改密码！";
			}
			SystemConfig systemConfig=systemConfigDao.findOneByCode("passwordperiod");
			int day=30;
			if (systemConfig != null) {
				if (systemConfig.isUsed() && systemConfig.getUserday() != null) {
					day=Integer.parseInt(systemConfig.getUserday());
				}
			}
			//passwordlog获取该用户最后一次修改日志，判断好当前日期的天数差，这个时候默认是30天修改密码
			//return "密级已经过期，需要立即修改！密码";
			if (!passwordLogs.isEmpty()) {
				try {
					if (SimpleUtils.daysBetweenToday(passwordLogs.get(0).getOpertime()) > day) 
					
					return "密码已经过期，需要立即修改密码！";
				} catch (ParseException e) {}
		    }
		return null;
	}
			
		return null;
	}

}
