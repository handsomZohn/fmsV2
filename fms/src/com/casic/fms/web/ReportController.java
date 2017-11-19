package com.casic.fms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.casic.fms.bean.LogPeriod;
import com.casic.fms.bean.PCClient;
import com.casic.fms.bean.query.FileLogQueryBean;
import com.casic.fms.bean.report.OrginfoMonthly;
import com.casic.fms.service.AuthorizeService;
import com.casic.fms.service.FileLogService;
import com.casic.fms.service.LogStatisticService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class ReportController  extends BaseWebController{
	
	private FileLogService	fileLogService;
	private LogStatisticService	logStatisticService;
	private AuthorizeService		authorizeService;

	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}
	
	@Autowired
	public void setLogStatisticService(LogStatisticService logStatisticService) {
		this.logStatisticService = logStatisticService;
	}
	
	@Autowired
	public void setFileLogService(FileLogService fileLogService) {
		this.fileLogService = fileLogService;
	}

	
	/**
	 * 显示一台计算机的日志
	 * @param model
	 * @param clientMac
	 * @return
	 */
	@RequestMapping(value = {"statistic/client/{clientMac}"},method=RequestMethod.GET)
	public String statisticClient(Model model,@PathVariable("clientMac")String clientMac) {
		PCClient pc = this.logStatisticService.getPCClient(clientMac);
		model.addAttribute("clientinfo", pc);
		return "statistic/client";
	}
	/**
	 * 显示usbkey的日志
	 * @param model
	 * @param clientMac
	 * @return
	 */
	@RequestMapping(value = {"statistic/user/{usbkey}"},method=RequestMethod.GET)
	public String statisticUser(Model model,@PathVariable("usbkey")String usbkey) {
		return "statistic/user";
	}
	
	
	

	@RequiresPermissions("report:month")
	@RequestMapping(value = {"report/monthly/{pageIndex}"})
	public String reportMonthlyOrginfo(FileLogQueryBean queryBean,Model model, @PathVariable("pageIndex")Integer pageIndex, 
			@RequestParam(value="pageSize",required=false)Integer pageSize,HttpSession session,HttpServletRequest request) {
		if(pageSize == null)		pageSize = 20;
		if("POST".equalsIgnoreCase(request.getMethod()) ){
			session.setAttribute("monthlysummaryquerybean", queryBean);
		}else{
			queryBean = (FileLogQueryBean)session.getAttribute("monthlysummaryquerybean");
		}
		if(queryBean == null){
			LogPeriod lp = logStatisticService.findMaxPeriod();
			queryBean = new FileLogQueryBean();
			queryBean.setYear(lp.getYear());
			queryBean.setMonth(lp.getMonth());
			session.setAttribute("monthlysummaryquerybean", queryBean);
		}

		Page<OrginfoMonthly>  pageModels = this.logStatisticService.queryOrgMonthlyReport(queryBean.getOrginfoId(), queryBean.getYear(),queryBean.getMonth(), pageIndex, pageSize);
		addPageinfo(model, pageModels);
		model.addAttribute("models", pageModels.getContent());
		model.addAttribute("querymodel", queryBean);
		model.addAttribute("years", this.logStatisticService.getReportYears());
		model.addAttribute("months", this.logStatisticService.getReportMonths(queryBean.getYear()));
		model.addAttribute("orginfoes", this.authorizeService.getAllOrginfo(true));
		return "report/monthly";
	}
	
	@RequiresPermissions("report:quarter")
	@RequestMapping(value = {"report/quarter"})
	public String reportQuarterOrginfo(FileLogQueryBean queryBean,Model model,HttpSession session,HttpServletRequest request) {
		if("POST".equalsIgnoreCase(request.getMethod()) ){
			session.setAttribute("quartersummaryquerybean", queryBean);
		}else{
			queryBean = (FileLogQueryBean)session.getAttribute("quartersummaryquerybean");
		}
		if(queryBean == null){
			LogPeriod lp = logStatisticService.findMaxPeriod();
			queryBean = new FileLogQueryBean();
			queryBean.setYear(lp.getYear());
			queryBean.setMonth("1");
			session.setAttribute("quartersummaryquerybean", queryBean);
		}

		model.addAttribute("querymodel", queryBean);
		model.addAttribute("years", this.logStatisticService.getReportYears());
		model.addAttribute("quarters", this.logStatisticService.getReportQuarter());
		model.addAttribute("orginfoes", this.authorizeService.getAllOrginfo(true));
		return "report/quarter";
	}
	
	@RequiresPermissions("report:annual")
	@RequestMapping(value = {"report/annual"})
	public String reportAnnualOrginfo(FileLogQueryBean queryBean,Model model, HttpSession session,HttpServletRequest request) {
		if("POST".equalsIgnoreCase(request.getMethod()) ){
			session.setAttribute("annualsummaryquerybean", queryBean);
		}else{
			queryBean = (FileLogQueryBean)session.getAttribute("annualsummaryquerybean");
		}
		if(queryBean == null){
			LogPeriod lp = logStatisticService.findMaxPeriod();
			queryBean = new FileLogQueryBean();
			queryBean.setYear(lp.getYear());
			session.setAttribute("annualsummaryquerybean", queryBean);
		}
		model.addAttribute("querymodel", queryBean);
		model.addAttribute("years", this.logStatisticService.getReportYears());
		model.addAttribute("orginfoes", this.authorizeService.getAllOrginfo(true));
		return "report/annual";
	}
	@RequestMapping(value = {"widget/report/orgmonthly/{year}/{month}"},method=RequestMethod.GET)
	public String generateOrgMonthlyReport(@PathVariable("year")String year, 
			@PathVariable("month")String month, 
			@RequestParam(value="orgid",required=false)String orgid, 
			Model model) {
		FileLogQueryBean queryBean= new FileLogQueryBean();
		queryBean.setYear(year);
		queryBean.setMonth(month);
		queryBean.setOrginfoId(orgid);
		String str = this.logStatisticService.createOrgMonthlyReportTxt(queryBean, null);
		model.addAttribute("result", str);
		return "widget/report/result";
	}
	@RequestMapping(value = {"widget/report/monthly/security/pie/{year}/{month}"},method=RequestMethod.GET)
	public String reportWidgetSecurityPie(@PathVariable("year")String year, 
			@PathVariable("month")String month, 
			@RequestParam(value="orgid",required=false)String orgid,
			Model model) {
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("orgid", orgid);
		return "widget/report/chart/securitypie";
	}
	@RequestMapping(value = {"widget/report/monthly/filetype/pie/{year}/{month}"},method=RequestMethod.GET)
	public String reportWidgetFileTypePie(@PathVariable("year")String year, 
			@PathVariable("month")String month, 
			@RequestParam(value="orgid",required=false)String orgid,
			Model model) {
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("orgid", orgid);
		return "widget/report/chart/filetypepie";
	}
	@RequestMapping(value = {"widget/report/annual/{year}"},method=RequestMethod.GET)
	public String generateOrgMonthlyReport(@PathVariable("year")String year, 
			@RequestParam(value="orgid",required=false)String orgid, 
			Model model) {
		FileLogQueryBean queryBean= new FileLogQueryBean();
		queryBean.setYear(year);
		queryBean.setOrginfoId(orgid);
		String str = this.logStatisticService.createOrgAnnualReportTxt(queryBean, null);
		model.addAttribute("result", str);
		return "widget/report/result";
	}
	@RequestMapping(value = {"widget/report/annual/security/pie/{year}"},method=RequestMethod.GET)
	public String reportAnnualWidgetSecurityPie(@PathVariable("year")String year, 
			@RequestParam(value="orgid",required=false)String orgid,
			Model model) {
		model.addAttribute("year", year);
		model.addAttribute("orgid", orgid);
		return "widget/report/chart/securitypie";
	}
	@RequestMapping(value = {"widget/report/annual/filetype/pie/{year}"},method=RequestMethod.GET)
	public String reportAnnualWidgetFileTypePie(@PathVariable("year")String year, 
			@RequestParam(value="orgid",required=false)String orgid,
			Model model) {
		model.addAttribute("year", year);
		model.addAttribute("orgid", orgid);
		return "widget/report/chart/filetypepie";
	}
	@RequestMapping(value = {"widget/report/quarter/{year}/{quarter}"},method=RequestMethod.GET)
	public String generateOrgQuarterReport(
			@PathVariable("year")String year, 
			@PathVariable("quarter")String quarter, 
			@RequestParam(value="orgid",required=false)String orgid, 
			Model model) {
		FileLogQueryBean queryBean= new FileLogQueryBean();
		queryBean.setYear(year);
		queryBean.setOrginfoId(orgid);
		queryBean.setMonth(quarter);
		String str = this.logStatisticService.createOrgQuarterReportTxt(queryBean, null);
		model.addAttribute("result", str);
		return "widget/report/result";
	}
	@RequestMapping(value = {"widget/report/quarter/security/pie/{year}/{quarter}"},method=RequestMethod.GET)
	public String reportQuarterWidgetSecurityPie(
			@PathVariable("year")String year, 
			@PathVariable("quarter")String quarter, 
			@RequestParam(value="orgid",required=false)String orgid,
			Model model) {
		model.addAttribute("year", year);
		model.addAttribute("quarter", quarter);
		model.addAttribute("orgid", orgid);
		return "widget/report/chart/securitypie";
	}
	@RequestMapping(value = {"widget/report/quarter/filetype/pie/{year}/{quarter}"},method=RequestMethod.GET)
	public String reportQuarterWidgetFileTypePie(
			@PathVariable("year")String year, 
			@PathVariable("quarter")String quarter, 
			@RequestParam(value="orgid",required=false)String orgid,
			Model model) {
		model.addAttribute("year", year);
		model.addAttribute("quarter", quarter);
		model.addAttribute("orgid", orgid);
		return "widget/report/chart/filetypepie";
	}	
}
