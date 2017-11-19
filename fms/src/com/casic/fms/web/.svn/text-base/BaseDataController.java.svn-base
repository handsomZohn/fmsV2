package com.casic.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casic.fms.service.BaseDataService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web/widget")
public class BaseDataController  extends BaseWebController{

	private BaseDataService		baseDataService;
	
	
	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}

	@RequestMapping(value = "/filetypename/{code}",method=RequestMethod.GET)
	public String getFileTypeName(@PathVariable("code") String code,Model model) {
		model.addAttribute("name",this.baseDataService.getFileTypeName(code));
		return "widget/filetype/view";
	}
	@RequestMapping(value = "/operation/{id}/name",method=RequestMethod.GET)
	public String getOperationName(@PathVariable("id") String operation,Model model) {
		model.addAttribute("name",this.baseDataService.getOperationTypeName(operation));
		return "widget/operation/view";
	}
	@RequestMapping(value = "/security/{id}/name",method=RequestMethod.GET)
	public String getSecurityName(@PathVariable("id") String security,Model model) {
		model.addAttribute("name",this.baseDataService.getSecruityName(security));
		return "widget/security/view";
	}

}
