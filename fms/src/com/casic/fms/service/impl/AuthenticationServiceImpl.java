package com.casic.fms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.common.MD5Util;
import com.casic.fms.bean.Group;
import com.casic.fms.bean.PasswordLog;
import com.casic.fms.bean.Permission;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.Role;
import com.casic.fms.bean.User;
import com.casic.fms.bean.UserRole;
import com.casic.fms.dao.GroupDao;
import com.casic.fms.dao.PasswordLogDao;
import com.casic.fms.dao.PermissionDao;
import com.casic.fms.dao.RoleDao;
import com.casic.fms.dao.RolePermissionDao;
import com.casic.fms.dao.UserDao;
import com.casic.fms.dao.UserGroupDao;
import com.casic.fms.dao.UserRoleDao;
import com.casic.fms.service.AuthenticationService;

/**
 * 安全相关实体的管理类,包括用户和权限组.
 * 
 * @author crazylion
 */
@Component
@Transactional(readOnly = false)
public class AuthenticationServiceImpl extends BaseServiceImp implements AuthenticationService{
	private static Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	private UserDao userDao;
	private GroupDao groupDao;
	private RoleDao roleDao;
	private PermissionDao	permissionDao;
	private UserGroupDao		userGroupDao;
	private UserRoleDao		userRoleDao;
	private RolePermissionDao	rolePermissionDao;
	private PasswordLogDao	passwordLogDao;
	@Autowired
	public void setPasswordLogDao(PasswordLogDao passwordLogDao) {
		this.passwordLogDao = passwordLogDao;
	}
	@Autowired
	private ShiroDbRealm shiroRealm;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Autowired(required = false)
	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}
	
	@Autowired
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	@Autowired
	public void setRolePermissionDao(RolePermissionDao rolePermissionDao) {
		this.rolePermissionDao = rolePermissionDao;
	}

	@Autowired
	public void setUserGroupDao(UserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
	
	
	@Autowired
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public User getUser(String id) {
		return userDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public User saveUser(User user, UserRole userRole) {
		User olduser = this.userDao.findOne(user.getId());
		if(user.getPassword() != null && user.getPassword().trim().length()>0){
			//修改用户密码
			user.setPassword(MD5Util.getMD5String(user.getPassword()));
		}else{
			//不更新用户密码
			user.setPassword(olduser.getPassword());
		}
		user = userDao.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getLoginName());
		
		if(userRole != null){
			userRole.setUserId(user.getId());
			this.userRoleDao.deleteByUserId(userRole.getUserId());
			this.userRoleDao.save(userRole);
		}
		//保存密码修改日志
		PasswordLog passwordLog = new PasswordLog();
		passwordLog.updateTs();
		passwordLog.setUserid(user.getId());
		passwordLog.setUsername(user.getLoginName());
		passwordLog.setOperresult("密码修改成功");
		passwordLog.setOperation("修改密码");
		passwordLog.setOpertime(passwordLog.getTs());
		if (passwordLog != null) {
			
			this.passwordLogDao.save(passwordLog);
		}
		return user;
	}
	
	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	@Transactional(readOnly = false)
	public ReInfo deleteUser(String id) {
		ReInfo ri = new ReInfo();
		ri.setCode("false");
		if (isSupervisor(id)) {
			ri.setMessage(getMessage("user.system.cannotdelete"));
			return ri;
		}
		userDao.delete(id);
		ri.setCode("true");
		return ri;
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(String id) {
		return "1".equals(id);
	}

	public List<User> getAllUser() {
		return (List<User>) userDao.findAll(new Sort(Direction.ASC, "id"));
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public Group getGroup(String id) {
		return groupDao.findOne(id);
	}

	public List<Group> getAllGroup() {
		return (List<Group>) groupDao.findAll((new Sort(Direction.ASC, "id")));
	}

	@Transactional(readOnly = false)
	public void saveGroup(Group entity) {
		groupDao.save(entity);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteGroup(String id) {
		groupDao.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}




	public Role getUserRoleByUserid(String userid){
		List<UserRole> userroles = this.userRoleDao.findByUserId(userid);
		if(userroles != null && userroles.size()>0){
			return this.roleDao.findOne(userroles.get(0).getRoleId());
		}else{
			return null;
		}
	}
	
	
	public Page<User> getUsers(int pageIndex, int pageSize) {
		PageRequest page = new PageRequest(pageIndex, pageSize, User.getSortASC("loginName"));
		return this.userDao.findAll(page);
	}

	public boolean checkPassword(User user) {
		User olduser = this.userDao.findByLoginName(user.getLoginName());
		if(olduser !=null){
			return olduser.getPassword().equals(MD5Util.getMD5String(user.getPassword()));
		}
		return false;
	}


	

	public List<String> getShiroPermissons(String userid) {
		List<String> strs = new ArrayList<String>();
		List<String>		roleids = this.userRoleDao.findRolesByUserId(userid);
		if(roleids == null)		return strs;
		List<String> permissionids = this.rolePermissionDao.findPermissionsByRoleids(roleids);
		
		List<Permission>	 permissions = this.permissionDao.findAllByIdIn(permissionids);
		
		for (Permission permission : permissions) {
			if(permission.getResourceCode() != null && permission.getOperationCode() != null)
				strs.add(permission.getResourceCode()+":"+permission.getOperationCode());
		}
		return strs;
	}


	public String getAdminPassword() {
		// TODO Auto-generated method stub
		User user = this.userDao.findOne("1");
		return user.getPassword();
	}
	
	public ReInfo loginAdmin(String username,String password){
		ReInfo  ri =  ReInfo.getFailed();
		if(username == null){
			ri.message = "用户登录名不能为空！";
			return ri;
		}
		if(password == null){
			ri.message = "用户的登录密码不能为空!";
			return ri;
		}
		User user = this.userDao.findByLoginName(username);
		if(user == null){
			ri.message = "用户登录名错误！";
			return ri;
		}
		
		if(!user.getPassword().equals(password)){
			ri.message = "用户的登录密码错误！";
			return ri;
		}
		
		//需要判断用户角色是否为管理员
		
		return  ReInfo.getSucceed();
	}





}
