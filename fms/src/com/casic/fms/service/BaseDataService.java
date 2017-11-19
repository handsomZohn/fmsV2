package com.casic.fms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.casic.fms.bean.FileClassify;
import com.casic.fms.bean.FileSecurity;
import com.casic.fms.bean.FileType;
import com.casic.fms.bean.ProcessList;
import com.casic.fms.bean.SecurityLevel;
import com.casic.fms.bean.SecurityLimit;


/**
 * 基础数据平台
 * @author crazylion
 *
 */
public interface BaseDataService {
	
	public FileType saveFileType(FileType ft);	
	/**
	 * 根据ID获取文件类型
	 * @param id
	 * @return
	 */
	public FileType  getFileTypeByID(String id);

	/**
	 * 
	 * 增加文档类型
	 * @param ft
	 * @return
	 */
	public 	FileType  addFileType(FileType  ft);
	
	/**
	 * 获取系统监控的文件类型列表
	 * @return
	 */
	public List<FileType>	getFileTypes();
	
	/**
	 * 获取保密级别列表
	 * @return
	 */
	public List<SecurityLevel>	getSecurityLevels();
	
	
	/**
	 * 获取文件类型
	 * @param fileTypeCode
	 * @return
	 */
	String getFileTypeName(String fileTypeCode);
	
	/**
	 * 保密级别
	 * @param securityCode
	 * @return
	 */
	String getSecruityName(String securityCode);
	
	/**
	 * 获取文件类型名称 
	 * @param operationType
	 * @return
	 */
	String getOperationTypeName(String operationType);
	
	public List<SecurityLimit>	getAllSecurityLimit();
	/**
	 * 增加一个密级关键字设置
	 * @return
	 */
	public SecurityLimit	 addSecurityLimit(SecurityLimit sl);
	
	public SecurityLimit getSecurityLimit(String id);
	public void removeSecurityLimit(String id);
	
	public Page<SecurityLimit>  getSecurityLimitList(Integer pageIndex,Integer pageSize);
	
	public List<ProcessList>		getAllProcessList();
	
	public ProcessList	addProcessList(ProcessList pl);
	
	public ProcessList  	getProcessList(String id);

	public void deleteProcessList(String id);
	/**
	 * 获取所有的文件分类
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<FileClassify> getFileClassifyList(Integer pageIndex,
			Integer pageSize);
	/**
	 * 增加一个文件分类
	 * @param classify
	 * @return
	 */
	public FileClassify addFileClassify(FileClassify classify);
	public FileClassify getFileClassify(String id);
}
