package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.OrgInfoBean;

/**
 * 组织结构DAO定义
 *
 * @author crazylion
 */

public interface OrgInfoDao extends JpaRepository<OrgInfoBean, String> {

	OrgInfoBean findOneByCode(String orgCode);
	/**
	 * 根据父类信息获取子类的数据
	 * @param parentid
	 * @return
	 */
	public List<OrgInfoBean>		findByParentId(String parentid);

	/**
	 * 获取一级部门信息
	 **/
	public List<OrgInfoBean>	    findByParentIdIsNull();
	
}
