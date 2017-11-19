package com.casic.email;

public interface ModPwdMailService {
	/**
	 * 方法功能：发送可以重新设置密码的邮件. <BR>
	 * @param to
	 * @param userName
	 * @param activityUrl
	 */
	public void sendModPwdMail(String to, String userName, String activityUrl);
	
}
