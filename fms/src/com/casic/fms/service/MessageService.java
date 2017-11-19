
package com.casic.fms.service;

import org.springframework.data.domain.Page;

import com.casic.fms.bean.Message;
import com.casic.fms.bean.ReInfo;


/**
 * 消息服务
 * @author crazylion
 *
 */
public interface MessageService {
	
	/**
	 * 根据时间获取所有的系统消息
	 * @return
	 */
	public Page<Message> find(int pageIndex,int pageSize);
	
	
	/**
	 * 获取消息信息
	 * @param id
	 * @return
	 */
	public Message find(String id);
	
	
	/**
	 * 发布一个消息
	 * @param 
	 * @return
	 */
	public Message publish(Message msg);
	
	/**
	 * 删除一个消息
	 * @param id
	 * @return
	 */
	public int delete(String id);
	
	/**
	 * 检测消息是否有更新，如有更新
	 * Reinfo.code="true'，否则返回Reinfo.code="false"
	 * @param lastdate
	 * @return
	 */
	public ReInfo checkUpdate(String lastdate);
}
