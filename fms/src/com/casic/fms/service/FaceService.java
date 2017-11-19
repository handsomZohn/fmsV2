package com.casic.fms.service;

import com.casic.fms.bean.Face;

public interface FaceService {
	
	/**
	 * 方法功能：根据头像id获得头像信息. <BR>
	 * @param id
	 * @return
	 * @see com.casic.fms.service.AuthenticationService
	 */
	public Face getFace(String id);
	
	/**
	 * 方法功能：保存头像信息. <BR>
	 * @param entity
	 * @return 头像
	 * @see com.casic.fms.service.AuthenticationService
	 */
	public Face saveFace(Face entity);
	
	/**
	 * 方法功能：根据头像id删除头像. <BR>
	 * @param id
	 * @see com.casic.fms.service.AuthenticationService
	 */
	public void deleteFace(String id);
	
	/**
	 * 方法功能：根据头像id获取头像信息. <BR>
	 * @param id
	 * @return
	 * @see com.casic.fms.service.AuthenticationService
	 */
	public Face findFaceById(String id);
	
}
