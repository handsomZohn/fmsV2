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

import com.casic.email.MimeMailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * MIME邮件服务类.
 * 
 * 演示由Freemarker引擎生成的的html格式邮件, 并带有附件.
 
 * @author calvin
 */
public class MimeMailServiceImpl implements MimeMailService{

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger logger = LoggerFactory.getLogger(MimeMailServiceImpl.class);

	private JavaMailSender mailSender;

	private Template template;
	
	private String from;
	
	private String title;

	/**
	 * 发送MIME格式的用户修改通知邮件.
	 */
	public void sendRegistMail(String to,String userName,String password,String activityUrl) {

//		try {
//			MimeMessage msg = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);
//			helper.setTo(to);
//			helper.setFrom(from);
//			helper.setSubject(title);
//
//			String content = generateContent(userName,password,activityUrl);
//			helper.setText(content, true);
//	        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
//	        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
//	        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
//	        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
//	        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
//	        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
//	        CommandMap.setDefaultCommandMap(mc);
//			
//			mailSender.send(msg);
//			logger.info("HTML版邮件已发送");
//		} catch (MessagingException e) {
//			logger.error("构造邮件失败", e);
//		} catch (Exception e) {
//			logger.error("发送邮件失败", e);
//		}
	}

	/**
	 * 使用Freemarker生成html格式内容.
	 */
	@SuppressWarnings("unchecked")
	private String generateContent(String userName,String password,String activityUrl) throws MessagingException {

		try {
			Map context = new HashMap();
			context.put("userName", userName);
			context.put("password", password);
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
	 * Spring的MailSender.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
	 */
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
		//根据freemarkerConfiguration的templateLoaderPath载入文件.
		template = freemarkerConfiguration.getTemplate("mailTemplate.ftl", DEFAULT_ENCODING);
	}
	
	public void setTitle(String title){
		this.title=title;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
}
