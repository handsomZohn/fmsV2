package com.casic.fms.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.casic.fms.bean.FileLogHistory;
import com.casic.fms.bean.LogPeriod;
import com.casic.fms.bean.OrgInfoBean;
import com.casic.fms.bean.PCClient;
import com.casic.fms.bean.USBKeyInfo;
import com.casic.fms.bean.query.FileLogQueryBean;
import com.casic.fms.bean.query.QuarterBean;
import com.casic.fms.bean.report.OrginfoMonthly;

/**
 * 日志统计服务类
 * @author sean
 *
 */
public interface LogStatisticService {
	
	public final static String SECURITY="SECURITY"; //（定密总数），
	public final static String MONITOR="MONITOR";//(监控总数)
	
	/**
	 * 获取最新多少天的日期
	 * @return
	 */
	List<String>		getLastDays(int dayNums, int offset, String endDate);
	
	/**
	 * 根据天数和操作类型，获取每天的操作总数
	 * @param days
	 * @param opertype
	 * @return
	 */
	Object	getDaysSummaryByType(int days,String opertype);
	
	/**
	 * 将当天的日志数据发送到历史表中进行保存。
	 * 系统将会是一个自动任务在执行，不影响白天的操作。
	 */
	void transferHistory();
	
	/**
	 * 统计每天的数据
	 */
	void summaryDailyLog();
	
	
	void cleanupClientInfo();
	/**
	 * 根据IP或者MAC地址查询文件操作日志
	 * @param mac
	 * @param ip
	 * @return
	 */
	Page<FileLogHistory>	getByClient(String mac,Integer pageIndex, Integer pageSize );
	
	Page<FileLogHistory>	getLogs(Integer pageIndex, Integer pageSize, FileLogQueryBean  queryBean);
	
	long getLogCount();
	
	long getClientCount();
	
	public long getSecurityCount();
	
	
	public Page<PCClient>	  getClientList(Integer pageIndex, Integer pageSize);
	
	public PCClient	getPCClient(String mac);
	
	public PCClient	updatePCClient(PCClient client);
	
	public USBKeyInfo 	getUSBKey(String usbkey);
	
	public Map<String,List<Long>>  summaryClientByMac(String mac);
	
	/**
	 * 计算月报表,如果orgId为空，则计算整个公司的报表
	 * @return
	 */
	public int summaryUserMonthlyReport();
	
	/**
	 * 计算年度报表,如果orgId为空，则计算整个公司的报表
	 * @return
	 */
	public int summaryUserYearlyReport();
	
	/**
	 * 计算季度报表，如果orgId为空，则计算整个公司的报表
	 * @param orgId
	 * @return
	 */
	public int summaryUserQuarterReport();

	/**
	 * 计算月报表,如果orgId为空，则计算整个公司的报表
	 * @return
	 */
	public int summaryOrginfoMonthlyReport();
	
	/**
	 * 计算年度报表,如果orgId为空，则计算整个公司的报表
	 * @return
	 */
	public int summaryOrginfoYearlyReport();
	
	/**
	 * 计算季度报表，如果orgId为空，则计算整个公司的报表
	 * @param orgId
	 * @return
	 */
	public int summaryOrginfoQuarterReport();
	
	
	public String summaryLogPeriod();
	
	/**
	 * 查询机构的月度统计报表
	 * @param orginfoid
	 * @param ym
	 * @return
	 */
	public Page<OrginfoMonthly> queryOrgMonthlyReport(String orginfoid, String year,String month, Integer pageIndex,Integer pageSize);
	
	public Page<OrginfoMonthly> queryOrgAnnualReport(String orginfoid, String year, Integer pageIndex,Integer pageSize);

	
	public LogPeriod		findMaxPeriod();	
	
	
	public  List<String>		getReportYears();
	
	public  List<String>		getReportMonths(String year);
	
	public  List<QuarterBean>		getReportQuarter();
	
	public String createOrgMonthlyReportTxt(FileLogQueryBean queryBean, String format);

	public String createOrgAnnualReportTxt(FileLogQueryBean queryBean, String format);

	public String createOrgQuarterReportTxt(FileLogQueryBean queryBean, String format);
	/**
	 * 根据密级信息统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getMonthlySecruityGroupSummary(FileLogQueryBean queryBean);
	/**
	 * 根据文件类型统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getMonthlyFileTypeGroupSummary(FileLogQueryBean queryBean);

	/**
	 * 根据密级信息统计每年的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getAnnualSecruityGroupSummary(FileLogQueryBean queryBean);
	/**
	 * 根据文件类型统计每年的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getAnnualFileTypeGroupSummary(FileLogQueryBean queryBean);
	
	
	/**
	 * 根据密级信息统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getQuarterSecruityGroupSummary(FileLogQueryBean queryBean);
	/**
	 * 根据文件类型统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getQuarterFileTypeGroupSummary(FileLogQueryBean queryBean);

	
	/**
	 * 查询我的台账
	 * @param pageIndex
	 * @param pageSize
	 * @param queryBean
	 * @return
	 */
	public Page<FileLogHistory>	searchOrgLogsByUsbkey(Integer pageIndex, Integer pageSize, FileLogQueryBean  queryBean);
}
