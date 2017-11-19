package com.casic.fms.service;

import java.util.List;

import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.VersionInfo;


public interface VersionInfoService {
	
	VersionInfo getOneByID(String id);
	
	/**
	 * 获取最新的版本号
	 * @return
	 */
	VersionInfo getLastVersion();
	
	/**
	 * 添加新的版本信息
	 * @return
	 */
	ReInfo addVersionInfo(VersionInfo  vi);
	

	/**
	 * 获取文件版本列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<VersionInfo> getVersions();

}
