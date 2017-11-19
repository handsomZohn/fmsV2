package com.casic.fms.web.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.query.FileLogQueryBean;
import com.casic.fms.service.LogStatisticService;
import com.casic.fms.service.trigger.LogSingletonService;
import com.casic.fms.service.trigger.USBKeyTokenService;

/**
 * Urls:
 * 
 * @author crazylion
 */
@Controller
@RequestMapping(value = "/json")
public class JsonLogReportController extends BaseJsonController{

	private static Logger logger = LoggerFactory.getLogger(JsonLogReportController.class);


	private LogStatisticService 	logStatisticService;

	@Autowired
	public void setLogStatisticService(LogStatisticService logStatisticService) {
		this.logStatisticService = logStatisticService;
	}
	
	
	
	@RequestMapping(value = "randkey/{usbkey}",method=RequestMethod.GET)
	@ResponseBody
	public ReInfo getMyLogsKey(@PathVariable("usbkey")String usbkey) {
		return USBKeyTokenService.getInstance().getToken(usbkey);
	}

	@RequestMapping(value = "mylogs/{token}",method=RequestMethod.GET)
	@ResponseBody
	public String getMyLogs(@PathVariable("token")String token,
			HttpServletRequest request,
			HttpServletResponse response) {
		try{
			response.sendRedirect(request.getContextPath()+"/mylogs/"+token);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return null;
	}

	/**
	 * 获取最近30天的日期
	 * @return
	 */
	@RequestMapping(value = "lastdates/{days}",method=RequestMethod.GET)
	@ResponseBody
	public List<String>	getLastDays(@PathVariable("days")Integer days){
		return logStatisticService.getLastDays(days,0,null);
	}

	/**
	 * 根据操作类型获取最近X天内的统计数据
	 * opertype: SECURITY（定密总数），MONITOR(监控总数)
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/lastdays/{days}/{opertype}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getDaysSummaryByType(@PathVariable("days")Integer days,@PathVariable("opertype")String opertype){
		return logStatisticService.getDaysSummaryByType(days, opertype);
	}
	/**
	 * 根据文件类型和mac地址，获取该主机的每个密级的数量
	 * opertype: SECURITY（定密总数），MONITOR(监控总数)
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/securitytotal/{clientMac}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getClientSecuritySummaryByFileType(@PathVariable("clientMac")String clientMac){
		return logStatisticService.summaryClientByMac(clientMac);
	}

	/**
	 * 根据文件类型和mac地址，获取该主机的每个密级的数量
	 * opertype: SECURITY（定密总数），MONITOR(监控总数)
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/securitytotal/",method=RequestMethod.GET)
	@ResponseBody
	public Object  getSecuritySummaryBYYM(@PathVariable("clientMac")String clientMac){
		return logStatisticService.summaryClientByMac(clientMac);
	}
	/**
	 * 根据年月及组织机构ID统计密级分布数据
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/org/monthly/security/{year}/{month}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getSecurityGroupBYYM(@PathVariable("year")String year,
			@PathVariable("month")String month,
			@RequestParam("orgid")String orginfoId
			){
		FileLogQueryBean querybean =new  FileLogQueryBean();
		querybean.setYear(year);
		querybean.setMonth(month);
		querybean.setOrginfoId(orginfoId);
		return logStatisticService.getMonthlySecruityGroupSummary(querybean);
	}
	/**
	 * 根据年月及组织机构ID统计密级分布数据
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/org/monthly/filetype/{year}/{month}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getFileTypeGroupBYYM(@PathVariable("year")String year,
			@PathVariable("month")String month,
			@RequestParam("orgid")String orginfoId
			){
		FileLogQueryBean querybean =new  FileLogQueryBean();
		querybean.setYear(year);
		querybean.setMonth(month);
		querybean.setOrginfoId(orginfoId);
		return logStatisticService.getMonthlyFileTypeGroupSummary(querybean);
	}
	/**
	 * 根据年月及组织机构ID统计密级分布数据
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/org/annual/security/{year}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getSecurityGroupBYYear(@PathVariable("year")String year,
			@RequestParam("orgid")String orginfoId
			){
		FileLogQueryBean querybean =new  FileLogQueryBean();
		querybean.setYear(year);
		querybean.setOrginfoId(orginfoId);
		return logStatisticService.getAnnualSecruityGroupSummary(querybean);
	}
	/**
	 * 根据年月及组织机构ID统计密级分布数据
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/org/annual/filetype/{year}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getFileTypeGroupBYYM(@PathVariable("year")String year,
			@RequestParam("orgid")String orginfoId
			){
		FileLogQueryBean querybean =new  FileLogQueryBean();
		querybean.setYear(year);
		querybean.setOrginfoId(orginfoId);
		return logStatisticService.getAnnualFileTypeGroupSummary(querybean);
	}
	/**
	 * 获取文件的日志信息
	 * @param url
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "logs/{type}",method=RequestMethod.GET)
	@ResponseBody
	public void showHelp(HttpServletRequest request,HttpServletResponse response) {
		try{
			response.sendRedirect(request.getContextPath()+"/static/help/help.doc");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 根据年月及组织机构ID统计密级分布数据
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/org/quarter/security/{year}/{month}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getSecurityGroupByYearAndQuarter(@PathVariable("year")String year,
			@PathVariable("month")String month,
			@RequestParam("orgid")String orginfoId
			){
		FileLogQueryBean querybean =new  FileLogQueryBean();
		querybean.setYear(year);
		querybean.setMonth(month);
		querybean.setOrginfoId(orginfoId);
		return logStatisticService.getQuarterSecruityGroupSummary(querybean);
	}
	/**
	 * 根据年月及组织机构ID统计密级分布数据
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "summary/org/quarter/filetype/{year}/{month}",method=RequestMethod.GET)
	@ResponseBody
	public Object  getFileTypeGroupByYearAndQuarter(@PathVariable("year")String year,
			@PathVariable("month")String month,
			@RequestParam("orgid")String orginfoId
			){
		FileLogQueryBean querybean =new  FileLogQueryBean();
		querybean.setYear(year);
		querybean.setMonth(month);
		querybean.setOrginfoId(orginfoId);
		return logStatisticService.getQuarterFileTypeGroupSummary(querybean);
	}	
	
	/**
	 * 根据文件类型和mac地址，获取该主机的每个密级的数量
	 * opertype: SECURITY（定密总数），MONITOR(监控总数)
	 * @param days
	 * @return
	 */
	@RequestMapping(value = "install/{typecode}",method=RequestMethod.GET)
	@ResponseBody
	public Object  testSummaryByFileType(@PathVariable("typecode")String typecode){
		if("0".equals(typecode))
			LogSingletonService.getInstance().startDailyLogTransfer();
		else  if("1".equals(typecode))
			LogSingletonService.getInstance().startMonthlyJob();
		return "选择的操作不存在";
	}


	



}
