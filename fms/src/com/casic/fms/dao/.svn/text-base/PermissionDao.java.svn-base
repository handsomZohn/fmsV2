package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.Permission;

/**
 * 权限组对象的Dao interface.
 * 
 * @author crazylion
 */

public interface PermissionDao extends JpaRepository<Permission, String> {
	
	
	public void deleteByResourceCodeAndOperationCode(String resourceid,String operationid);
	
	List<Permission>		findAllByIdIn(List<String> ids);
	
	public Permission findOneByResourceCodeAndOperationCode(String resourceid,String operationid);
}
