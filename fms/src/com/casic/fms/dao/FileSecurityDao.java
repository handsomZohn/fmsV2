package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.FileSecurity;

/**
 * 
 * @author crazylion
 */

public interface FileSecurityDao extends JpaRepository<FileSecurity, String> {
	
	/**
	 * 根据mac地址和文件名称，获取文件的定密申请记录
	 * @param mac
	 * @param fileName
	 * @return
	 */
	List<FileSecurity>		findAllByClientMacAndFileName(String  mac,String fileName);
	
	@Query("SELECT COUNT(id) FROM FileSecurity fs WHERE fs.ts LIKE :daydate%  ")
	long  getCountByDate(@Param("daydate") String daydate);
	
}
