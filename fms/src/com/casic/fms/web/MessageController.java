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

import com.casic.fms.bean.Message;
import com.casic.fms.service.MessageService;

/**
 * Urls:
 * 消息列表        : GET  /web/messages/{pageindex}?pageSize=10
 * 发布消息        : GET  /web/message
 * 保存消息	      : POST /web/message
 * 修改消息        : GET  /web/message/update/{id}
 * 删除消息        : GET  /web/message/delete/{id}
 * 
 * @author crazylion
 *
 */
@Controller
@RequestMapping(value = "/web")
public class MessageController  extends BaseWebController{

	private MessageService	messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	@RequiresPermissions("message:list")
	@RequestMapping(value = "messages/{pageIndex}",method=RequestMethod.GET)
	public String list(@PathVariable("pageIndex") Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize,Model model) {
		if(pageSize == null)	pageSize= 10;
		Page<Message>  pageModels = this.messageService.find(pageIndex, pageSize);
		model.addAttribute("models", pageModels.getContent());
		addPageinfo(model, pageModels);
		return "message/index";
	}
	@RequiresPermissions("message:message")
	@RequestMapping(value = "message",	method=RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("model", new Message());
		return "message/form";
	}
	@RequiresPermissions("message:update")
	@RequestMapping(value = "message/update/{id}",	method=RequestMethod.GET)
	public String update(@PathVariable("id") String id,Model model) {
		Message msg = this.messageService.find(id);
		model.addAttribute("model", msg);
		return "message/form";
	}
	//@RequiresPermissions("message:showMessage")
	@RequestMapping(value = "message/{id}",	method=RequestMethod.GET)
	public String showMessage(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("message", new Message());
		return "message/view";
	}
	
	@RequiresPermissions("message:save")
	@RequestMapping(value = "message", method=RequestMethod.POST)
	public String save(Message message, RedirectAttributes redirectAttributes) {
		this.messageService.publish(message);
		redirectAttributes.addFlashAttribute("tipinfo", "消息保存成功");
		return "redirect:messages/0";
	}
	@RequiresPermissions("message:delete")
	@RequestMapping(value = "message/delete/{id}")
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		this.messageService.delete(id);
		redirectAttributes.addFlashAttribute("tipinfo", "删除消息成功");
		return "redirect:/web/messages/0";
	}

}
