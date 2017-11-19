package com.casic.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.FileLogHistory;
import com.casic.fms.bean.report.OrginfoMonthly;

/**
 * USBkey月度统计数据的信息 interface.
 * 
 * @author crazylion
 */
public interface OrgInfoMonthlyDao extends JpaRepository<OrginfoMonthly, String>, JpaSpecificationExecutor<FileLogHistory>{
	
	
	@Query("SELECT MAX(CONCAT(u.year,u.month)) FROM OrginfoMonthly u WHERE u.orginfoId =:orgid")
	String  getMaxYearMonthByOrg(@Param("orgid")String orginfId);

	
	void deleteByOrginfoId(String orgid);
	
	//@Query("FROM OrginfoMonthly u WHERE u.year =:year AND u.month=:month AND u.orgCode LIKE:orgCode%")
	Page<OrginfoMonthly>		findByYearAndMonthAndOrgCodeLike(String year,String month,String orgCode, Pageable pageable);
	
	Page<OrginfoMonthly>		findByYearAndOrgCodeLike(String year,String orgCode, Pageable pageable);

	Page<OrginfoMonthly>		findByYearAndMonth(String year,String month, Pageable pageable);

	Page<OrginfoMonthly>		findByYear(String year, Pageable pageable);

	@Query("SELECT SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month=:month ")
	Long sumByYearAndMonth(@Param("year")String year,@Param("month")String month);
	
	@Query("SELECT SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month=:month AND u.orgCode LIKE :orgCode% ")
	Long sumByYearAndMonthAndOrgCodeLike(@Param("year")String year,@Param("month")String month,@Param("orgCode")String orgCode);
	
	@Query("SELECT securityCode,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month=:month GROUP BY u.securityCode")
	List<Object> getMonthlyGroupSummaryBySecurityLevel(@Param("year")String year,@Param("month")String month);
	
	@Query("SELECT securityCode,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month=:month AND u.orgCode LIKE :orgCode%  GROUP BY u.securityCode")
	List<Object> getMonthlyOrgGroupSummaryBySecurityLevel(@Param("year")String year,@Param("month")String month,@Param("orgCode")String orgCode);

	@Query("SELECT fileType,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month=:month GROUP BY u.fileType" )
	List<Object> getMonthlyGroupSummaryByFileType(@Param("year")String year,@Param("month")String month);
	
	@Query("SELECT fileType,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month=:month AND u.orgCode LIKE :orgCode%  GROUP BY u.fileType")
	List<Object> getMonthlyOrgGroupSummaryByFileType(@Param("year")String year,@Param("month")String month,@Param("orgCode")String orgCode);

	@Query("SELECT SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year ")
	Long sumByYear(@Param("year")String year);
	
	@Query("SELECT SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.orgCode LIKE :orgCode% ")
	Long sumByYearAndOrgCodeLike(@Param("year")String year,@Param("orgCode")String orgCode);
	
	@Query("SELECT securityCode,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year GROUP BY u.securityCode")
	List<Object> getAnnualGroupSummaryBySecurityLevel(@Param("year")String year);
	
	@Query("SELECT securityCode,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.orgCode LIKE :orgCode%  GROUP BY u.securityCode")
	List<Object> getAnnualOrgGroupSummaryBySecurityLevel(@Param("year")String year,@Param("orgCode")String orgCode);

	@Query("SELECT fileType,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year GROUP BY u.fileType" )
	List<Object> getAnnualGroupSummaryByFileType(@Param("year")String year);
	
	@Query("SELECT fileType,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.orgCode LIKE :orgCode%  GROUP BY u.fileType")
	List<Object> getAnnualOrgGroupSummaryByFileType(@Param("year")String year,@Param("orgCode")String orgCode);


	
	@Query("SELECT SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month in:months ")
	Long sumByYearAndQuarter(@Param("year")String year,@Param("months")List<String> months);
	
	@Query("SELECT SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month in:months AND u.orgCode LIKE :orgCode% ")
	Long sumByYearAndQuarterAndOrgCodeLike(@Param("year")String year,@Param("months")List<String> months,@Param("orgCode")String orgCode);
	
	@Query("SELECT securityCode,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month in:months  GROUP BY u.securityCode")
	List<Object> getQuarterGroupSummaryBySecurityLevel(@Param("year")String year,@Param("months")List<String> months);
	
	@Query("SELECT securityCode,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month in:months AND u.orgCode LIKE :orgCode%  GROUP BY u.securityCode")
	List<Object> getQuarterOrgGroupSummaryBySecurityLevel(@Param("year")String year,@Param("months")List<String> months,@Param("orgCode")String orgCode);

	@Query("SELECT fileType,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month in:months  GROUP BY u.fileType" )
	List<Object> getQuarterGroupSummaryByFileType(@Param("year")String year,@Param("months")List<String> months);
	
	@Query("SELECT fileType,SUM(settingNumber) FROM OrginfoMonthly u WHERE u.year=:year AND u.month in:months AND u.orgCode LIKE :orgCode%  GROUP BY u.fileType")
	List<Object> getQuarterOrgGroupSummaryByFileType(@Param("year")String year,@Param("months")List<String> months,@Param("orgCode")String orgCode);

	
}
