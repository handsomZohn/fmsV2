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
@RequestMapping(value = "/file/verifycode/reg")
public class VerifyCodeRegController {
	  private static final String VERIFYCODE_REG = "verifyCodeReg";

	  @RequestMapping(value = {"new", ""})
	  public void newCode(Model model, HttpSession session, HttpServletResponse response) throws IOException {
		  VerifyCodeUtil cu = new VerifyCodeUtil();
		  String verifycodeStr = cu.paintImg();
		  putCode(VERIFYCODE_REG, verifycodeStr, session);
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
		String code = getCode(VerifyCodeRegController.VERIFYCODE_REG, session);
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
			  VerifyCodeRegManager.getInstance(this, session).addVerifyCode(code);
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
				Logger.getLogger(VerifyCodeRegController.class).info("Session already invalidated, verifyCodeReg become invalid");
			}
		  }
	  }
	  
	  static class VerifyCodeRegManager {
		  private static final Logger logger = Logger.getLogger(VerifyCodeRegManager.class);
		  
		  private static final Timer timer = new Timer();
		  
		  private static final int DELAY = 3 * 60 * 1000; 
		  
		  private static final Map<String, TimerTask> tasks = new HashMap<String, TimerTask>();
		  
		  private VerifyCodeRegController controller = null;
		  
		  private HttpSession session = null;
		  
		  private VerifyCodeRegManager(){
			  
		  }
		  
		  private VerifyCodeRegManager(VerifyCodeRegController controller, HttpSession session){
			  this.controller = controller;
			  this.session = session;
		  }

		public static VerifyCodeRegManager getInstance(VerifyCodeRegController controller, HttpSession session){
			  return new VerifyCodeRegManager(controller, session);
		  }
		  
		  public synchronized void addVerifyCode(String verifyCode){
			  TimerTask taskOlder = tasks.get(VerifyCodeRegController.VERIFYCODE_REG);
			  if(taskOlder != null){
				  try {
					  taskOlder.cancel();
				} catch (Exception e) {
					logger.info("remove user verifyCodeReg older task failed.");
				}
				tasks.remove(VerifyCodeRegController.VERIFYCODE_REG);
			  }
			  
			  logger.info("add user verifyCodeReg:" + verifyCode);
			  InvalidVerifyCodeRegTask task = InvalidVerifyCodeRegTask.getInstance(verifyCode, controller, session);
			  tasks.put(VerifyCodeRegController.VERIFYCODE_REG, task);
			  timer.schedule(task, DELAY);
		  }
		  
		  public synchronized void removeVerifyCode(String verifyCode){
			  logger.info("remove user verifyCodeReg:" + verifyCode);
			  controller.removeCode(VerifyCodeRegController.VERIFYCODE_REG, session);
			  TimerTask task = tasks.get(VerifyCodeRegController.VERIFYCODE_REG);
			  if(task != null){
				  try {
					task.cancel();
				} catch (Exception e) {
					logger.info("remove user verifyCodeReg task failed.");
				}
				tasks.remove(VerifyCodeRegController.VERIFYCODE_REG);
			  }
		  }
	  }
	  
	  static class InvalidVerifyCodeRegTask extends TimerTask{
		private static final Logger logger = Logger.getLogger(InvalidVerifyCodeRegTask.class);
		
		private final String code;
		  
		private VerifyCodeRegController controller = null;
		  
		private HttpSession session = null;		
		
		public InvalidVerifyCodeRegTask(String code){
			this.code = code;
		}
		
		public InvalidVerifyCodeRegTask(String code,
				VerifyCodeRegController controller, HttpSession session) {
			this.code = code;
			this.controller = controller;
			this.session = session;
		}

		public static InvalidVerifyCodeRegTask getInstance(String code, VerifyCodeRegController controller, HttpSession session){
			return new InvalidVerifyCodeRegTask(code, controller, session);
		}
		  
		@Override
		public void run() {
			logger.info("verifyCodeReg become invalid:" + code);
			if(session != null){
				VerifyCodeRegManager.getInstance(controller, session).removeVerifyCode(code);
			}
		}
		
	  }
	  
}
