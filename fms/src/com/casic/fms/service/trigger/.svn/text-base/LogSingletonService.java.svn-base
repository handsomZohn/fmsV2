package com.casic.fms.service.trigger;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.casic.fms.service.FileLogService;
import com.casic.fms.service.LogStatisticService;


/**
 * @author crazylion
 * 此处需要作为单例存在于内存中
 */
@Component
public class LogSingletonService {
	
	private static Logger logger = LoggerFactory.getLogger(LogSingletonService.class);

	
	private final static String serviceName ="fms-logservice";
	
	private LogStatisticService	getLogStatisticService(){
		LogStatisticService  logStatisticService=  ContextLoader.getCurrentWebApplicationContext().getBean(LogStatisticService.class);
		return logStatisticService;
	}
	private FileLogService	getFileLogService(){
		FileLogService  fileLogService=  ContextLoader.getCurrentWebApplicationContext().getBean(FileLogService.class);
		return fileLogService;
	}


	private LogSingletonService(){
	}
	
	
	
	/**
	 * WEBAPP只能有一个同步的实例
	 * @param sc
	 * @return
	 */
	public  static synchronized LogSingletonService  getInstance(){
		ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		LogSingletonService instance = (LogSingletonService)sc.getAttribute(serviceName);
		if(instance== null){
			synchronized (LogSingletonService.class) {
				instance = new LogSingletonService();
				sc.setAttribute(serviceName, instance);
			}
		}
		return instance;
	}
	
	
	/**
	 * 启动当天的日志转移工作
	 */
	public void startDailyLogTransfer(){
		/**
		 * 清除当天的日志缓存
		 */
		getFileLogService().clearCache();
		/**
		 * 整理当天新增的客户端信息和USBKey信息
		 */
		getLogStatisticService().cleanupClientInfo();
		/**
		 * 转移当天的日志文件到History
		 */
		getLogStatisticService().transferHistory();
	}
	
	/**
	 * 每个月的第一个工作日晚上九点开始计算月度工作报表
	 */
	public void startMonthlyJob(){
		/**
		 * 整理系统日志期间
		 */
		getLogStatisticService().summaryLogPeriod();
		/**
		 * 计算月报表
		 */
		getLogStatisticService().summaryUserMonthlyReport();
		/**
		 * 计算机构月报表
		 */
		getLogStatisticService().summaryOrginfoMonthlyReport();
	}
}
