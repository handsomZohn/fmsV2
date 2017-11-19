package com.casic.fms.web.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Urls:
 * 
 * @author crazylion
 */
@Controller
@RequestMapping(value = "/json")
public class XMLWeiXinController extends BaseXMLController{





	private static Logger logger = LoggerFactory.getLogger(XMLWeiXinController.class);
	
	/**
	 * 增加文档类型的安全进程
	 * @param url
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "wx",method=RequestMethod.GET)
	@ResponseBody
	public Object processWeiXin() {
		String str = "<xml><username>wangshaowei</username></xml>";
		return str;
	}



	
	public static void main(String[] strs){
		String filename = "c:\\test\\test.pdf";
		System.out.println(filename.substring(filename.lastIndexOf(".")));
	}




}
