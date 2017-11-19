package com.casic.fms.service.trigger;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TokenCleanupJob extends QuartzJobBean {
	
	private static Logger logger = LoggerFactory.getLogger(TokenCleanupJob.class);

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.debug("开始检测TOKEN是否过期 ！");
		//启动同步任务
		USBKeyTokenService.getInstance().cleanup();
		logger.debug("检测TOKEN过期完成 ！");
	}

}
