package com.casic.fms.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.casic.fms.bean.FileClassify;
import com.casic.fms.service.BaseDataService;
import com.casic.fms.service.FileClassifyService;

@Controller
@RequestMapping(value = "/web")
public class FileClassifyController extends BaseWebController {
	
	private FileClassifyService fileClassifyService;
	
	private BaseDataService		baseDataService;

	@Autowired
	public void setBaseDataService(BaseDataService baseDataService) {
		this.baseDataService = baseDataService;
	}
	
	@Autowired
	public void setFileClassifyService(FileClassifyService fileClassifyService) {
		this.fileClassifyService = fileClassifyService;
	}
	
	@RequiresPermissions("fileclassify:list")
	@RequestMapping(value = {"fileclassify/{pageIndex}"},method=RequestMethod.GET)
	public String listFileClassifyList(@PathVariable("pageIndex") Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize,Model model) {
		if(pageIndex == null)	pageIndex = 0;
		if(pageSize == null)	pageSize= 10;
		Page<FileClassify>  pageModels = baseDataService.getFileClassifyList(pageIndex, pageSize);
		model.addAttribute("models", pageModels.getContent());
		super.addPageinfo(model, pageModels);
		return "fileclassify/index";
	}
	@RequiresPermissions("fileclassify:create")
	@RequestMapping(value = "/fileclassify/create",method=RequestMethod.GET)
	public String createFileClassifyForm(Model model) {
		model.addAttribute("model",new FileClassify());
		model.addAttribute("levelmodels", this.baseDataService.getSecurityLevels());
		return "fileclassify/form";
	}
	
	@RequiresPermissions("fileclassify:save")
	@RequestMapping(value = "/fileclassify/save",method=RequestMethod.POST)
	public String saveFileClassify(FileClassify classify,RedirectAttributes redirectAttributes) {
		this.baseDataService.addFileClassify(classify);
		return "redirect:/web/fileclassify/0";
	}
	
	@RequiresPermissions("fileclassify:delete")
	@RequestMapping(value = "fileclassify/delete/{id}")
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		this.fileClassifyService.delete(id);
		redirectAttributes.addFlashAttribute("tipinfo", "删除成功");
		return "redirect:/web/fileclassify/0";
	}
	@RequiresPermissions("fileclassify:update")
	@RequestMapping(value = "fileclassify/update/{id}")
	public String update(Model model, @PathVariable("id") String id) {
		model.addAttribute("model", this.baseDataService.getFileClassify(id));
		model.addAttribute("levelmodels", this.baseDataService.getSecurityLevels());
		return "fileclassify/form";
	}
	
	

}
