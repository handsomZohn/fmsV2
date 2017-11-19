package com.casic.fms.dao;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.User;

/**
 * 用户对象的Dao interface.
 * 
 * @author crazylion
 */
@Resource
public interface UserDao extends JpaRepository<User, String> {

	User findByLoginName(String loginName);
	
	User findByEmail(String email);
	
	User findByLoginNameAndPassword(String loginName,String password);
}
