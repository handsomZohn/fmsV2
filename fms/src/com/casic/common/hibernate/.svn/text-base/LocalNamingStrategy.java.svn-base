package com.casic.common.hibernate;

import org.hibernate.cfg.ImprovedNamingStrategy;

import com.casic.common.CustomSettionContext;

public class LocalNamingStrategy extends ImprovedNamingStrategy {
	

//	@Override
//	public String tableName(String tableName) {
//		String table = super.tableName(tableName);
//		if(CustomSettionContext.getRequestLocal()!=null){
//			table+= "_" + CustomSettionContext.getRequestLocal();
//		}
//		return table;
//	}

	@Override
	public String classToTableName(String className) {
		if(CustomSettionContext.getRequestLocal()!=null){
			return super.classToTableName(className)+ "_" + CustomSettionContext.getRequestLocal();
		}
		return super.classToTableName(className);
	}

}
