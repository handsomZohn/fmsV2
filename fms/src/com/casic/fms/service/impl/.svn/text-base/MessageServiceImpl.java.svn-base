package com.casic.fms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.Message;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.dao.MessageDao;
import com.casic.fms.service.MessageService;

/**
 * 消息管理服务类
 * 
 * @author crazylion
 */
@Component
@Transactional(readOnly = false)
public class MessageServiceImpl implements MessageService{
	
	private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	private  MessageDao  messageDao;
	
	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public Page<Message> find(int pageIndex,int pageSize) {
		Pageable page = new PageRequest(pageIndex, pageSize, Message.getTsSort());
		Page<Message> msgs = this.messageDao.findAll(page);
		return msgs;
	}

	public Message publish(Message msg) {
		// TODO Auto-generated method stub
		msg.updateTs();
		msg = this.messageDao.save(msg);
		return msg;
	}

	public int delete(String id) {
		// TODO Auto-generated method stub
		this.messageDao.delete(id);
		return 1;
	}

	public Message find(String id) {
		// TODO Auto-generated method stub
		return this.messageDao.findOne(id);
	}

	public ReInfo checkUpdate(String lastdate) {
		// TODO Auto-generated method stub
		ReInfo ri = new ReInfo();
		ri.code ="false";
		long updateCount = 0;
		if(	lastdate != null){
			updateCount = this.messageDao.getCountAfterDate(lastdate);
		}else{
			updateCount = this.messageDao.count();
		}
		if(updateCount>0){
			ri.code = "true";
			ri.message = "您有"+updateCount+"条系统通知没有阅读！";
		}else{
			ri.message = "没有需要未阅读的系统通知！";
		}
		return ri;
	}


}
