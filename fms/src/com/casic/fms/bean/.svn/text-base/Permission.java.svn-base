package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 资源对应的操作，权限最小单元，不需要再定义Resource和Operation
 * 
 * @author crazylion
 */
@Entity
@Table(name = "fms_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission  extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5027337171418088385L;
	private String resourceCode;
	private String resourceName;
	private String operationCode;
	private String operationName;
	
	private String comments;
	
	@Column(length=500,nullable=true)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(length=50,nullable=false)
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	@Column(length=50,nullable=false)
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	@Column(length=50,nullable=false)
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	@Column(length=50,nullable=false)
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	
	
}
