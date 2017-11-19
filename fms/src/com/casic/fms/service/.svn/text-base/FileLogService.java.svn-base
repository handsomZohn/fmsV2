package com.casic.fms.service;

import org.springframework.data.domain.Page;

import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.FileSecurity;

public interface FileLogService {
	

	/**
	 * 文件申请定密
	 * @param fileSecurityDefine
	 * @return
	 */
	FileSecurity applySecruity(FileSecurity fileSecurityDefine);
	
	Page<FileSecurity> getFileSecuritys(Integer pageIndex, Integer pageSize);
	/**
	 * 保存文件操作日志
	 * @param fileLog
	 * @return
	 */
	FileLog  saveLog(FileLog fileLog);
	
	/**
	 * 查询当天最新的日志
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Page<FileLog>	getLogs(Integer pageIndex, Integer pageSize);

	public void clearCache();

}
