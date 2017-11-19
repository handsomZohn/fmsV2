package com.casic.fms.bean;

import javax.persistence.Column;

/**
 * 资源或者功能描述
 * @author crazylion
 *
 */
//@Entity
//@Table(name = "fms_resource")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Resource  extends BaseEntity {
	
	
	private String code;
	private String name;
	private String comments;
	
	
	
	@Column(length=30,nullable=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	
	
}
