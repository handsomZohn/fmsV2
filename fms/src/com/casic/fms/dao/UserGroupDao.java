package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.UserGroup;

/**
 * 权限组对象的Dao interface.
 * 
 * @author crazylion
 */

public interface UserGroupDao extends JpaRepository<UserGroup, String> {
}
