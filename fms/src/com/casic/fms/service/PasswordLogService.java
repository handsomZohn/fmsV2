package com.casic.fms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.casic.fms.bean.PasswordLog;

public interface PasswordLogService {
	

	/**
	 * 查询当天最新的日志
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	Page<PasswordLog>	getLogs(Integer pageIndex, Integer pageSize);

	/**
	 * 判断用户是否需要修改密码
	 * @param userid
	 * @return
	 */
	String checkPasswordNeedUpdate(String userid);


}
