package com.casic.fms.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casic.common.VerifyCodeUtil;

@Controller
@RequestMapping(value = "/file/verifycode")
public class VerifyCodeController {
	  private static final String VERIFYCODE = "verifyCode";

	  @RequestMapping(value = {"new", ""})
	  public void newCode(Model model, HttpSession session, HttpServletResponse response) throws IOException {
		  VerifyCodeUtil cu = new VerifyCodeUtil();
		  String verifycodeStr = cu.paintImg();
		  putCode(VERIFYCODE, verifycodeStr, session);
		  response.setHeader("Pragma", "no-cache");
		  response.setHeader("Cache-Control", "no-cache");
		  response.setDateHeader("Expires", 0);
		  response.setContentType("image/jpeg");

		  ServletOutputStream os = response.getOutputStream();
		  ImageIO.write(cu.getBuffImg(), "jpeg", os);
		  os.close();
	  }	  

	  @RequestMapping(value = "validate")
	  @ResponseBody
	  public String validate(@RequestParam("verifyCode") String verifyCode, HttpSession session) {
		verifyCode = StringUtils.trim(verifyCode);   
		String code = getCode(VerifyCodeController.VERIFYCODE, session);
		if(verifyCode == null){
			return "false";
		}else if(verifyCode.equalsIgnoreCase(code)){
			return "true";
		}else{
			return "false";
		}
	  }
	  
	  private void  putCode(String VERIFYCODE, String code, HttpSession session){
		  if(VERIFYCODE != null && code != null && session != null){
			  session.setAttribute(VERIFYCODE, code);
			  VerifyCodeManager.getInstance(this, session).addVerifyCode(code);
		  }
	  }
	  
	  private String getCode(String VERIFYCODE, HttpSession session){
		  return (String) session.getAttribute(VERIFYCODE);
	  }
	  
	  private void removeCode(String VERIFYCODE, HttpSession session){
		  if(session != null){
			  try {
				session.removeAttribute(VERIFYCODE);
			} catch (IllegalStateException illE) {
				Logger.getLogger(VerifyCodeController.class).info("Session already invalidated, verifyCode become invalid");
			}
		  }
	  }
	  
	  static class VerifyCodeManager {
		  private static final Logger logger = Logger.getLogger(VerifyCodeManager.class);
		  
		  private static final Timer timer = new Timer();
		  
		  private static final int DELAY = 1 * 60 * 1000; 
		  
		  private static final Map<String, TimerTask> tasks = new HashMap<String, TimerTask>();
		  
		  private VerifyCodeController controller = null;
		  
		  private HttpSession session = null;
		  
		  private VerifyCodeManager(){
			  
		  }
		  
		  private VerifyCodeManager(VerifyCodeController controller, HttpSession session){
			  this.controller = controller;
			  this.session = session;
		  }

		public static VerifyCodeManager getInstance(VerifyCodeController controller, HttpSession session){
			  return new VerifyCodeManager(controller, session);
		  }
		  
		  public synchronized void addVerifyCode(String verifyCode){
			  TimerTask taskOlder = tasks.get(VerifyCodeController.VERIFYCODE);
			  if(taskOlder != null){
				  try {
					  taskOlder.cancel();
				} catch (Exception e) {
					logger.info("remove user verifyCode older task failed.");
				}
				tasks.remove(VerifyCodeController.VERIFYCODE);
			  }
			  
			  logger.info("add user verifyCode:" + verifyCode);
			  InvalidVerifyCodeTask task = InvalidVerifyCodeTask.getInstance(verifyCode, controller, session);
			  tasks.put(VerifyCodeController.VERIFYCODE, task);
			  timer.schedule(task, DELAY);
		  }
		  
		  public synchronized void removeVerifyCode(String verifyCode){
			  logger.info("remove user verifyCode:" + verifyCode);
			  controller.removeCode(VerifyCodeController.VERIFYCODE, session);
			  TimerTask task = tasks.get(VerifyCodeController.VERIFYCODE);
			  if(task != null){
				  try {
					task.cancel();
				} catch (Exception e) {
					logger.info("remove user verifyCode task failed.");
				}
				tasks.remove(VerifyCodeController.VERIFYCODE);
			  }
		  }
	  }
	  
	  static class InvalidVerifyCodeTask extends TimerTask{
		private static final Logger logger = Logger.getLogger(InvalidVerifyCodeTask.class);
		
		private final String code;
		  
		private VerifyCodeController controller = null;
		  
		private HttpSession session = null;		
		
		public InvalidVerifyCodeTask(String code){
			this.code = code;
		}
		
		public InvalidVerifyCodeTask(String code,
				VerifyCodeController controller, HttpSession session) {
			this.code = code;
			this.controller = controller;
			this.session = session;
		}

		public static InvalidVerifyCodeTask getInstance(String code, VerifyCodeController controller, HttpSession session){
			return new InvalidVerifyCodeTask(code, controller, session);
		}
		  
		@Override
		public void run() {
			logger.info("verifyCode become invalid:" + code);
			if(session != null){
				VerifyCodeManager.getInstance(controller, session).removeVerifyCode(code);
			}
		}
		
	  }
	  
}
