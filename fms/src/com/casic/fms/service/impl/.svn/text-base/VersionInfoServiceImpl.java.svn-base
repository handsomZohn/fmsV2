package com.casic.fms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.Message;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.VersionInfo;
import com.casic.fms.dao.MessageDao;
import com.casic.fms.dao.VersionInfoDao;
import com.casic.fms.service.MessageService;
import com.casic.fms.service.VersionInfoService;

/**
 * 消息管理服务类
 * 
 * @author crazylion
 */
@Component
@Transactional(readOnly = false)
public class VersionInfoServiceImpl implements VersionInfoService{
	
	private static Logger logger = LoggerFactory.getLogger(VersionInfoServiceImpl.class);

	private VersionInfoDao versionInfoDao;
	
	@Autowired
	public void setVersionInfoDao(VersionInfoDao versionInfoDao) {
		this.versionInfoDao = versionInfoDao;
	}

	public VersionInfo getLastVersion() {
		List<VersionInfo>  list = this.versionInfoDao.findAll(VersionInfo.getSortDESC("code"));
		if(list != null && list.size()>0)	return list.get(0);
		VersionInfo vi = new VersionInfo();
		vi.setCode("0");
		vi.setComment("没有版本定义！");
		return vi;
	}

	public ReInfo addVersionInfo(VersionInfo vi) {
		// TODO Auto-generated method stub
		ReInfo failed = ReInfo.getFailed();
		if(vi == null || vi.getCode()== null || ("").equals(vi.getCode())){
			failed.message = "新增的版本信息不能为空！";
			return failed;
		}
		VersionInfo oldvi = this.versionInfoDao.findOneByCode(vi.getCode());
		if(oldvi != null ){
			failed.message = "增加的版本号("+oldvi.getCode()+")已经存在！";
			return failed;
		}
		this.versionInfoDao.save(vi);
		return ReInfo.getSucceed();
	}

	public List<VersionInfo> getVersions() {
		// TODO Auto-generated method stub
		return this.versionInfoDao.findAll(VersionInfo.getSortDESC("code"));
	}

	public VersionInfo getOneByID(String id) {
		// TODO Auto-generated method stub
		return this.versionInfoDao.findOne(id);
	}


	
}
