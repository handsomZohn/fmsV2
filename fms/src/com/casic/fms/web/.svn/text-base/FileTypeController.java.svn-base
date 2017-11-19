package com.casic.fms.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.FileType;
import com.casic.fms.service.BaseDataService;

/**
 * Urls:
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class FileTypeController  extends BaseWebController{

	private BaseDataService		baseDataService;
	
	
	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	
	@RequiresPermissions("filetype:list")
	@RequestMapping(value = {"filetypes"},method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("models", this.baseDataService.getFileTypes());
		return "filetype/index";
	}
	
	@RequiresPermissions("filetype:edit")
	@RequestMapping(value = "/filetype/{id}",method=RequestMethod.GET)
	public String editForm(Model model,@PathVariable("id")String id) {
		model.addAttribute("model", this.baseDataService.getFileTypeByID(id));
		return "filetype/form";
	}
	@RequiresPermissions("filetype:create")
	@RequestMapping(value = "/filetype/create",method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new FileType());
		return "filetype/form";
	}
	
	@RequiresPermissions("filetype:save")
	@RequestMapping(value = "/filetype/save",method=RequestMethod.POST)
	public String save(FileType ft,RedirectAttributes redirectAttributes) {
		this.baseDataService.saveFileType(ft);
		return "redirect:/web/filetypes";
	}



}
