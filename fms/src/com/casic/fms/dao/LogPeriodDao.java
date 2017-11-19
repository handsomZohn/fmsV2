package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.LogPeriod;

/**
 * 系统日志期间目录
 * @author crazylion
 */

public interface LogPeriodDao extends JpaRepository<LogPeriod, String> {
	

	@Query("SELECT MAX(CONCAT(u.year,u.month)) FROM LogPeriod u")
	String  getMaxYearMonth();
	
	@Query("SELECT DISTINCT u.year FROM LogPeriod u ORDER BY u.year DESC")
	List<String>		getYearList();
	
	@Query("SELECT DISTINCT u.month FROM LogPeriod u WHERE year=:year ORDER BY u.month")
	List<String>		getMonthsByYear(@Param("year") String year);
	
	LogPeriod findOneByYearAndMonth(String strYear,String Month);
	
	@Query("FROM LogPeriod lp WHERE lp.year>=:year and lp.month>=:month")
	List<LogPeriod> getAllPeriodFromYM(@Param("year")String year,@Param("month")String month);
}
