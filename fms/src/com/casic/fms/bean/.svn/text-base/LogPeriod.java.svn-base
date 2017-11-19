package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 日志的年份月度记录,每月计算一次
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_logperiod")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LogPeriod extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3738590300644558077L;
	/**
	 * 年度
	 */
	private String year;
	
	/**
	 * 月份
	 */
	private String month;
	
	
	@Column(length=6,nullable=false)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(length=4,nullable=false)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	
}
