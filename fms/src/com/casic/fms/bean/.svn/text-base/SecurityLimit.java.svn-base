package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 保密级别关键字指定，指定的关键字需要设置最低的保密级别
 * 
 * @author crazylion
 */
@Entity
@Table(name = "fms_security_limit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SecurityLimit extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1231952122698546588L;

	/**
	 * 密级名称
	 */
	private String keyword;
	
	/**
	 * 密级设置
	 */
	private String securityCode;

	public SecurityLimit() {
	}
	
	@Column(length=50,nullable=false)
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Column(length=50,nullable=false)
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}


	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}



}
