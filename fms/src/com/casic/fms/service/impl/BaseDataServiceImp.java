package com.casic.fms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.FileClassify;
import com.casic.fms.bean.FileSecurity;
import com.casic.fms.bean.FileType;
import com.casic.fms.bean.ProcessList;
import com.casic.fms.bean.SecurityLevel;
import com.casic.fms.bean.SecurityLimit;
import com.casic.fms.dao.FileClassifyDao;
import com.casic.fms.dao.FileTypeDao;
import com.casic.fms.dao.ProcessListDao;
import com.casic.fms.dao.SecurityLevelDao;
import com.casic.fms.dao.SecurityLimitDao;
import com.casic.fms.service.BaseDataService;

@Component
@Transactional(readOnly = false)
public class BaseDataServiceImp implements BaseDataService {


	private SecurityLevelDao	securityLevelDao;
	private SecurityLimitDao	securityLimitDao;
	private FileTypeDao 		fileTypeDao;
	private ProcessListDao	processListDao;
	private FileClassifyDao fileClassifyDao;
	
	@Autowired
	public void setFileClassifyDao(FileClassifyDao fileClassifyDao) {
		this.fileClassifyDao = fileClassifyDao;
	}
	

	@Autowired
	public void setProcessListDao(ProcessListDao processListDao) {
		this.processListDao = processListDao;
	}
	@Autowired
	public void setSecurityLimitDao(SecurityLimitDao securityLimitDao) {
		this.securityLimitDao = securityLimitDao;
	}
	
	@Autowired
	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}
	
	
	@Autowired
	public void setSecurityLevelDao(SecurityLevelDao securityLevelDao) {
		this.securityLevelDao = securityLevelDao;
	}
	
	/**
	 * 根据文档扩展名获取文件的类型名称
	 */
	public String getFileTypeName(String code) {
		FileType ft = this.fileTypeDao.findOneByCode(code);
		if(ft == null){
			return "未定义的文档类型";
		}
		return ft.getName();
	}

	public String getSecruityName(String securityCode) {
		SecurityLevel sl= this.securityLevelDao.findOneByCode(securityCode);
		if(sl != null){
			return sl.getName();
		}
		return "未明确";
	}

	public String getOperationTypeName(String operationType) {
		// TODO Auto-generated method stub
		if("READ".equalsIgnoreCase(operationType)){
			return "读取";
		}else if("SAVE".equalsIgnoreCase(operationType)){
			return "保存";
		}else if("REMOVE".equalsIgnoreCase(operationType)){
			return "删除";
		}else if("SECURITY".equalsIgnoreCase(operationType)){
			return "定密";
		}else if("UNSECURITY".equalsIgnoreCase(operationType)){
			return "隔离";
		}else if("PRINT".equalsIgnoreCase(operationType)){
			return "打印";
		}else if("TOUSB".equalsIgnoreCase(operationType)){
			return "USB复制";
		}
		return "操作";
	}

	public List<FileType> getFileTypes() {
		// TODO Auto-generated method stub
		return this.fileTypeDao.findAll(FileType.getSortASC("code"));
	}

	public List<SecurityLevel> getSecurityLevels() {
		// TODO Auto-generated method stub
		return this.securityLevelDao.findAll(SecurityLevel.getSortASC("code"));
	}
	
	
	public FileType saveFileType(FileType ft){
		ft.setProcessMD5(clearRenStr(ft.getProcessMD5()));
		return this.fileTypeDao.save(ft);
	}

	
	public FileType addFileType(FileType ft) {
		if(ft.getCode() != null ){
			FileType oldft = this.fileTypeDao.findOneByCode(ft.getCode());
			if(oldft != null){
				String procmd5 = oldft.getProcessMD5();
				if(procmd5 == null || procmd5.trim().length()==0){
					oldft.setProcessMD5(ft.getProcessMD5());
				}else{
					if(procmd5.indexOf(ft.getProcessMD5())<0){
						//需要增加MD5进程
						if(!procmd5.endsWith(";")){
							procmd5.concat(";");
						}
						oldft.setProcessMD5(procmd5.concat(ft.getProcessMD5()));
					}
				}
				oldft.setProcessMD5(clearRenStr(oldft.getProcessMD5()));
				return this.fileTypeDao.save(oldft);
			}else{
				ft.setProcessMD5(clearRenStr(ft.getProcessMD5()));
				return this.fileTypeDao.save(ft);
			}
		}
		return null;
	}

	private String clearRenStr(String temp){
		if(temp !=null){
			temp = temp.replaceAll("\r","");
			temp = temp.replaceAll("\n","");
		}
		return temp;
	}

	public FileType getFileTypeByID(String id) {
		// TODO Auto-generated method stub
		return this.fileTypeDao.findOne(id);
	}
	
	/**
	 * 增加一个密级关键字设置
	 * @return
	 */
	public SecurityLimit	 addSecurityLimit(SecurityLimit sl){
		SecurityLimit oldsl = this.securityLimitDao.findByKeyword(sl.getKeyword());
		if(oldsl != null){
			oldsl.setSecurityCode(sl.getSecurityCode());
			return this.securityLimitDao.save(oldsl);
		}
		return this.securityLimitDao.save(sl);
	}
	
	public void removeSecurityLimit(String id){
		this.securityLimitDao.delete(id);
		return;
	}
	
	public Page<SecurityLimit>  getSecurityLimitList(Integer pageIndex,Integer pageSize){
		Pageable 	page = new PageRequest(pageIndex, pageSize,FileSecurity.getSortASC("keyword"));
		return this.securityLimitDao.findAll(page);
	}

	public SecurityLimit getSecurityLimit(String id) {
		// TODO Auto-generated method stub
		return this.securityLimitDao.findOne(id);
	}

	public List<SecurityLimit> getAllSecurityLimit() {
		// TODO Auto-generated method stub
		return securityLimitDao.findAll(SecurityLimit.getSortASC("keyword"));
	}

	public ProcessList  	getProcessList(String id){
		return this.processListDao.findOne(id);
	}
	public List<ProcessList>		getAllProcessList(){
		return this.processListDao.findAll(ProcessList.getTsSortASC());
	}
	public ProcessList	addProcessList(ProcessList pl){
		ProcessList plold  = this.processListDao.findOneByProcessName(pl.getProcessName());
		if(plold != null){
			plold.setName(pl.getName());
			plold.updateTs();
			return this.processListDao.save(plold);
		}
		return this.processListDao.save(pl);
	}
	public void deleteProcessList(String id) {
		// TODO Auto-generated method stub
		this.processListDao.delete(id);
	}
	public Page<FileClassify> getFileClassifyList(Integer pageIndex,
			Integer pageSize) {
		Pageable 	page = new PageRequest(pageIndex, pageSize,FileSecurity.getSortASC("name"));
		return this.fileClassifyDao.findAll(page);
	}
	
	/**
	 * 增加一个文件分类
	 */
	public FileClassify addFileClassify(FileClassify classify) {
		FileClassify oldClassify=this.fileClassifyDao.findByName(classify.getName());
		if (oldClassify != null) {
			oldClassify.setCode(classify.getCode());
			oldClassify.setName(classify.getName());
			oldClassify.setComments(classify.getComments());
			return this.fileClassifyDao.save(oldClassify);
		}
		return this.fileClassifyDao.save(classify);
	}


	/**
	 * 更新文件分类
	 */
	public FileClassify getFileClassify(String id) {
		// TODO Auto-generated method stub
		return this.fileClassifyDao.findOne(id);
	}


	
}
