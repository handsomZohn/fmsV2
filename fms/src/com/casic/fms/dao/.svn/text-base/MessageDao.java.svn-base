package com.casic.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casic.fms.bean.Message;

/**
 * 车辆品牌Dao interface.
 * 
 * @author crazylion
 */
public interface MessageDao extends JpaRepository<Message, String> {

	
	/**
	 * 获取某个日期之后的消息数量
	 * @param date
	 * @return
	 */
	@Query("select count(id) from Message m where m.ts >= :lastdate")
	long getCountAfterDate(@Param("lastdate")String lastdate);
}
