package com.casic.fms.service.trigger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.casic.common.SimpleUtils;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.USBKeyInfo;
import com.casic.fms.service.AuthorizeService;


/**
 * @author crazylion
 * 此处需要作为单例存在于内存中
 */
@Component
public class USBKeyTokenService {
	
	private static Logger logger = LoggerFactory.getLogger(USBKeyTokenService.class);

	
	private final static String serviceName ="fms-usbkeyservice";
	
	/**
	 * USBKey的临时访问字符串
	 */
	private Map<String,RandToken>	randKeys = Collections.synchronizedMap(new HashMap<String,RandToken>());
	
	private Map<String,String>		key2TOken = Collections.synchronizedMap(new HashMap<String,String>());
	private AuthorizeService	getAuthorizeService(){
		AuthorizeService  authorizeService=  ContextLoader.getCurrentWebApplicationContext().getBean(AuthorizeService.class);
		return authorizeService;
	}


	private USBKeyTokenService(){
	}
	
	
	
	
	/**
	 * WEBAPP只能有一个同步的实例
	 * @param sc
	 * @return
	 */
	public  static synchronized USBKeyTokenService  getInstance(){
		ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		USBKeyTokenService instance = (USBKeyTokenService)sc.getAttribute(serviceName);
		if(instance== null){
			synchronized (USBKeyTokenService.class) {
				instance = new USBKeyTokenService();
				sc.setAttribute(serviceName, instance);
			}
		}
		return instance;
	}
	
	
	/**
	 * 启动当天的日志转移工作
	 */
	public  ReInfo getToken(String usbkey){
		try{
				USBKeyInfo usbkeyinfo = getAuthorizeService().getUSBKey(usbkey);
				if(usbkeyinfo != null){
					String  oldtoken = this.key2TOken.get(usbkey);
					if(oldtoken != null){
						//清除旧的TOKEN
						this.randKeys.remove(oldtoken);
					}
					ReInfo ri = ReInfo.getSucceed();
					ri.data = UUID.randomUUID().toString();
					RandToken token = new RandToken();
					token.token = ri.data;
					token.usbkey = usbkey;
					token.ts = SimpleUtils.getCreateTime();
					this.randKeys.put(ri.data, token);
					this.key2TOken.put(usbkey, ri.data);
					ri.message="获取临时token成功！";
					System.out.println("现在共有"+String.valueOf(this.randKeys.size())+"有效TOKEN");
					return ri;
				}else{
					ReInfo ri = ReInfo.getFailed();
					ri.message ="KEY："+usbkey+"暂时系统还没进行统计，请等待明天进行操作！";
					return ri;
				}
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}finally{
		}
		ReInfo ri = ReInfo.getFailed();
		ri.message ="KEY："+usbkey+"暂时系统还没进行统计，请等待明天进行操作！";
		return ri;
	}
	
	public String getUsbkeyByToken(String token){
		if(isValid(token)){
			return this.randKeys.get(token).usbkey;
		}
		return null;
	}
	
	public boolean isValid(String token){
		return this.randKeys.containsKey(token);
	}
	
	/**
	 * 每分钟定时整理一次
	 */
	public synchronized boolean cleanup(){
		try{
			Set<String> tokens =  randKeys.keySet();
			Calendar calendarNow = Calendar.getInstance();
			calendarNow.setTime(SimpleUtils.getSimpleDateFormat().parse(SimpleUtils.getCreateTime()));
			while(!tokens.isEmpty() && tokens.iterator().hasNext()) {
				String token = tokens.iterator().next();
				RandToken randToken = randKeys.get(token);
				Calendar calendarTs = Calendar.getInstance();
				calendarTs.setTime(SimpleUtils.getSimpleDateFormat().parse(randToken.ts));
				calendarTs.add(Calendar.MINUTE, 30);
				if(calendarNow.after(calendarTs)){
					this.randKeys.remove(token);
					logger.info("清除TOKEN成功！");
				}
			}
			
		}catch(Exception ex){
			logger.error("清除USBKey临时token出错！",ex);
		}finally{
		}
		return false;
		
	}
	
	class RandToken{
		String token;
		String usbkey;
		String ts;
	}
	
	public static void main(String[] str){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			Calendar calendarTs = Calendar.getInstance();
			calendarTs.setTime(sdf.parse("2015-11-16 16:30:01"));
			calendarTs.add(Calendar.MINUTE, 30);
			
			Calendar calendarNow = Calendar.getInstance();
			calendarNow.setTime(new Date());
			if(calendarNow.before(calendarTs)){
				System.out.println("超时了");
			}else{
				System.out.println("没超时");
			}
		}catch(Exception ex){
			
		}
	}
}
