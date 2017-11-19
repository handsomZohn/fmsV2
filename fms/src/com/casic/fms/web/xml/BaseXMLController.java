package com.casic.fms.web.xml;

import org.codehaus.jackson.map.ObjectMapper;

public class BaseXMLController {


	
	public Object readJsonValue(String body,Class classType){
		try{
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(body, classType);
		}catch(Exception ex){
			System.out.println("解析JSON字符串出错!\nJSON字符串：");
			System.out.println(body);
			ex.printStackTrace();
			return null;
		}
	}
}
