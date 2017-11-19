package com.casic.fms.service.impl;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.FileLogHistory;
import com.casic.fms.bean.FileType;
import com.casic.fms.bean.LogPeriod;
import com.casic.fms.bean.OrgInfoBean;
import com.casic.fms.bean.OrgUSBKeyInfo;
import com.casic.fms.bean.PCClient;
import com.casic.fms.bean.SecurityLevel;
import com.casic.fms.bean.USBKeyInfo;
import com.casic.fms.bean.query.FileLogQueryBean;
import com.casic.fms.bean.query.QuarterBean;
import com.casic.fms.bean.report.OrginfoMonthly;
import com.casic.fms.bean.report.USBKeyMonthly;
import com.casic.fms.dao.FileLogDao;
import com.casic.fms.dao.FileLogHistoryDao;
import com.casic.fms.dao.FileSecurityDao;
import com.casic.fms.dao.FileTypeDao;
import com.casic.fms.dao.LogPeriodDao;
import com.casic.fms.dao.OrgInfoDao;
import com.casic.fms.dao.OrgInfoMonthlyDao;
import com.casic.fms.dao.OrgUSBKeyInfoDao;
import com.casic.fms.dao.PCClientDao;
import com.casic.fms.dao.SecurityLevelDao;
import com.casic.fms.dao.USBKeyInfoDao;
import com.casic.fms.dao.USBKeyMonthlyDao;
import com.casic.fms.dao.custom.FileLogDaoCustom;
import com.casic.fms.dao.custom.FileLogHistoryCustom;
import com.casic.fms.service.LogStatisticService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
@Transactional(readOnly = false)
public class LogStatisticServiceImp implements LogStatisticService {

	private static Logger logger = LoggerFactory.getLogger(LogStatisticServiceImp.class);

	private Template template;
	private Configuration freemarkerConfiguration;

	private PCClientDao	pCClientDao;
	private USBKeyInfoDao	usbKeyInfoDao;
	private FileLogDao  fileLogDao;
	private FileSecurityDao	fileSecurityDao;
	private FileLogHistoryDao	fileLogHistoryDao;
	private FileTypeDao		fileTypeDao;
	private SecurityLevelDao		securityLevelDao;
	private LogPeriodDao		logPeriodDao;	
	private OrgInfoDao		orgInfoDao;
	private OrgUSBKeyInfoDao		orgUSBKeyInfoDao;
	
	private USBKeyMonthlyDao  uSBKeyMonthlyDao;
	private OrgInfoMonthlyDao	orgInfoMonthlyDao;
	private FileLogDaoCustom		fileLogDaoCustom;
	
	@Autowired
	public void setFileLogDaoCustom(FileLogDaoCustom fileLogDaoCustom) {
		this.fileLogDaoCustom = fileLogDaoCustom;
	}

	@Autowired
	public void setOrgInfoMonthlyDao(OrgInfoMonthlyDao orgInfoMonthlyDao) {
		this.orgInfoMonthlyDao = orgInfoMonthlyDao;
	}


	@Autowired
	public void setuSBKeyMonthlyDao(USBKeyMonthlyDao uSBKeyMonthlyDao) {
		this.uSBKeyMonthlyDao = uSBKeyMonthlyDao;
	}

	@Autowired
	public void setOrgUSBKeyInfoDao(OrgUSBKeyInfoDao orgUSBKeyInfoDao) {
		this.orgUSBKeyInfoDao = orgUSBKeyInfoDao;
	}
	
	@Autowired
	public void setOrgInfoDao(OrgInfoDao orgInfoDao) {
		this.orgInfoDao = orgInfoDao;
	}
	
