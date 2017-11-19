package com.casic.fms.service.impl;

import java.util.Collections;
import java.util.Locale;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * 远程获取数据接口基类，可以在配置文件中进行定义
 * 
 * @author crazylion
 * 
 */
public class BaseServiceImp extends RestTemplate {

	private static Logger logger = LoggerFactory.getLogger(BaseServiceImp.class);

	protected MessageSource messageSource;
	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Locale getLocale(){
		return null;
	}
	
	public String getMessage(String code){
		return this.messageSource.getMessage(code, null, getLocale());
	}
	
	public String getMessage(String code, Object[] args){
		return this.messageSource.getMessage(code, args, getLocale());
	}
	
	public String getRequestUrl(String uri) {
		return  uri;
	}

	public Object postForJson(String url, Object requestObjectData,
			Class responseType) {
		try {

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));

			HttpEntity request = new HttpEntity(requestObjectData, requestHeaders);
			
			return postForObject(getRequestUrl(url), request, responseType);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Object readJsonValue(String body, Class classType) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(body, classType);
		} catch (Exception ex) {
			System.out.println("解析JSON字符串出错!\nJSON字符串：");
			System.out.println(body);
			ex.printStackTrace();
			return null;
		}
	}


}
