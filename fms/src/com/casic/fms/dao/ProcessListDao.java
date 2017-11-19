package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casic.fms.bean.ProcessList;

/**
 * 系统进程白名单列表
 *
 * @author crazylion
 */

public interface ProcessListDao extends JpaRepository<ProcessList, String> {
	
	ProcessList findOneByProcessName(String pname);
}
