package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 保密级别
 * 
 * @author crazylion
 */
@Entity
@Table(name = "fms_security_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SecurityLevel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1231952122698546588L;
	/**
	 * 密级编码
	 */
	private String code;
	/**
	 * 密级名称
	 */
	private String name;
	/**
	 * 密级说明
	 */
	private String comments;
	
	/**
	 * 是否启用
	 */
	private boolean isUsed;

	public SecurityLevel() {
	}

	public SecurityLevel(String code, String name) {
		this.code = code;
		this.name = name;
	}

	@Column(length=50,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=500,nullable=true)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	@Column(length=30,nullable=false,unique=true)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Column( nullable=false)
	public boolean isUsed() {
		
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
