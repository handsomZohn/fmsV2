package com.casic.fms.service.impl;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.engine.query.spi.QueryPlanCache;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import com.casic.common.CustomSettionContext;
import com.casic.fms.service.ChangeLocalService;

@Component
public class ChangeLocalServiceImpl implements ChangeLocalService {

	@PersistenceContext
	private EntityManager em;
	public void changeLocal(String language) {
		if(!CustomSettionContext.getRequestLocal().equals(language)){
			CustomSettionContext.setRequestLocal(language);
			if(em.getEntityManagerFactory() instanceof HibernateEntityManagerFactory){
				try {
					SessionFactory factory = ((HibernateEntityManagerFactory)em.getEntityManagerFactory()).getSessionFactory();
					Field field = SessionFactoryImpl.class.getDeclaredField("queryPlanCache");
					boolean isAcces = field.isAccessible();
					field.setAccessible(true);
					QueryPlanCache querycache = (QueryPlanCache)field.get(factory);
					querycache.cleanup();
					field.setAccessible(isAcces);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
