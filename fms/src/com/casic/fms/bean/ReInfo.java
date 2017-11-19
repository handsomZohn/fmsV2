package com.casic.fms.bean;

/**
 * 操作返回结果，根据不同的消息提示和编码,每个接口自己确定返回的数据编码及消息
 * @author crazylion
 *
 */
public class ReInfo {
	
	public String code;
	
	public String message;
	
	public String data;
	
	public static ReInfo getSucceed(){
		ReInfo ri = new ReInfo();
		ri.code = "9";
		return ri;
	}
	
	public static ReInfo getFailed(){
		ReInfo ri = new ReInfo();
		ri.code = "0";
		return ri;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
