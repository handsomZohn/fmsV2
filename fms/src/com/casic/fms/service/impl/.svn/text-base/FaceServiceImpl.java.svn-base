package com.casic.fms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.Face;
import com.casic.fms.dao.FaceDao;
import com.casic.fms.service.FaceService;

@Service
@Transactional(readOnly = true)
public class FaceServiceImpl implements FaceService {
	private FaceDao faceDao;
	
	@Autowired
	public void setFaceDao(FaceDao faceDao){
		this.faceDao = faceDao;
	}
	
	@Transactional(readOnly = false)
	public void deleteFace(String id) {
		faceDao.delete(id);
	}

	public Face findFaceById(String id) {
		return faceDao.findById(id);
	}

	public Face getFace(String id) {
		return faceDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public Face saveFace(Face entity) {
		return faceDao.save(entity);
	}

}
