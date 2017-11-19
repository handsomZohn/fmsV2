package com.casic.fms.web.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casic.fms.bean.Message;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.service.MessageService;

/**
 * Urls:
 * 获取系统发布的消息列表      : GET /json/messages/{pageIndex}
 * 
 * @author crazylion
 */
@Controller
@RequestMapping(value = "/json")
public class JsonMessageController extends BaseJsonController{

	private MessageService	messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	
	@RequestMapping(value = "messages/{pageIndex}",method=RequestMethod.GET)
	@ResponseBody
	public Page<Message> listAll(@PathVariable("pageIndex") Integer pageIndex,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		if(pageSize == null)	pageSize= 10;
		Page<Message>  models = this.messageService.find(pageIndex, pageSize);
		return models;
	}
	
	/**
	 * 检测是否有新消息
	 * @param lastdate
	 * @return
	 */
	@RequestMapping(value = "messages/checkupdate",method=RequestMethod.GET)
	@ResponseBody
	public ReInfo checkUpdate(@RequestParam(value=("lastdate"), required=false) String lastdate) {
		return this.messageService.checkUpdate(lastdate);
	}
	
	
	/**
	 * 测试消息发布接口
	 * @param url
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "message",method=RequestMethod.POST)
	@ResponseBody
	public Message push( @RequestBody String body) {
		Message msg = (Message)super.readJsonValue(body, Message.class);
		msg = this.messageService.publish(msg);
		return msg;
	}


}
