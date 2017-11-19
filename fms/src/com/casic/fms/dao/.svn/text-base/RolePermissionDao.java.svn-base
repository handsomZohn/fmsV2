package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.RolePermission;

/**
 * 权限组对象的Dao interface.
 * 
 * @author crazylion
 */

public interface RolePermissionDao extends JpaRepository<RolePermission, String> {
	
	public void deleteByRoleId(String roleid);
	
	@Query("SELECT permissionId FROM RolePermission rp WHERE rp.roleId in:roleids ")
	public List<String>	findPermissionsByRoleids(@Param("roleids") List<String>  roleids);
}