	@Autowired
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
		this.freemarkerConfiguration = freemarkerConfiguration;
	}
	

	@Autowired
	public void setLogPeriodDao(LogPeriodDao logPeriodDao) {
		this.logPeriodDao = logPeriodDao;
	}
	
	@Autowired
	public void setFileTypeDao(FileTypeDao fileTypeDao) {
		this.fileTypeDao = fileTypeDao;
	}
	@Autowired
	public void setSecurityLevelDao(SecurityLevelDao securityLevelDao) {
		this.securityLevelDao = securityLevelDao;
	}
	@Autowired
	public void setUsbKeyInfoDao(USBKeyInfoDao usbKeyInfoDao) {
		this.usbKeyInfoDao = usbKeyInfoDao;
	}
	@Autowired
	public void setpCClientDao(PCClientDao pCClientDao) {
		this.pCClientDao = pCClientDao;
	}
	@Autowired
	public void setFileLogHistoryDao(FileLogHistoryDao fileLogHistoryDao) {
		this.fileLogHistoryDao = fileLogHistoryDao;
	}

	@Autowired
	public void setFileLogDao(FileLogDao fileLogDao) {
		this.fileLogDao = fileLogDao;
	}
	@Autowired
	public void setFileSecurityDao(FileSecurityDao fileSecurityDao) {
		this.fileSecurityDao = fileSecurityDao;
	}

	public long getLogCount() {
		// TODO Auto-generated method stub
		return this.fileLogDao.count()+ this.fileLogHistoryDao.count();
	}

	public long getClientCount() {
		// TODO Auto-generated method stub
		return this.pCClientDao.count();
	}
	
	public long getSecurityCount(){
		return  this.fileLogDao.getSecurityTotalCount()+ this.fileLogHistoryDao.getSecurityTotalCount();
	}
	
	/**
	 * 根据MAC地址获取主机信息
	 * @param mac
	 * @return
	 */
	public PCClient	getPCClient(String mac){
		return this.pCClientDao.findByClientMac(mac);
	}
	public PCClient	updatePCClient(PCClient client){
		return this.pCClientDao.save(client);
	}
	
	public USBKeyInfo 	getUSBKey(String usbkey){
		return this.usbKeyInfoDao.findByUsbkey(usbkey);
	}
	

	
	/**
	 * 获取监控终端列表
	 */
	public Page<PCClient>  getClientList(Integer pageIndex, Integer pageSize){
		PageRequest page = new PageRequest(pageIndex, pageSize, FileLogHistory.getSortASC("clientMac"));
		return this.pCClientDao.findAll(page);
	}
	
	public List<USBKeyInfo>   getUSBKeyList(){
		return this.usbKeyInfoDao.findAll();
	}

	/**
	 * 根据主机统计每个类型的文档，每个密级的定密数量
	 * @param mac
	 * @return
	 */
	public Map<String,List<Long>>  summaryClientByMac(String mac){
		List<SecurityLevel>  sls = securityLevelDao.findAll(SecurityLevel.getSortASC("code"));
		List<FileType>	fts = fileTypeDao.findAll(FileType.getSortASC("code"));
		Map<String,List<Long>>  hmap = new LinkedHashMap<String,List<Long>>();
		for (SecurityLevel sl : sls) {
			List<Long>	summs = new ArrayList<Long>();
			for (FileType ft : fts) {
				Long summ = this.fileLogHistoryDao.getCountByClientMac(FileLog.OPERATION_SECURITY, mac, ft.getCode(), sl.getCode());
				summs.add(summ);
			}
			hmap.put(sl.getName(), summs);
			
		}
		return hmap;
	}
	
	
	/**
	 * 将日志文件从fileLog 转移到fileLogHistory
	 */
	public void transferHistory() {
		// TODO Auto-generated method stub
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(Calendar.getInstance().getTime());
//		List<FileLog>  alllogs = this.fileLogDao.findByTsBefore(today);
//		
//		List<FileLogHistory>	 hlist = new ArrayList<FileLogHistory>();
//		for (FileLog fileLog : alllogs) {
//			hlist.add(new FileLogHistory(fileLog));
//		}
		try{
//			this.fileLogHistoryDao.save(hlist);
//			this.fileLogDao.delete(alllogs);
			fileLogDaoCustom.transferDailyLog(today);
		}catch(Exception ex){
			logger.error("日志文件转移出错！", ex);
		}
	}
	
	/**
	 * 根据日志整理客户端的信息，包括客户端的Mac地址和USBkey的信息
	 */
	public void cleanupClientInfo(){
		List<FileLog> list = this.fileLogDao.findAll();
		for (FileLog filelog : list) {
			USBKeyInfo usbinfo = this.usbKeyInfoDao.findByUsbkey(filelog.getUsbkey());
			if(usbinfo == null){
				usbinfo = new USBKeyInfo();
				usbinfo.setUsbkey(filelog.getUsbkey());
				usbinfo.setUsername(filelog.getUsername());
				this.usbKeyInfoDao.save(usbinfo);
			}
			PCClient pcc = this.pCClientDao.findByClientMac(filelog.getClientMac());
			if(pcc == null){
				pcc = new PCClient();
				pcc.setClientMac(filelog.getClientMac());
				pcc.setClientName(filelog.getClientName());
				pcc.setIpAddress(filelog.getIpAddress());
				pcc.setUsbkey(filelog.getUsbkey());
				pcc.setUsername(filelog.getUsername());
				this.pCClientDao.save(pcc);
			}
		}

	}
	
	
	public void summaryDailyLog() {
		// TODO Auto-generated method stub
		
	}
	
	public List<String> getLastDays(int dayNums, int offset, String endDate) {
		List<String>   dls = new ArrayList<String>();
	    Calendar cal = Calendar.getInstance(); 
	    if(endDate != null)
	    		cal.setTime(Date.valueOf(endDate));
	    cal.add(Calendar.DAY_OF_YEAR, -(dayNums+offset));
	    SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
	    //DateFormat df = DateFormat.getDateInstance(); //默认语言（汉语）下的默认风格（MEDIUM风格，比如：2008-6-16 20:54:53）
	    for(int i=0;i<dayNums; i++){
	    		cal.add(Calendar.DAY_OF_YEAR,1);
	    		dls.add(df.format(cal.getTime()));
	    }
		return dls;
	}
	/**
	 * 根据操作类型，计算每天的数据总和
	 */
	public Object getDaysSummaryByType(int days, String opertype) {
		List<String>	   dates = this.getLastDays(days,1,null);
		List<List> 	summary = new ArrayList<List>();  
		if(LogStatisticService.SECURITY.equals(opertype)){
			for (String date : dates) {
				List<Object>  daycount = new ArrayList<Object>();
				daycount.add(date);
				daycount.add(fileSecurityDao.getCountByDate(date));
				summary.add(daycount);
			}
		}else if(LogStatisticService.MONITOR.equals(opertype)){
			for (String date : dates) {
				List<Object>  daycount = new ArrayList<Object>();
				daycount.add(date);
				daycount.add(fileLogHistoryDao.getCountByDate(date));
				summary.add(daycount);
			}
			
		}
		return summary;
	}

	public Page<FileLogHistory> getByClient(String mac,Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		PageRequest page = new PageRequest(pageIndex, pageSize, FileLogHistory.getSortASC("ts"));
		return this.fileLogHistoryDao.findAllByClientMac(mac,page);
	}
	public Page<FileLogHistory> getLogs(Integer pageIndex, Integer pageSize, FileLogQueryBean  queryBean) {
		if(queryBean !=null && queryBean.getOrginfoId() != null && !"".equals(queryBean.getOrginfoId())){
			OrgInfoBean orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
			List<String>  usbkeys = this.orgUSBKeyInfoDao.getUSBKeyByOrg(orginfo.getCode());
			queryBean.setUsbkey(null);
			queryBean.setUsbkeys(usbkeys);
		}
		PageRequest page = new PageRequest(pageIndex, pageSize, FileLogHistory.getSortDESC("ts"));
		return this.fileLogHistoryDao.findAll(FileLogHistoryCustom.customQuery(queryBean),page);
	}
	
	/**
	 * 获取部门的log日志
	 * @param pageIndex
	 * @param pageSize
	 * @param queryBean
	 * @return
	 */
	public Page<FileLogHistory>	searchOrgLogsByUsbkey(Integer pageIndex, Integer pageSize, FileLogQueryBean  queryBean){
		OrgUSBKeyInfo  oui =this.orgUSBKeyInfoDao.findOneByUsbkey(queryBean.getUsbkey());
		PageRequest page = new PageRequest(pageIndex, pageSize, FileLogHistory.getSortDESC("ts"));
		if(oui != null && oui.getRole().equals(OrgUSBKeyInfo.ROLE_LEADER)){
			List<String> usbkeys = this.orgUSBKeyInfoDao.getUSBKeyByOrg(oui.getOrginfoCode());
			queryBean.setUsbkey(null);
			queryBean.setUsbkeys(usbkeys);
		}
		return this.fileLogHistoryDao.findAll(FileLogHistoryCustom.customQuery(queryBean),page);
	}
	/**
	 * 整理系统的日志年度和月份列表
	 */
	public String summaryLogPeriod(){
		try{
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		String minDate = this.fileLogHistoryDao.getMinOperationTime();
		Calendar  calMin = Calendar.getInstance();
		calMin.setTime(sdf.parse(minDate));
		
		String maxDate = this.fileLogHistoryDao.getMaxOperationTime();
		Calendar  calMax = Calendar.getInstance();
		calMax.setTime(sdf.parse(maxDate));
		while(calMin.before(calMax)){
			int month = calMin.get(Calendar.MONTH)+1;
			String strMonth = String.valueOf(month);
			if(month<10){
				strMonth = "0"+strMonth;
			}
			String strYear = String.valueOf(calMin.get(Calendar.YEAR));
			LogPeriod lp = new LogPeriod();
			lp.setMonth(strMonth);
			lp.setYear(strYear);
			LogPeriod oldlp = this.logPeriodDao.findOneByYearAndMonth(lp.getYear(), lp.getMonth());
			if(oldlp == null){
				this.logPeriodDao.save(lp);
			}
			calMin.add(Calendar.MONTH, 1);
		}
		}catch(Exception ex){
			logger.error("整理系统年份和月份出错！", ex);
		}
		return "成功";
	}
	
	/**
	 * 根据User计算月报表
	 * @return
	 */
	public int summaryUserMonthlyReport(){
		String maxReportPeriod = this.uSBKeyMonthlyDao.getMaxYearMonth();
		List<LogPeriod>	 runPeriods = null;
		if(maxReportPeriod != null && maxReportPeriod.trim().length()>4){
			runPeriods = this.logPeriodDao.getAllPeriodFromYM(maxReportPeriod.substring(0,4), maxReportPeriod.substring(4));
		}else{
			runPeriods = this.logPeriodDao.findAll();
		}
		
		List<USBKeyMonthly>  listReport =new  ArrayList<USBKeyMonthly>();
		for (final LogPeriod logPeriod : runPeriods) {
			List<Object>  arrs = this.fileLogHistoryDao.getGroupCountByTime(logPeriod.getYear()+"-"+logPeriod.getMonth());
			for (Object objects : arrs) {
				Object[] cols = (Object[])objects;
				USBKeyMonthly ulm = new USBKeyMonthly();
				ulm.setYear(logPeriod.getYear());
				ulm.setMonth(logPeriod.getMonth());
				ulm.setUsbkey((String)cols[0]);
				ulm.setSecurityCode((String)cols[1]);
				ulm.setFileType((String)cols[2]);
				ulm.setLogNumber((Long)cols[3]);
				ulm.setSettingNumber((Long)cols[4]);
				if(ulm.getSecurityCode() != null && ulm.getFileType()!=null)
					listReport.add(ulm);
			}
			this.uSBKeyMonthlyDao.deleteByYearAndMonth(logPeriod.getYear(), logPeriod.getMonth());
		}
		this.uSBKeyMonthlyDao.save(listReport);
		return 0;
	}
	/**
	 * 计算年度报表
	 * @return
	 */
	public int summaryUserYearlyReport(){
		return 0;
	}
	/**
	 * 计算季度报表
	 * @param orgId
	 * @return
	 */
	public int summaryUserQuarterReport(){
		return 0;
	}
	

	public int summaryOrginfoMonthlyReport() {
		// TODO Auto-generated method stub
		List<OrgInfoBean>	orginfoes = this.orgInfoDao.findAll();
		List<OrginfoMonthly>		list =new ArrayList<OrginfoMonthly>();
		for (OrgInfoBean orgInfoBean : orginfoes) {
			List<String>  usbkeys = this.orgUSBKeyInfoDao.getUSBKeyByOrg(orgInfoBean.getCode());
			if(usbkeys.size()==0)	continue;
			String oldMaxYm = this.orgInfoMonthlyDao.getMaxYearMonthByOrg(orgInfoBean.getId());
			List<Object>  results = null;
			if(oldMaxYm == null){
				results = uSBKeyMonthlyDao.getOrgGroupCount(usbkeys);
			}else{
				results = uSBKeyMonthlyDao.getOrgGroupCountByYM(usbkeys, oldMaxYm);
			}
			for (Object object : results) {
					Object[] cols = (Object[])object;
					// ul.year, ul.month, ul.securityCode, ul.fileType,SUM(ul.settingNumber), SUM(ul.logNumber)
					OrginfoMonthly  om = new OrginfoMonthly();
					om.setOrginfoId(orgInfoBean.getId());
					om.setOrgCode(orgInfoBean.getCode());
					om.setYear((String)cols[0]);
					om.setMonth((String)cols[1]);
					om.setSecurityCode((String)cols[2]);
					om.setFileType((String)cols[3]);
					om.setSettingNumber((Long)cols[4]);
					om.setLogNumber((Long)cols[5]);
					list.add(om);
			}
		}
		this.orgInfoMonthlyDao.save(list);
		return 0;
	}
	
	public int summaryOrginfoYearlyReport() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int summaryOrginfoQuarterReport() {
		// TODO Auto-generated method stub
		return 0;
	}


	public Page<OrginfoMonthly> queryOrgMonthlyReport(String orginfoid, String year, String month, Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(pageIndex, pageSize, OrginfoMonthly.getSortASC("orgCode"));
		if(year == null  || month==null){
			String ym = this.logPeriodDao.getMaxYearMonth();
			year = ym.substring(0,4);
			month = ym.substring(4,6);
		}
		if(orginfoid == null || "".equals(orginfoid) )
			return this.orgInfoMonthlyDao.findByYearAndMonth(year, month, pageable);
		else{
			OrgInfoBean orginfo = this.orgInfoDao.findOne(orginfoid);
			if(orginfo==null)	 return this.orgInfoMonthlyDao.findByYearAndMonth(year, month, pageable);
			return this.orgInfoMonthlyDao.findByYearAndMonthAndOrgCodeLike(year, month, orginfo.getCode()+"%", pageable);
		}
	}
	public Page<OrginfoMonthly> queryOrgAnnualReport(String orginfoid, String year, Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(pageIndex, pageSize, OrginfoMonthly.getSortASC("orgCode"));
		if(year == null){
			String ym = this.logPeriodDao.getMaxYearMonth();
			year = ym.substring(0,4);
		}
		if(orginfoid == null || "".equals(orginfoid) )
			return this.orgInfoMonthlyDao.findByYear(year, pageable);
		else{
			OrgInfoBean orginfo = this.orgInfoDao.findOne(orginfoid);
			if(orginfo==null)	 return this.orgInfoMonthlyDao.findByYear(year, pageable);
			return this.orgInfoMonthlyDao.findByYearAndOrgCodeLike(year, orginfo.getCode()+"%", pageable);
		}
	}



	public LogPeriod findMaxPeriod() {
		// TODO Auto-generated method stub
		String ym =  this.logPeriodDao.getMaxYearMonth();
		if(ym == null){
			summaryLogPeriod();
		}
		ym =  this.logPeriodDao.getMaxYearMonth();
		LogPeriod lp = new LogPeriod();
		lp.setYear(ym.substring(0,4));
		lp.setMonth(ym.substring(4,6));
		return lp;
	}
	
	public  List<String>		getReportYears(){
		return this.logPeriodDao.getYearList();
	}
	
	public  List<String>		getReportMonths(String year){
		return this.logPeriodDao.getMonthsByYear(year);
	}
	
	/**
	 * 根据密级信息统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getMonthlySecruityGroupSummary(FileLogQueryBean queryBean){
		Long totalSecurityCount = 0L;
		List<Object>		securityCount = null;
		if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
			OrgInfoBean orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndMonthAndOrgCodeLike(queryBean.getYear(), queryBean.getMonth(), orginfo.getCode()+"%");
			if(totalSecurityCount>0L)
				securityCount = this.orgInfoMonthlyDao.getMonthlyOrgGroupSummaryBySecurityLevel(queryBean.getYear(), queryBean.getMonth(),orginfo.getCode());
		}else{
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndMonth(queryBean.getYear(), queryBean.getMonth());
			if(totalSecurityCount>0L)
				securityCount = this.orgInfoMonthlyDao.getMonthlyGroupSummaryBySecurityLevel(queryBean.getYear(), queryBean.getMonth());
		}
		securityCount = replaceSecurityCodeToName(securityCount);
		return resetPercent(securityCount,totalSecurityCount,false);
	}
	/**
	 * 根据文件类型统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getMonthlyFileTypeGroupSummary(FileLogQueryBean queryBean){
		Long totalSecurityCount = 0L;
		List<Object>		filetypeCount = null;
		if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
			OrgInfoBean orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndMonthAndOrgCodeLike(queryBean.getYear(), queryBean.getMonth(), orginfo.getCode()+"%");
			if(totalSecurityCount !=null && totalSecurityCount>0L)
				filetypeCount = this.orgInfoMonthlyDao.getMonthlyOrgGroupSummaryByFileType(queryBean.getYear(), queryBean.getMonth(),orginfo.getCode());
		}else{
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndMonth(queryBean.getYear(), queryBean.getMonth());
			if(totalSecurityCount != null && totalSecurityCount>0L)
				filetypeCount = this.orgInfoMonthlyDao.getMonthlyGroupSummaryByFileType(queryBean.getYear(), queryBean.getMonth());
		}
		filetypeCount = replaceFileTypeCodeToName(filetypeCount);
		return resetPercent(filetypeCount,totalSecurityCount,false);
	}

	
	/**
	 * 根据密级信息统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getAnnualSecruityGroupSummary(FileLogQueryBean queryBean){
		Long totalSecurityCount = 0L;
		List<Object>		securityCount = null;
		if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
			OrgInfoBean orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndOrgCodeLike(queryBean.getYear(),  orginfo.getCode()+"%");
			if(totalSecurityCount!=null && totalSecurityCount>0L)
				securityCount = this.orgInfoMonthlyDao.getAnnualOrgGroupSummaryBySecurityLevel(queryBean.getYear(), orginfo.getCode());
		}else{
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYear(queryBean.getYear());
			if(totalSecurityCount != null && totalSecurityCount>0L)
				securityCount = this.orgInfoMonthlyDao.getAnnualGroupSummaryBySecurityLevel(queryBean.getYear());
		}
		securityCount = replaceSecurityCodeToName(securityCount);
		return resetPercent(securityCount,totalSecurityCount,false);
	}
	/**
	 * 根据文件类型统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getAnnualFileTypeGroupSummary(FileLogQueryBean queryBean){
		Long totalSecurityCount = 0L;
		List<Object>		filetypeCount = null;
		if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
			OrgInfoBean orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndOrgCodeLike(queryBean.getYear(), orginfo.getCode()+"%");
			if(totalSecurityCount!=null && totalSecurityCount>0L)
				filetypeCount = this.orgInfoMonthlyDao.getAnnualOrgGroupSummaryByFileType(queryBean.getYear(),orginfo.getCode());
		}else{
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYear(queryBean.getYear());
			if(totalSecurityCount != null && totalSecurityCount>0L)
				filetypeCount = this.orgInfoMonthlyDao.getAnnualGroupSummaryByFileType(queryBean.getYear());
		}
		filetypeCount = replaceFileTypeCodeToName(filetypeCount);
		return resetPercent(filetypeCount,totalSecurityCount,false);
	}
	
	/**
	 * 根据密级信息统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getQuarterSecruityGroupSummary(FileLogQueryBean queryBean){
		Long totalSecurityCount = 0L;
		List<Object>		securityCount = null;
		List<String>  months = QuarterBean.getMonths(queryBean.getMonth());
		if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
			OrgInfoBean orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndQuarterAndOrgCodeLike(queryBean.getYear(), months, orginfo.getCode()+"%");
			if(totalSecurityCount !=null && totalSecurityCount>0L)
				securityCount = this.orgInfoMonthlyDao.getQuarterOrgGroupSummaryBySecurityLevel(queryBean.getYear(),  months, orginfo.getCode());
		}else{
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndQuarter(queryBean.getYear(), months );
			if(totalSecurityCount !=null && totalSecurityCount>0L)
				securityCount = this.orgInfoMonthlyDao.getQuarterGroupSummaryBySecurityLevel(queryBean.getYear(),  months);
		}
		securityCount = replaceSecurityCodeToName(securityCount);
		return resetPercent(securityCount,totalSecurityCount,false);
	}
	/**
	 * 根据文件类型统计每个月的数量
	 * @param queryBean
	 * @return
	 */
	public List<Object>	  getQuarterFileTypeGroupSummary(FileLogQueryBean queryBean){
		Long totalSecurityCount = 0L;
		List<Object>		filetypeCount = null;
		List<String>  months = QuarterBean.getMonths(queryBean.getMonth());
		if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
			OrgInfoBean orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndQuarterAndOrgCodeLike(queryBean.getYear(),  months, orginfo.getCode()+"%");
			if(totalSecurityCount != null && totalSecurityCount>0L)
				filetypeCount = this.orgInfoMonthlyDao.getQuarterOrgGroupSummaryByFileType(queryBean.getYear(), months,orginfo.getCode());
		}else{
			totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndQuarter(queryBean.getYear(), months);
			if(totalSecurityCount != null && totalSecurityCount>0L)
				filetypeCount = this.orgInfoMonthlyDao.getQuarterGroupSummaryByFileType(queryBean.getYear(), months);
		}
		filetypeCount = replaceFileTypeCodeToName(filetypeCount);
		return resetPercent(filetypeCount,totalSecurityCount,false);
	}
	
	
	
	/**
	 * 创建机构月度统计报告
	 */
	public String createOrgAnnualReportTxt(FileLogQueryBean queryBean, String format) {
		try{
			if (format == null) {
				template = this.freemarkerConfiguration.getTemplate("annualorgsummary.htm", "utf-8");
			} else {
				template = this.freemarkerConfiguration.getTemplate("annualorgsummary.txt", "utf-8");
			}
			Map<String, Object> context = new HashMap<String, Object>();
			Long totalSecurityCount = 0L;
			List<Object>		securityCount = null;
			List<Object>		filetypeCount = null;
			OrgInfoBean orginfo = null;
			if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
				orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
				totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndOrgCodeLike(queryBean.getYear(), orginfo.getCode()+"%");
				securityCount = this.orgInfoMonthlyDao.getAnnualOrgGroupSummaryBySecurityLevel(queryBean.getYear(),orginfo.getCode());
				filetypeCount = this.orgInfoMonthlyDao.getAnnualOrgGroupSummaryByFileType(queryBean.getYear(),orginfo.getCode());
			}else{
				orginfo = new OrgInfoBean();
				orginfo.setName("整个公司");
				totalSecurityCount = this.orgInfoMonthlyDao.sumByYear(queryBean.getYear());
				securityCount = this.orgInfoMonthlyDao.getAnnualGroupSummaryBySecurityLevel(queryBean.getYear());
				filetypeCount = this.orgInfoMonthlyDao.getAnnualGroupSummaryByFileType(queryBean.getYear());
			}
			
			securityCount = replaceSecurityCodeToName(securityCount);
			filetypeCount = replaceFileTypeCodeToName(filetypeCount);
			context.put("queryModel",queryBean);
			context.put("orginfo",orginfo);
			context.put("totalSecurityCount", totalSecurityCount);
			context.put("securityList",securityCount);
			context.put("filetypeList",filetypeCount);
			context.put("securityPercentList",resetPercent(securityCount,totalSecurityCount,true));
			context.put("filetypePercentList",resetPercent(filetypeCount,totalSecurityCount,true));
			return FreeMarkerTemplateUtils.processTemplateIntoString(template,context);

		}catch(Exception ex){
			logger.error("解析模板错误！", ex);
		}

		return null;
	}
		
	/**
	 * 创建机构月度统计报告
	 */
	public String createOrgMonthlyReportTxt(FileLogQueryBean queryBean, String format) {
		try{
			if (format == null) {
				template = this.freemarkerConfiguration.getTemplate("monthlyorgsummary.htm", "utf-8");
			} else {
				template = this.freemarkerConfiguration.getTemplate("monthlyorgsummary.txt", "utf-8");
			}
			Map<String, Object> context = new HashMap<String, Object>();
			Long totalSecurityCount = 0L;
			List<Object>		securityCount = null;
			List<Object>		filetypeCount = null;
			OrgInfoBean orginfo = null;
			if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
				orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
				totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndMonthAndOrgCodeLike(queryBean.getYear(), queryBean.getMonth(), orginfo.getCode()+"%");
				securityCount = this.orgInfoMonthlyDao.getMonthlyOrgGroupSummaryBySecurityLevel(queryBean.getYear(), queryBean.getMonth(),orginfo.getCode());
				filetypeCount = this.orgInfoMonthlyDao.getMonthlyOrgGroupSummaryByFileType(queryBean.getYear(), queryBean.getMonth(),orginfo.getCode());
			}else{
				orginfo = new OrgInfoBean();
				orginfo.setName("整个公司");
				totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndMonth(queryBean.getYear(), queryBean.getMonth());
				securityCount = this.orgInfoMonthlyDao.getMonthlyGroupSummaryBySecurityLevel(queryBean.getYear(), queryBean.getMonth());
				filetypeCount = this.orgInfoMonthlyDao.getMonthlyGroupSummaryByFileType(queryBean.getYear(), queryBean.getMonth());
			}
			
			securityCount = replaceSecurityCodeToName(securityCount);
			filetypeCount = replaceFileTypeCodeToName(filetypeCount);
			context.put("queryModel",queryBean);
			context.put("orginfo",orginfo);
			context.put("totalSecurityCount", totalSecurityCount);
			context.put("securityList",securityCount);
			context.put("filetypeList",filetypeCount);
			context.put("securityPercentList",resetPercent(securityCount,totalSecurityCount,true));
			context.put("filetypePercentList",resetPercent(filetypeCount,totalSecurityCount,true));
			return FreeMarkerTemplateUtils.processTemplateIntoString(template,context);

		}catch(Exception ex){
			logger.error("解析模板错误！", ex);
		}

		return null;
	}
	/**
	 * 创建机构月度统计报告
	 */
	public String createOrgQuarterReportTxt(FileLogQueryBean queryBean, String format) {
		try{
			if (format == null) {
				template = this.freemarkerConfiguration.getTemplate("quarterorgsummary.htm", "utf-8");
			} else {
				template = this.freemarkerConfiguration.getTemplate("quarterorgsummary.txt", "utf-8");
			}
			Map<String, Object> context = new HashMap<String, Object>();
			Long totalSecurityCount = 0L;
			List<Object>		securityCount = null;
			List<Object>		filetypeCount = null;
			OrgInfoBean orginfo = null;
			List<String>		months = QuarterBean.getMonths(queryBean.getMonth());
			if(queryBean.getOrginfoId() !=null && !"".equals(queryBean.getOrginfoId())){
				orginfo = this.orgInfoDao.findOne(queryBean.getOrginfoId());
				totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndQuarterAndOrgCodeLike(queryBean.getYear(), months, orginfo.getCode()+"%");
				securityCount = this.orgInfoMonthlyDao.getQuarterOrgGroupSummaryBySecurityLevel(queryBean.getYear(), months,orginfo.getCode());
				filetypeCount = this.orgInfoMonthlyDao.getQuarterOrgGroupSummaryByFileType(queryBean.getYear(), months,orginfo.getCode());
			}else{
				orginfo = new OrgInfoBean();
				orginfo.setName("整个公司");
				totalSecurityCount = this.orgInfoMonthlyDao.sumByYearAndQuarter(queryBean.getYear(), months);
				securityCount = this.orgInfoMonthlyDao.getQuarterGroupSummaryBySecurityLevel(queryBean.getYear(), months);
				filetypeCount = this.orgInfoMonthlyDao.getQuarterGroupSummaryByFileType(queryBean.getYear(), months);
			}
			
			securityCount = replaceSecurityCodeToName(securityCount);
			filetypeCount = replaceFileTypeCodeToName(filetypeCount);
			context.put("queryModel",queryBean);
			context.put("orginfo",orginfo);
			context.put("totalSecurityCount", totalSecurityCount);
			context.put("securityList",securityCount);
			context.put("filetypeList",filetypeCount);
			context.put("securityPercentList",resetPercent(securityCount,totalSecurityCount,true));
			context.put("filetypePercentList",resetPercent(filetypeCount,totalSecurityCount,true));
			return FreeMarkerTemplateUtils.processTemplateIntoString(template,context);

		}catch(Exception ex){
			logger.error("解析模板错误！", ex);
		}

		return null;
	}
	private  List<Object>	replaceSecurityCodeToName(List<Object>  list){
		if(list == null || list.size()==0)	return list;
		for (int i=0; i<list.size(); i++) {
			Object[] objs = (Object[])list.get(i);
			SecurityLevel sl = this.securityLevelDao.findOneByCode((String)objs[0]);
			objs[0] = sl==null?"":sl.getName();
		}
		return list;
	}
	
	private List<Object>	resetPercent(List<Object> list, Long totalSecurityCount, boolean isPercentFormat){
		List<Object> map = new ArrayList<Object>();
		if(totalSecurityCount == null)	return map;
		DecimalFormat dfp = new DecimalFormat("##.#%");
		DecimalFormat df = new DecimalFormat("#.###");
		Double dtotal = Double.valueOf(totalSecurityCount.toString());
		for (int i=0; i<list.size(); i++) {
			Object[] objs = (Object[])list.get(i);
			Double db = Double.valueOf((Long)objs[1]);
			List<Object>  one = new ArrayList<Object>();
			one.add((String)objs[0]);
			if(isPercentFormat){
				one.add(dfp.format(db/dtotal));
			}else{
				one.add(Double.valueOf(df.format(db/dtotal))*100);
			}
			if(db>0)
				map.add(one);
		}
		return map;
	}
	
	private  List<Object>	replaceFileTypeCodeToName(List<Object>  list){
		if(list == null || list.size()==0)	return list;
		for (int i=0; i<list.size(); i++) {
			Object[] objs = (Object[])list.get(i);
			objs[0] = this.fileTypeDao.findOneBySuffixName((String)objs[0]).getName();
		}
		return list;
	}
	

	public  static void main(String[] strs){
		try{
//		Date now = new Date(); 
//		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.parse("2012-09-09 12:12:00"));
//	    Calendar cal = Calendar.getInstance(); 
//	    cal.setTime(sdf.parse("2012-09-09 12:12:00"));
//	    //cal.add(field, amount);
//	    DateFormat d1 = DateFormat.getDateInstance(); //默认语言（汉语）下的默认风格（MEDIUM风格，比如：2008-6-16 20:54:53）
//	    System.out.println(cal.get(Calendar.YEAR));
//	    System.out.println(cal.get(Calendar.MONTH));
//	    System.out.println(cal.get(Calendar.DAY_OF_MONTH));
//	    System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
//	    Calendar  old = Calendar.getInstance(); 
//	    cal.add(Calendar.MONDAY, 1);
//	    System.out.println(cal.get(Calendar.MONTH));
////	    List<String>   liststrs = new LogStatisticServiceImp().getLastDays(30,1,null);
////	    System.out.println(liststrs);
//	    if(cal.after(old)){
//		    System.out.println("过了");
//	    }
			String ym = "201509";
			System.out.println(ym.substring(0,4));
			System.out.println(ym.substring(4,6));
			
			DecimalFormat df = new DecimalFormat("#0.0%");
			double  dl = 8.0/999.0;
			System.out.println(df.format(dl));
			
			NumberFormat formatter = new DecimalFormat("#.###");
			Double x=new Double(37.0/59.0);
			String xx = formatter.format(x);
			System.out.println(xx);
		}catch(Exception ex){
			ex.printStackTrace();
		}
 	}


	public List<QuarterBean> getReportQuarter() {
		return QuarterBean.getAllQuarter();
	}


}
