package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.FileLogHistory;

/**
 * 权限组对象的Dao interface.
 * 
 * @author crazylion
 */
public interface FileLogHistoryDao extends JpaRepository<FileLogHistory, String>, JpaSpecificationExecutor<FileLogHistory>{
	
	@Query("SELECT DISTINCT clientMac FROM FileLogHistory")
	List<String> getClient();
	
	@Query("SELECT DISTINCT fileFullname FROM FileLogHistory")
	List<String>  getFile();
	
	@Query("SELECT DISTINCT fileFullname FROM FileLogHistory f WHERE f.clientMac=:mac")
	Long getFileCountByClient(@Param("mac") String mac);

	@Query("SELECT COUNT(id) FROM FileLogHistory fl WHERE fl.ts LIKE :daydate%  ")
	long  getCountByDate(@Param("daydate") String daydate);
	
	@Query("SELECT COUNT(fs.fileId) FROM FileLogHistory fs WHERE fs.operation=:operation AND fs.clientMac =:mac AND fs.identifyCode=:identifyCode AND fs.secruityLevel=:secruityLevel ")
	long getCountByClientMac(@Param("operation")String operation,@Param("mac")String clientMac, @Param("identifyCode")String identifyCode,@Param("secruityLevel")String secruityLevel);

	@Query("SELECT COUNT(fs.fileId) FROM FileLogHistory fs WHERE fs.operation='SECURITY'")
	long getSecurityTotalCount();
	
	/**
	 * 根据MAC地址获取日志
	 * @param clientMac
	 * @param sort
	 * @return
	 */
	Page<FileLogHistory>		findAllByClientMac(String clientMac, Pageable page);
	
	/**
	 * 根据usbkey获取日志
	 * @param fileid
	 * @param page
	 * @return
	 */
	Page<FileLogHistory>		findByUsbkey(String fileid,Pageable page);
	
	@Query("SELECT MIN(fl.opertime) FROM FileLogHistory fl ")
	String getMinOperationTime();
	
	@Query("SELECT MAX(fl.opertime) FROM FileLogHistory fl ")
	String getMaxOperationTime();
	
	/**
	 * 此方法在数据量超过10W的时候一定要禁止使用，前期为了获取历史数据的期间临时采用此方法。
	 * @return
	 */
	@Query("SELECT distinct(SUBSTRING(fl.opertime,0,8)) as ym FROM FileLogHistory fl  ORDER BY ym")
	List<String> getListYearMonth();
	
	@Query("SELECT fs.usbkey,fs.secruityLevel,fs.identifyCode, COUNT(fs.id), SUM(CASE WHEN fs.operation='SECURITY' THEN 1 ELSE 0 END)  FROM FileLogHistory fs  WHERE fs.opertime LIKE :ym%  GROUP BY fs.usbkey,fs.secruityLevel,fs.identifyCode")
	List<Object> getGroupCountByTime(@Param("ym")String ym);

}
