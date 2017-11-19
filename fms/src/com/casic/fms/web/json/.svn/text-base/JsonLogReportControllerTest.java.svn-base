package com.casic.fms.web.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.casic.common.SimpleUtils;
import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.ReInfo;

/**
 * Urls:
 * 
 * @author crazylion
 */

public class JsonLogReportControllerTest extends BaseJsonController {

	private static Logger logger = LoggerFactory
			.getLogger(JsonLogReportControllerTest.class);

	
	public static final int total = 1000;
	int count = 0;
	
	public static void  main(String[] args){
		for(int i=0; i<total; i++){
			Runnable test = new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					//new JsonLogReportControllerTest().getRandKey();
					new JsonLogReportControllerTest().saveLog();
					System.out.println("日志处理成功！");
				}
			};
			test.run();
		}
	}
	
	private void getRandKey(){
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    ReInfo ri = (ReInfo)rest.getForObject("http://localhost:8080/fmsv2/json/randkey/000000000fc1edea",  ReInfo.class);
	    System.out.println(ri.message+ri.getData());
	}
	
	private void saveLog(){
		try{
			FileLog fl = getFileLog();
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity request= new HttpEntity(fl, headers);
		    rest.postForObject("http://localhost:8080/fmsv2/json/filelog", request, FileLog.class);
		    count++;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private	FileLog getFileLog(){
		FileLog fl = new FileLog();
		fl.setClientMac("01:23:23:45:AC:33");
		fl.setClientName("测试客户端");
		fl.setFileFullname("d:\test\test\test.docx");
		fl.setFileId("1231231292339dkhjdafkjhdsa");
		fl.setFileLength(1230);
		fl.setIpAddress("192.168.1.99");
		fl.setUsbkey("123123921832918");
		fl.setUsername("wangshaowei");
		fl.setOpertime(SimpleUtils.getCreateTime());
		fl.setProcessMD5("12312312321939213219329213129");
		fl.setOperation(FileLog.OPERATION_SAVE);
		fl.setProcessPath("winword.exe");
		return fl;
	}
	class TestLogThread implements Runnable{
		
		public TestLogThread() {
			super();
			// TODO Auto-generated constructor stub
		}

		public void run() {
			// TODO Auto-generated method stub
			//new JsonLogReportControllerTest().saveLog();
			new JsonLogReportControllerTest().getRandKey();
		}
		
	}
}
