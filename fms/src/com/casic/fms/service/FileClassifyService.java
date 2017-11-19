package com.casic.fms.service;

import com.casic.fms.bean.FileClassify;


public interface FileClassifyService {

	void delete(String id);

	/**
	 * 更新
	 * @param id
	 * @return
	 */
	public FileClassify getFileClassify(String id);
}
