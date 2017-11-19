package com.casic.fms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 角色权限定义
 * @author crazylion
 *
 */
@Entity
@Table(name = "fms_role_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RolePermission extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8364515226565173653L;
	private String roleId;
	private String permissionId;
	
	public RolePermission(String roleId, String permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}
	public RolePermission() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column(length=50,nullable=false)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(length=50,nullable=false)
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
