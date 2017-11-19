package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户消息推送表
 * 
 * 
 * @author crazylion
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "fms_message")
public class Message extends BaseEntity {
	
	/**
	 * 消息主题
	 */
	private String title;
	
	/**
	 * 消息内容
	 */
	private String msgContent;
	
	/**
	 * 包含的URL
	 */
	private String url;


	@Column(length=100,nullable=false)
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}



	@Column(length=255,nullable=true)
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	@Column(length=2000,nullable=false)
	public String getMsgContent() {
		return msgContent;
	}


	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
}