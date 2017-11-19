package com.casic.fms.dao;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.PasswordLog;

/**
 * 权限组对象的Dao interface.
 * 
 * @author crazylion
 */

public interface PasswordLogDao extends JpaRepository<PasswordLog, String>{
	
	


	
	/**
	 * 找出今天之前的所有日志
	 * @param today
	 * @return
	 */
	List<PasswordLog>	findByTsBefore(String today);

	/**
	 * 找出所有的密码修改日志
	 * @param userid
	 * @return
	 */
	List<PasswordLog>   findByUseridOrderByOpertimeDesc(String userid);



	


}
