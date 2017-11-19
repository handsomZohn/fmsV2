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
@Table(name = "fms_user_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroup extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5536551431749885392L;
	private String userId;
	private String groupId;
	@Column(length=50,nullable=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(length=50,nullable=false)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
}
