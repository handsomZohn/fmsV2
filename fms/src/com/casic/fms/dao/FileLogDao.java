package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.FileLog;

/**
 * 权限组对象的Dao interface.
 * 
 * @author crazylion
 */

public interface FileLogDao extends JpaRepository<FileLog, String>{
	
	
	@Query("SELECT DISTINCT fileFullname FROM FileLog")
	List<String>  getFile();

	@Query("SELECT COUNT(fs.fileId) FROM FileLog fs WHERE fs.operation='SECURITY'")
	long getSecurityTotalCount();

	
	/**
	 * 找出今天之前的所有日志
	 * @param today
	 * @return
	 */
	List<FileLog>	findByTsBefore(String today);
	
	@Query("SELECT DISTINCT fileFullname FROM FileLog f WHERE f.clientMac=:mac")
	Long getFileCountByClient(@Param("mac") String mac);

	@Query("SELECT COUNT(id) FROM FileLog fl WHERE fl.ts LIKE :daydate%  ")
	long  getCountByDate(@Param("daydate") String daydate);

	@Query("SELECT DISTINCT fl.usbkey FROM FileLog fl WHERE fl.usbkey =:usbkey")
	String findOneByUsbkey(@Param("usbkey")String usbkey);
	
}
