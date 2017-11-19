package com.casic.fms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.common.MD5Util;
import com.casic.fms.bean.Permission;
import com.casic.fms.bean.Role;
import com.casic.fms.bean.RolePermission;
import com.casic.fms.bean.User;
import com.casic.fms.bean.UserRole;
import com.casic.fms.dao.GroupDao;
import com.casic.fms.dao.PermissionDao;
import com.casic.fms.dao.RoleDao;
import com.casic.fms.dao.RolePermissionDao;
import com.casic.fms.dao.UserDao;
import com.casic.fms.dao.UserGroupDao;
import com.casic.fms.dao.UserRoleDao;
import com.casic.fms.service.InitService;

/**
 * 初始化用户权限组信息
 * 
 * @author crazylion
 */
@Component
@Transactional(readOnly = false)
public class InitServiceImpl extends BaseServiceImp implements InitService{
	private static Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);
	private UserDao userDao;
	private GroupDao groupDao;
	private RoleDao roleDao;
	private PermissionDao	permissionDao;
	private UserGroupDao		userGroupDao;
	private UserRoleDao		userRoleDao;
	private RolePermissionDao	rolePermissionDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}


	@Autowired
	public void setUserGroupDao(UserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

	@Autowired
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	@Autowired
	public void setRolePermissionDao(RolePermissionDao rolePermissionDao) {
		this.rolePermissionDao = rolePermissionDao;
	}

	/**
	 * 系统默认角色
	 * @return
	 */
	private List<Role> getDefaultRoles(){
		List<Role>	roles = new ArrayList<Role>();
		Role role = new Role();
		role.setId("SYSTEMROLEROOT");
		role.setCode("root");
		role.setName("超级管理员");
		role.setComments("系统默认的超级管理员角色，不能删除，不能做权限修改！");
		roles.add(role);
		return roles;
	}
	
	/**
	 * 获取系统默认用户 
	 * @return
	 */
	private User getDefaultUser(){
		User user = new User();
		user.setEmail("admin@touchwant.com");
		user.setId("1");
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword(MD5Util.getMD5String("123456"));
		return user;
	}
	

	

	private void initUser() {
		User user = this.getDefaultUser();
		this.userDao.save(user);
	}
	
	private void initRoles(){
		this.roleDao.save(getDefaultRoles());
	}
	
	
	private List<Permission> initPermission(){
		List<Permission>		list = new ArrayList<Permission>();
		Permission per = new Permission();
		per.setResourceCode("resource");
		per.setResourceName("资源");
		per.setOperationCode("save");
		per.setOperationName("写入");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);

		per = new Permission();
		per.setResourceCode("resource");
		per.setResourceName("资源");
		per.setOperationCode("view");
		per.setOperationName("浏览");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);

		per = new Permission();
		per.setResourceCode("resource");
		per.setResourceName("资源");
		per.setOperationCode("list");
		per.setOperationName("列表");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);

		per = new Permission();
		per.setResourceCode("resource");
		per.setResourceName("资源");
		per.setOperationCode("remove");
		per.setOperationName("删除");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);
		
		per = new Permission();
		per.setResourceCode("user");
		per.setResourceName("用户");
		per.setOperationCode("save");
		per.setOperationName("写入");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);

		per = new Permission();
		per.setResourceCode("user");
		per.setResourceName("用户");
		per.setOperationCode("view");
		per.setOperationName("浏览");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);

		per.setResourceCode("user");
		per.setResourceName("用户");
		per.setOperationCode("list");
		per.setOperationName("浏览");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);
		
		per.setResourceCode("user");
		per.setResourceName("用户");
		per.setOperationCode("list");
		per.setOperationName("列表");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);
		
		per.setResourceCode("user");
		per.setResourceName("用户");
		per.setOperationCode("remove");
		per.setOperationName("删除");
		this.permissionDao.deleteByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
		list.add(per);
		
		return this.permissionDao.save(list);
	}
	
	private List<RolePermission>	 initRolePermission(List<Permission> defalutPermissions){
		List<RolePermission>		rps = new ArrayList<RolePermission>();
		List<Role>	roles = this.getDefaultRoles();
		for (Role role : roles) {
			this.rolePermissionDao.deleteByRoleId(role.getId());
			for (Permission per : defalutPermissions) {
				RolePermission rp = new RolePermission();
				rp.setPermissionId(per.getId());
				rp.setRoleId(role.getId());
				rps.add(rp);
			}
		}
		return this.rolePermissionDao.save(rps);
	}

	private List<UserRole>	initUserRoles(){
		List<UserRole> userroles = new ArrayList<UserRole>();
		User user = this.getDefaultUser();
		List<Role>	roles = getDefaultRoles();
		for (Role role: roles) {
			UserRole ur = new UserRole();
			ur.setRoleId(role.getId());
			ur.setUserId(user.getId());
			userroles.add(ur);
		}
		this.userRoleDao.deleteByUserId(user.getId());
		return this.userRoleDao.save(userroles);
	}
	
	
	public void initRBAC() {
		
		initUser();
		
		initRoles();
		
		initUserRoles();
		
		List<Permission> pers = initPermission();
		
		initRolePermission(pers);

	}

	
	

}
