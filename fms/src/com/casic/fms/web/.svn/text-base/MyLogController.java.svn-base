package com.casic.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.FileLogHistory;
import com.casic.fms.bean.query.FileLogQueryBean;
import com.casic.fms.service.AuthorizeService;
import com.casic.fms.service.BaseDataService;
import com.casic.fms.service.FileLogService;
import com.casic.fms.service.LogStatisticService;
import com.casic.fms.service.trigger.USBKeyTokenService;

/**
 * Urls:
 * 我的台账，不需要用户权限
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "")
public class MyLogController  extends BaseWebController{

	private LogStatisticService	logStatisticService;
	private BaseDataService		baseDataService;

	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	@Autowired
	public void setLogStatisticService(LogStatisticService logStatisticService) {
		this.logStatisticService = logStatisticService;
	}
	

	@RequestMapping(value = {"mylogs/{token}"},method=RequestMethod.GET)
	public String listToday(
			@PathVariable("token")String token,
			@RequestParam(value="pageIndex", required=false) Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize,Model model) {
		if(pageSize == null)		pageSize= 10;
		if(pageIndex == null)	pageIndex= 0;
		if(!USBKeyTokenService.getInstance().isValid(token)){
			//token超时
			model.addAttribute("message", "Token已经失效，请重新从客户端打开页面！");
			return "error";
		}
		String usbkey = USBKeyTokenService.getInstance().getUsbkeyByToken(token);
		FileLogQueryBean queryBean = new FileLogQueryBean();
		queryBean.setUsbkey(usbkey);
		Page<FileLogHistory>  pageModels = this.logStatisticService.searchOrgLogsByUsbkey(pageIndex, pageSize, queryBean);
		model.addAttribute("models", pageModels.getContent());
		model.addAttribute("token", token);
		addPageinfo(model, pageModels);
		return "report/mylogs";
	}


	@RequestMapping(value = "mylogs/widget/filetypename/{code}",method=RequestMethod.GET)
	public String getFileTypeName(@PathVariable("code") String code,Model model) {
		model.addAttribute("name",this.baseDataService.getFileTypeName(code));
		return "widget/filetype/view";
	}
	@RequestMapping(value = "mylogs/widget/operation/{id}/name",method=RequestMethod.GET)
	public String getOperationName(@PathVariable("id") String operation,Model model) {
		model.addAttribute("name",this.baseDataService.getOperationTypeName(operation));
		return "widget/operation/view";
	}
	@RequestMapping(value = "mylogs/widget/security/{id}/name",method=RequestMethod.GET)
	public String getSecurityName(@PathVariable("id") String security,Model model) {
		model.addAttribute("name",this.baseDataService.getSecruityName(security));
		return "widget/security/view";
	}
}
