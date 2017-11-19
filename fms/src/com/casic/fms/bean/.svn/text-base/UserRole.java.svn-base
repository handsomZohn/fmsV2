package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户角色定义
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_user_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRole extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6285428875352139093L;
	private String userId;
	private String roleId;
	
	@Column(length=50,nullable=false)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(length=50,nullable=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
}
