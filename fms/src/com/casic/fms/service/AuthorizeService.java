package com.casic.fms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.casic.fms.bean.OrgInfoBean;
import com.casic.fms.bean.OrgUSBKeyInfo;
import com.casic.fms.bean.Permission;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.Role;
import com.casic.fms.bean.USBKeyInfo;
import com.casic.fms.bean.UserRole;

public interface AuthorizeService {

	
	/**
	 * 增加角色信息
	 * @param role
	 * @return
	 */
	Role saveRole(Role role,String permissionids);
	
	/**
	 * 删除角色
	 * @param role
	 */
	ReInfo deleteRole(Role role);
	
	
	/**
	 * 获取角色已经分配的资源操作权限
	 * @param roleid
	 * @return
	 */
	List<String>	getRolePermission(String roleid);
	
	/**
	 * 获取所有的角色信息
	 * @return
	 */
	List<Role>	getRoles();

	
	Role getRole(String id);	
	/**
	 * 获取用户的角色列表
	 * @param userid
	 * @return
	 */
	 List<UserRole> 	getUserRoles(String userid);
	
	/**
	 * 保存用户角色
	 * @param roles
	 * @return
	 */
	List<Role>	saveUserRoles(List<Role>	roles);

	/**
	 * 获取系统定义的所有功能信息
	 * @return
	 */
	List<Permission>		getAllPermissions();
	
	
	/**
	 * 获取功能权限定义
	 * @param id
	 * @return
	 */
	Permission	getPermission(String id);
	
	/**
	 * 保存功能权限
	 * @param per
	 * @return
	 */
	public Permission savePermission(Permission per);
	
	/**
	 * 删除功能权限信息
	 * @param id
	 * @return
	 */
	public ReInfo deletePermission(String  id);
	
	/**
	 * 增加组织结构信息
	 * @param orginfo
	 * @return
	 */
	public OrgInfoBean saveOrgInfo(OrgInfoBean orginfo);

	/**
	 * 删除部门信息
	 * @param id
	 * @return
	 */
	public ReInfo removeOrgInfo(String id);
	
	/**
	 * 获取部门信息
	 * @param id
	 * @return
	 */
	public OrgInfoBean getOrgInfo(String id);
	/**
	 * 获取下级部门信息
	 * @param parentid
	 * @return
	 */
	public List<OrgInfoBean>	getChildsByParent(String parentid);
	
	
	public List<OrgInfoBean>		getAllOrginfo(boolean isOptions);
		
	/**
	 * 获取一级部门信息
	 * @return
	 */
	public List<OrgInfoBean>		getTopOrgInfoes();
	

	/**
	 * 检索USBkey信息
	 * @param username
	 * @param usbkey
	 * @param orginfoId
	 * @return
	 */
	public List<USBKeyInfo>  searchUSBKey(String username,String usbkey,String orginfoId);
	/**
	 * 获取USBKey列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<USBKeyInfo>	getUSBKeyInfoes(Integer pageIndex,Integer pageSize);
	
	/**
	 * 获取没有指定部门的USBKey用户信息
	 * @return
	 */
	public List<USBKeyInfo>	getNoOrgUSBKeyByOrgID(String orgId);
	
	/**
	 * 根据部门信息获取USBKey用户信息
	 * @param orgId
	 * @return
	 */
	public List<OrgUSBKeyInfo> getUSBKeyInfoByOrgID(String orgId);
	
	/**
	 * 获取没有指定密级信息的USBKey用户
	 * @return
	 */
	public List<USBKeyInfo>  getNoSecurityUSBKey();
	
	
	public USBKeyInfo 	getUSBKey(String usbkey);
	
	public USBKeyInfo 	updateUSBKeyInfo(USBKeyInfo usbkeyinfo);
	
	public USBKeyInfo saveUSBKey(USBKeyInfo usbkey);
	
	public ReInfo  assignUSBKey(String orgID,String usbkeys);
	
	public String getOrgFullName(String id);
	
	public OrgUSBKeyInfo  findOrgUSBKeyInfo(String id);
	
	public void  deleteOrgUSBKeyInfo(String id);
	
	public OrgInfoBean  findOrgInfoByOrgCode(String orgcode);
	
	public OrgUSBKeyInfo  updateOrgUSBKeyRole(String id);
}	
