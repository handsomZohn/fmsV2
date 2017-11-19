package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.UserRole;

/**
 * 权限组对象的Dao interface.
 * 
 * @author crazylion
 */

public interface UserRoleDao extends JpaRepository<UserRole, String> {
	public void deleteByUserId(String userid);
	
	/**
	 * 获取用户所有的角色
	 * @param userid
	 * @return
	 */
	@Query("SELECT roleId from UserRole ur WHERE ur.userId=:userid")
	public List<String>	findRolesByUserId(@Param("userid") String userid);
	
	
	public List<UserRole> findByUserId(@Param("userid") String userid);

}
