package com.casic.fms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.FileClassify;
import com.casic.fms.dao.FileClassifyDao;
import com.casic.fms.service.FileClassifyService;
@Service
@Transactional(readOnly = false)
public class FileClassifyServiceImpl extends BaseServiceImp implements
		FileClassifyService {
	private FileClassifyDao fileClassifyDao;
	@Autowired
	public void setFileClassifyDao(FileClassifyDao fileClassifyDao) {
		this.fileClassifyDao = fileClassifyDao;
	}
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.fileClassifyDao.delete(id);
	}
	
	/**
	 * 更新文件分类
	 */
	public FileClassify getFileClassify(String id) {
		// TODO Auto-generated method stub
		return this.fileClassifyDao.findOne(id);
	}

}
