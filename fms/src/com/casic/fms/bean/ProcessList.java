package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 系统进程白名单
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_processlist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProcessList extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738590300644558077L;
	
	/**
	 * 文件类型名称
	 */
	private String name;
	

	/**
	 * 安全进程的MD5数值,多个进程MD5用分号区别
	 */
	private String processName;
	

	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}


	
}
