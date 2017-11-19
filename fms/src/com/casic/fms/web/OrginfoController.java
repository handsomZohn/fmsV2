package com.casic.fms.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.OrgInfoBean;
import com.casic.fms.bean.OrgUSBKeyInfo;
import com.casic.fms.bean.USBKeyInfo;
import com.casic.fms.service.AuthorizeService;
import com.casic.fms.service.BaseDataService;

/**
 * Urls:
 * List   page        : GET  /web/orginfoes/{pageIndex}?pageSize={pageSize}
 * Create page        : GET  /web/orginfo/create
 * Create action      : POST /web/orginfo/save
 * Update page        : GET  /web/orginfo/update/{id}
 * Update action      : POST /web/orginfo/save/{id}
 * Delete action      : POST /web/orginfo/delete/{id}
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class OrginfoController extends BaseWebController{




	private AuthorizeService		authorizeService;
	private BaseDataService		baseDataService;
	

	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	
	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}

	@RequiresPermissions("orginfo:list")
	@RequestMapping(value = { "/orginfoes" },method=RequestMethod.GET)
	public String list(Model model,@RequestParam(value="parentid", required=false)String parentid) {
		List<OrgInfoBean> orgs = null;
		if(parentid == null){
			orgs = authorizeService.getTopOrgInfoes();
		}else{
			orgs = authorizeService.getChildsByParent(parentid);
			model.addAttribute("parentid", parentid);
		}
		model.addAttribute("models", orgs);
		return "orginfo/index";
	}

	@RequiresPermissions("orginfo:create")
	@RequestMapping(value = "/orginfo/create",method=RequestMethod.GET)
	public String createForm(Model model,@RequestParam(value="parentid", required=false)String parentId) {
		OrgInfoBean orginfo = new OrgInfoBean();
		orginfo.setParentId(parentId);
		model.addAttribute("model", orginfo);
		return "orginfo/form";
	}
	@RequiresPermissions("orginfo:update")
	@RequestMapping(value = "/orginfo/{id}",method=RequestMethod.GET)
	public String updateForm(Model model,@PathVariable("id")String id) {
		OrgInfoBean orginfo = this.authorizeService.getOrgInfo(id);
		model.addAttribute("model", orginfo);
		return "orginfo/form";
	}
	@RequiresPermissions("orginfo:save")
	@RequestMapping(value = "/orginfo/save",method=RequestMethod.POST)
	public String save(OrgInfoBean model,RedirectAttributes redirectAttributes) {
		authorizeService.saveOrgInfo(model);
		return "redirect:/web/orginfoes";
	}

	@RequiresPermissions("orginfo:remove")
	@RequestMapping(value = "/orginfo/remove/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		authorizeService.removeOrgInfo(id);
		return "redirect:/web/orginfoes";
	}

	//@RequiresPermissions("orgusbkey:remove")
	@RequestMapping(value = "/orginfo/{id}/usbkey",method=RequestMethod.GET)
	public String getUSBKeyByOrg(Model model,@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		model.addAttribute("model", authorizeService.getOrgInfo(id));
		model.addAttribute("models", authorizeService.getUSBKeyInfoByOrgID(id));
		return "orginfo/usbkey";
	}
	//@RequiresPermissions("orgusbkey:remove")
	@RequestMapping(value = "/orgusbkey/delete/{id}",method=RequestMethod.GET)
	public String deleteUSBKeyByOrg(Model model,@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		OrgUSBKeyInfo oui= authorizeService.findOrgUSBKeyInfo(id);
		OrgInfoBean oib= authorizeService.findOrgInfoByOrgCode(oui.getOrginfoCode());
		authorizeService.deleteOrgUSBKeyInfo(id);
		return "redirect:/web/orginfo/"+oib.getId()+"/usbkey";
	}
	//@RequiresPermissions("orgusbkey:remove")
	@RequestMapping(value = "/orgusbkey/{id}/role",method=RequestMethod.GET)
	public String updateUSBKeyByOrgRole(Model model,@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		OrgUSBKeyInfo ouki = authorizeService.updateOrgUSBKeyRole(id);
		OrgInfoBean oib= authorizeService.findOrgInfoByOrgCode(ouki.getOrginfoCode());
		return "redirect:/web/orginfo/"+oib.getId()+"/usbkey";
	}

	
	@RequestMapping(value = "/usbkeyinfo/{usbkey}",method=RequestMethod.GET)
	public String updateUSBKeyForm(Model model,@PathVariable("usbkey")String usbkey) {
		USBKeyInfo usbkeyinfo = this.authorizeService.getUSBKey(usbkey);
		model.addAttribute("model", usbkeyinfo);
		model.addAttribute("sls", baseDataService.getSecurityLevels());
		return "usbkeyinfo/form";
	}
	//@RequiresPermissions("role:save")
	@RequestMapping(value = "/usbkeyinfo/save",method=RequestMethod.POST)
	public String saveUSBKey(USBKeyInfo model,RedirectAttributes redirectAttributes) {
		authorizeService.saveUSBKey(model);
		return "redirect:/web/clients/0";
	}

	
	//@RequiresPermissions("orginfo:getChilds")
	@RequestMapping(value = "/widget/orginfo/{id}/childs",method=RequestMethod.GET)
	public String getChilds(Model model,@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		List<OrgInfoBean>  lists = authorizeService.getChildsByParent(id);
		model.addAttribute("models", lists);
		return "/widget/orginfo/index";
	}
	//@RequiresPermissions("orginfo:getOrgFullName")
	@RequestMapping(value = "/widget/orginfo/{code}/fullname",method=RequestMethod.GET)
	public String getOrgFullName(Model model,@PathVariable("code") String orgcode, RedirectAttributes redirectAttributes) {
		model.addAttribute("fullname", authorizeService.getOrgFullName(orgcode));
		return "/widget/orginfo/fullname";
	}
	
	
	
	//@RequiresPermissions("orginfo:unAssign")
	@RequestMapping(value = "widget/orginfo/{id}/unassignusbkey",method=RequestMethod.GET)
	public String getUnAssignUSBKeyByOrg(Model model,@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		model.addAttribute("models", authorizeService.getNoOrgUSBKeyByOrgID(null));
		return "widget/usbkey/unassign";
	}




}
