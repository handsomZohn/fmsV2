package com.casic.email.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.casic.email.ModPwdMailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 邮件服务类：发送可以重新设置密码的邮件
 * @author yunpeng
 *
 */
public class ModPwdMailServiceImpl implements ModPwdMailService {

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger logger = LoggerFactory.getLogger(ModPwdMailServiceImpl.class);

	private JavaMailSender mailSender;

	private Template template;
	
	private String from;
	
	private String title;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 设置发送修改密码邮件的内容模版
	 * @param freemarkerConfiguration
	 * @throws IOException
	 */
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
		template = freemarkerConfiguration.getTemplate("modPwdTemplate.ftl", DEFAULT_ENCODING);
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	/**
	 * 将发送的修改密码所需信息，根据模版生成出来html格式内容
	 * @param userName
	 * @param activityUrl
	 * @return
	 * @throws MessagingException
	 */
	@SuppressWarnings("unchecked")
	private String generateModPwdContent(String userName, String activityUrl) throws MessagingException {
		try {
			Map context = new HashMap();
			context.put("userName", userName);
			context.put("activityUrl", activityUrl);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
		} catch (IOException e) {
			logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
			throw new MessagingException("FreeMarker模板不存在", e);
		} catch (TemplateException e) {
			logger.error("生成邮件内容失败, FreeMarker处理失败", e);
			throw new MessagingException("FreeMarker处理失败", e);
		}
	}
	
	/**
	 * 发送可以重新设置密码的邮件
	 * @param to
	 * @param userName
	 * @param activityUrl
	 */
	public void sendModPwdMail(String to, String userName, String activityUrl) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(title);
			
			String content = generateModPwdContent(userName, activityUrl);
			helper.setText(content, true);
			
	        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(mc);
			
			mailSender.send(msg);
			logger.info("HTML版邮件已发送");
		} catch (MessagingException e) {
			logger.error("构造邮件失败", e);
		} catch (Exception e) {
			logger.error("发送邮件失败", e);
		}
	}
	
}
