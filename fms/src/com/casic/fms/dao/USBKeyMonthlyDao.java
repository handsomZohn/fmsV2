package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.FileLogHistory;
import com.casic.fms.bean.report.USBKeyMonthly;

/**
 * USBkey月度统计数据的信息 interface.
 * 
 * @author crazylion
 */
public interface USBKeyMonthlyDao extends JpaRepository<USBKeyMonthly, String>, JpaSpecificationExecutor<FileLogHistory>{
	
	@Query("SELECT MAX(CONCAT(u.year,u.month)) FROM USBKeyMonthly u")
	String  getMaxYearMonth();
	
	@Query("SELECT ul.year, ul.month, ul.securityCode, ul.fileType, SUM(ul.settingNumber), SUM(ul.logNumber) FROM USBKeyMonthly ul WHERE ul.usbkey IN:usbkeys GROUP BY ul.year, ul.month, ul.securityCode, ul.fileType")
	List<Object> getOrgGroupCount(@Param("usbkeys")List<String> usbkeys);

	@Query("SELECT ul.year, ul.month, ul.securityCode, ul.fileType, SUM(ul.settingNumber), SUM(ul.logNumber) FROM USBKeyMonthly ul WHERE ul.usbkey IN:usbkeys AND CONCAT(ul.year,ul.month)>:yearmonth GROUP BY ul.year, ul.month, ul.securityCode, ul.fileType")
	List<Object> getOrgGroupCountByYM(@Param("usbkeys")List<String> usbkeys,@Param("yearmonth") String yearmonth);
	
	@Query("SELECT ul.securityCode, SUM(ul.settingNumber)  FROM USBKeyMonthly ul WHERE ul.year=:year AND ul.month=:month GROUP BY ul.securityCode")
	List<Object> getSummarySecurityByYM(@Param("year") String year,@Param("month") String month);

	@Query("SELECT ul.fileType, SUM(ul.settingNumber)  FROM USBKeyMonthly ul WHERE ul.year=:year AND ul.month=:month GROUP BY ul.fileType")
	List<Object> getSummaryFiletypeByYM(@Param("year") String year,@Param("month") String month);
	
	@Modifying
	@Query("DELETE USBKeyMonthly um WHERE um.year=:year AND um.month=:month")
	void deleteByYearAndMonth(@Param("year")String year,@Param("month")String month);
}
