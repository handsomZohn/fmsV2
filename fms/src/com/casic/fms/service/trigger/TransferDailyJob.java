package com.casic.fms.service.trigger;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TransferDailyJob extends QuartzJobBean {
	
	private static Logger logger = LoggerFactory.getLogger(TransferDailyJob.class);

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.debug("开始启动当天的日志服务！");
		//启动同步任务
		LogSingletonService.getInstance().startDailyLogTransfer();
		System.out.println("完成了当天的日志服务！");
	}

}
