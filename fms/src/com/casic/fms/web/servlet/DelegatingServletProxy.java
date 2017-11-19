package com.casic.fms.web.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class DelegatingServletProxy extends GenericServlet {
	private String targetBeanName;
	private Servlet proxy;
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		targetBeanName = config.getInitParameter("targetBeanName");
		this.getServletBean(config.getServletContext());
		proxy.init(config);
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		proxy.service(request, response);
	}
	
	public Servlet getServletBean(ServletContext servletContext){
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		proxy = (Servlet)wac.getBean(this.targetBeanName);
		return proxy;
	}
		
}
