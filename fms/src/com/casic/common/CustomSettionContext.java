package com.casic.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CustomSettionContext {
	
	public static  ThreadLocal<Map> context = new ThreadLocal<Map>();
	
	public static final String REQUEST_LOCAL = "local";
	public static final String REQUEST_LOCAL_DEFAULT = "zh";
	public static final String LOGIN_REDIRECT_URL = "loginRedirectUrl";
	
	public static void setContext(Map contextMap){
		context.set(contextMap);
	}
	
	public static Map getContext(){
		return context.get();
	}
	
	private static void init(){
		context.set(new HashMap());
	}
	
	@SuppressWarnings("unchecked")
	public static void setRequestLocal(String local){
		if(context.get() == null){
			init();
		}
		if(StringUtils.isNotBlank(local)){
			context.get().put(REQUEST_LOCAL, local);
		}
	}
	
	public static String getRequestLocal(){
		if(context.get() == null){
			init();
		}
		if(context.get().get(REQUEST_LOCAL) == null){
			context.get().put(REQUEST_LOCAL, REQUEST_LOCAL_DEFAULT);
		}
		return (String)context.get().get(REQUEST_LOCAL);
	}
	
	@SuppressWarnings("unchecked")
	public static void setReDirectUrl(String redirectUrl){
		if(context.get() == null){
			init();
		}
		if(StringUtils.isNotBlank(redirectUrl)){
			context.get().put(LOGIN_REDIRECT_URL, redirectUrl);
		}
	}
	
	public static String getReDirectUrl(){
		if(context.get() == null){
			init();
		}
		return (String)context.get().get(LOGIN_REDIRECT_URL);
	}


}
