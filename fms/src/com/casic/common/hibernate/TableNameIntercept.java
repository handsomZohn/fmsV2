package com.casic.common.hibernate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.casic.common.CustomSettionContext;

@Aspect 
public class TableNameIntercept {
	
	@Around("execution(* org.hibernate.persister.entity..*.*(..))") 
	public Object convertTableName(ProceedingJoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();  
		Object obj = null;  
		try {  
		    obj = joinPoint.proceed(args); 
		    if(CustomSettionContext.getRequestLocal()!=null){
				return obj+ "_" + CustomSettionContext.getRequestLocal();
			}
		} catch (Throwable e) {  
		    e.printStackTrace();  
		}  
		return obj;  
	}

}
