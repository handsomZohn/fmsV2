package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.Role;

/**
 * 角色对应的DAO
 * @author crazylion
 */

public interface RoleDao extends JpaRepository<Role, String> {
}
