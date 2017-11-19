package com.casic.fms.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.casic.common.SimpleUtils;
import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.FileSecurity;
import com.casic.fms.dao.FileLogDao;
import com.casic.fms.dao.FileSecurityDao;
import com.casic.fms.service.FileLogService;

@Component
@Transactional(readOnly = false)
public class FileLogServiceImp implements FileLogService {

	private static Logger logger = LoggerFactory
			.getLogger(FileLogServiceImp.class);
	private final String serviceName = "filelog.cache";

	private FileLogDao fileLogDao;
	private FileSecurityDao fileSecurityDao;

	@Autowired
	public void setFileSecurityDao(FileSecurityDao fileSecurityDao) {
		this.fileSecurityDao = fileSecurityDao;
	}

	@Autowired
	public void setFileLogDao(FileLogDao fileLogDao) {
		this.fileLogDao = fileLogDao;
	}

	private final Map<String, FileLog> getFileCache() {
		ServletContext sc = ContextLoader.getCurrentWebApplicationContext()
				.getServletContext();
		Map<String, FileLog> instance = (Map<String, FileLog>) sc
				.getAttribute(serviceName);
		if (instance == null) {
			synchronized (FileLogServiceImp.class) {
				instance = Collections.synchronizedMap(new HashMap<String, FileLog>());
				sc.setAttribute(serviceName, instance);
			}
		}
		return instance;
	}

	private boolean existLog(FileLog fileLog) {
		if (fileLog.getFileFullname().indexOf("~") > 0)
			return true;
		if (fileLog.getFileFullname().indexOf("\\Recent\\") > 0)
			return true;
		if (fileLog.getClientMac() != null && fileLog.getFileFullname() != null
				&& fileLog.getOperation() != null
				&& fileLog.getOpertime() != null) {
			String key = fileLog.getClientMac()
					+ fileLog.getFileFullname()
					+ fileLog.getOperation()
					+ fileLog.getOpertime().substring(0,
							fileLog.getOpertime().lastIndexOf(":") - 3);
			if (getFileCache().containsKey(key)) {
				return true;
			} else {
				getFileCache().put(key, fileLog);
				if(getFileCache().size()>10000){
					getFileCache().clear();
				}
				return false;
			}
		}
		return true;
	}

	private String getFileName(String filename) {
		// if(filename.indexOf("(")>0){
		// filename = filename.substring(0,
		// filename.indexOf("("))+filename.substring(filename.indexOf("."));
		// }
		try {
			return new String(filename.getBytes(getEncoding(filename)), "utf-8");
		} catch (Exception ex) {
			logger.error("转换字符集出错！", ex);
		}
		return filename;
	}

	public FileLog saveLog(FileLog fileLog) {
		if (fileLog == null)
			return fileLog;
		fileLog.updateTs();
		fileLog.setFileFullname(getFileName(fileLog.getFileFullname()));
		if (fileLog.getFileFullname() != null
				&& fileLog.getFileFullname().lastIndexOf(".") > 0) {
			String suffixName = fileLog.getFileFullname().substring(
					fileLog.getFileFullname().lastIndexOf(".") + 1);
			fileLog.setIdentifyCode(suffixName);
			fileLog.setSuffixName(suffixName);
			if(!existLog(fileLog)){
				//一个小时只记录一次
				return this.fileLogDao.save(fileLog);
			}
		}
		return fileLog;
	}

	public Page<FileSecurity> getFileSecuritys(Integer pageIndex,
			Integer pageSize) {
		Pageable page = new PageRequest(pageIndex, pageSize,
				FileSecurity.getSortDESC("ts"));
		return this.fileSecurityDao.findAll(page);
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/**
	 * 定密申请
	 */
	public FileSecurity applySecruity(FileSecurity fsc) {
		fsc.setFileName(getFileName(fsc.getFileName()));
		fsc.setStatus(FileSecurity.STATUS_PASS);
		fsc.setOpertime(SimpleUtils.getCreateTime());
		fsc.setAuthority(fsc.getUsbkey());
		if (fsc.getUsername() == null || fsc.getUsername().equals(""))
			fsc.setUsername(fsc.getUsbkey());
		fsc.updateTs();
		return this.fileSecurityDao.save(fsc);
	}

	public Page<FileLog> getLogs(Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		PageRequest page = new PageRequest(pageIndex, pageSize,
				FileLog.getSortDESC("ts"));
		return this.fileLogDao.findAll(page);
	}
	
	public void clearCache(){
		getFileCache().clear();
	}
	
	public static void  main(String[] args){
		String str = "2015-10-10 12:11:09";
		System.out.println(str.substring(0,str.lastIndexOf(":") - 3).trim());
	}

}
