package com.casic.common.spring;

import java.lang.reflect.Field;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.engine.query.spi.QueryPlanCache;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.casic.common.CustomSettionContext;

public class ExtendSessionLocaleResolver extends SessionLocaleResolver {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void setLocale(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		super.setLocale(request, response, locale);
		CustomSettionContext.setRequestLocal(locale.getLanguage());
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
