
package com.casic.fms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.casic.fms.bean.Group;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.Role;
import com.casic.fms.bean.User;
import com.casic.fms.bean.UserRole;

public interface AuthenticationService {
	
	public  String getAdminPassword();
	
	
	public Role getUserRoleByUserid(String userid);
	/**
	 * 检测用户密码是否正确
	 * @param user
	 * @return
	 */
	
	public boolean checkPassword(User user);
	
	/**
	  * 方法功能：根据用户Id获得用户详细信息. <BR>
	  * @param id
	  * @return
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public User getUser(String id);
	


	/**
	  * 方法功能：保存用户信息. <BR>
	  * @param entity
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public User saveUser(User entity, UserRole userRole);
	/**
	  * 方法功能：根据ID删除用户. <BR>
	  * @param id
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public ReInfo deleteUser(String id);
	/**
	  * 方法功能：获得所有用户信息. <BR>
	  * @return
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public List<User> getAllUser();
	
	/**
	 * 获取分页的用户信息
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<User>	getUsers(int pageIndex,int pageSize);
	/**
	  * 方法功能：根据用户登录名获取用户信息. <BR>
	  * @param loginName
	  * @return
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public User findUserByLoginName(String loginName);
	/**
	 * 方法功能：根据用户邮箱获取用户信息. <BR>
	 * @param email
	 * @return
	 * @see com.casic.fms.service.AuthenticationService
	 */
	public User findUserByEmail(String email);
	/**
	  * 方法功能：获得用户组信息. <BR>
	  * @param id
	  * @return
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public Group getGroup(String id);
	/**
	  * 方法功能：获得所有用户组信息. <BR>
	  * @return
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public List<Group> getAllGroup();
	/**
	  * 方法功能：保存用户组信息. <BR>
	  * @param entity
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public void saveGroup(Group entity);
	/**
	  * 方法功能：删除用户组信息. <BR>
	  * @param id
	  * @see com.casic.fms.service.AuthenticationService
	  * @since 
	  */
	public void deleteGroup(String id);
	

	/**
	 * 获取用户的权限信息
	 * @param userid
	 * @return
	 */
	public List<String>	getShiroPermissons(String userid);

	
	public ReInfo loginAdmin(String username,String password);
}
