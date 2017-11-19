package com.casic.fms.web.json;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.FileSecurity;
import com.casic.fms.bean.FileType;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.SecurityLevel;
import com.casic.fms.bean.SecurityLimit;
import com.casic.fms.bean.USBKeyInfo;
import com.casic.fms.bean.VersionInfo;
import com.casic.fms.service.AuthenticationService;
import com.casic.fms.service.AuthorizeService;
import com.casic.fms.service.BaseDataService;
import com.casic.fms.service.FileLogService;
import com.casic.fms.service.FileSecurityLevelService;
import com.casic.fms.service.VersionInfoService;

/**
 * Urls:
 * 
 * @author crazylion
 */
@Controller
@RequestMapping(value = "/json")
public class JsonOrgInfoController extends BaseJsonController{





	private static Logger logger = LoggerFactory.getLogger(JsonOrgInfoController.class);


	private AuthorizeService		authorizeService;
	


	@Autowired
	public void setAuthorizeService(AuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}



	
	

	

	/**
	 * 部门USBKey分配信息
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "usbkey/assign",method=RequestMethod.POST)
	@ResponseBody
	public ReInfo assignUSBKey(@RequestParam("orgid") String orgid,@RequestParam("usbkeyid") String usbkeyid) {
		try{
			ReInfo ri = ReInfo.getSucceed();
			this.authorizeService.assignUSBKey(orgid, usbkeyid);
			ri.setMessage("分配USBKey信息完成！");
			return ri;
		}catch(Exception ex){
			return ReInfo.getFailed();
		}
	}








}
