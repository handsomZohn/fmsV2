package com.casic.fms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.casic.fms.bean.FileLog;
import com.casic.fms.bean.OrgInfoBean;
import com.casic.fms.bean.OrgUSBKeyInfo;
import com.casic.fms.bean.Permission;
import com.casic.fms.bean.ReInfo;
import com.casic.fms.bean.Role;
import com.casic.fms.bean.RolePermission;
import com.casic.fms.bean.USBKeyInfo;
import com.casic.fms.bean.UserRole;
import com.casic.fms.dao.FileLogDao;
import com.casic.fms.dao.OrgInfoDao;
import com.casic.fms.dao.OrgUSBKeyInfoDao;
import com.casic.fms.dao.PermissionDao;
import com.casic.fms.dao.RoleDao;
import com.casic.fms.dao.RolePermissionDao;
import com.casic.fms.dao.USBKeyInfoDao;
import com.casic.fms.dao.UserRoleDao;
import com.casic.fms.service.AuthorizeService;

/**
 * 安全相关实体的管理类,包括用户和权限组.
 * 
 * @author crazylion
 */
@Component
@Transactional(readOnly = false)
public class AuthorizeServiceImpl extends BaseServiceImp implements AuthorizeService{


	private static Logger logger = LoggerFactory.getLogger(AuthorizeServiceImpl.class);
	private RoleDao 				roleDao;
	private PermissionDao		permissionDao;
	private UserRoleDao			userRoleDao;
	private RolePermissionDao	rolePermissionDao;
	private OrgInfoDao			orgInfoDao;
	private USBKeyInfoDao		usbKeyInfoDao;
	private OrgUSBKeyInfoDao		orgUSBKeyInfoDao;
	private FileLogDao			fileLogDao;
	




	@Autowired
	public void setFileLogDao(FileLogDao fileLogDao) {
		this.fileLogDao = fileLogDao;
	}

	@Autowired
	public void setOrgUSBKeyInfoDao(OrgUSBKeyInfoDao orgUSBKeyInfoDao) {
		this.orgUSBKeyInfoDao = orgUSBKeyInfoDao;
	}

	@Autowired
	public void setUsbKeyInfoDao(USBKeyInfoDao usbKeyInfoDao) {
		this.usbKeyInfoDao = usbKeyInfoDao;
	}
	
	@Autowired
	public void setOrgInfoDao(OrgInfoDao orgInfoDao) {
		this.orgInfoDao = orgInfoDao;
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
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	

	public Role saveRole(Role role,String permissionids) {
		// TODO Auto-generated method stub
		role =  this.roleDao.save(role);
		if(Role.ROOT.equals(role.getCode())){
			List<Permission>  pers = this.getAllPermissions();
			List<RolePermission>	 rps = new ArrayList<RolePermission>();
			for (Permission per : pers) {
				rps.add(new RolePermission(role.getId(),per.getId()));
			}
			this.rolePermissionDao.deleteByRoleId(role.getId());
			this.rolePermissionDao.save(rps);
			return role;
		}
		if(permissionids != null && permissionids.trim().length()>0){
			String[] ids = permissionids.split(",");
			List<RolePermission>	 rps = new ArrayList<RolePermission>();
			for(int i=0; i<ids.length; i++){
				rps.add(new RolePermission(role.getId(), ids[i]));
			}
			this.rolePermissionDao.deleteByRoleId(role.getId());
			this.rolePermissionDao.save(rps);
		}
		return role;
	}

	public Role getRole(String id) {
		// TODO Auto-generated method stub
		return this.roleDao.findOne(id);
	}
	public ReInfo deleteRole(Role role) {
		// TODO Auto-generated method stub
		ReInfo reinfo =new ReInfo();
		reinfo.setCode("false");
		role = this.roleDao.findOne(role.getId());
		if(!Role.ROOT.equals(role.getCode())){
			this.roleDao.delete(role.getId());
			reinfo.setCode("true");
		}else{
			reinfo.setMessage(this.getMessage("role.system.cannotdelete"));
		}
		return reinfo;	}



	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return this.roleDao.findAll();
	}

	

	public List<Role> saveUserRoles(List<Role> roles) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Permission> getAllPermissions() {
		// TODO Auto-generated method stub
		return permissionDao.findAll(Permission.getSortASC("resourceCode"));
	}

	public List<UserRole> getUserRoles(String userid) {
		// TODO Auto-generated method stub
		return this.userRoleDao.findByUserId(userid);
	}



	public Permission getPermission(String id) {
		// TODO Auto-generated method stub
		return this.permissionDao.findOne(id);
	}


	public Permission savePermission(Permission per) {
		// TODO Auto-generated method stub
		if(per.getResourceCode()!= null && per.getOperationCode()!=null && !"".equals(per.getResourceCode().trim()) && !"".equals(per.getOperationCode().trim())){
			Permission oldper = this.permissionDao.findOneByResourceCodeAndOperationCode(per.getResourceCode(), per.getOperationCode());
			if(oldper != null){
				oldper.setOperationName(per.getOperationName());
				oldper.setResourceName(per.getResourceName());
				return this.permissionDao.save(oldper);
			}else{
				return this.permissionDao.save(per);
			}
		}
		return per;
	}



	public ReInfo deletePermission(String id) {
		// TODO Auto-generated method stub
		this.permissionDao.delete(id);;
		return ReInfo.getSucceed();
	}

	public List<String> getRolePermission(String roleid) {
		// TODO Auto-generated method stub
		List<String>  roleids = new ArrayList<String>();
		roleids.add(roleid);
		return this.rolePermissionDao.findPermissionsByRoleids(roleids);
	}

	public OrgInfoBean saveOrgInfo(OrgInfoBean orginfo) {
		// TODO Auto-generated method stub
		if(orginfo.getParentId() != null && "".equals(orginfo.getParentId().trim())){
			orginfo.setParentId(null);
		}
		return this.orgInfoDao.save(orginfo);
	}

	public ReInfo removeOrgInfo(String id) {
		List<OrgInfoBean> childs = this.orgInfoDao.findByParentId(id);
		if(childs != null && childs.size()>0){
			ReInfo ri = ReInfo.getFailed();
			ri.setMessage("存在下级部门，不能删除！");
			return ri;
		}
		this.orgInfoDao.delete(id);
		return ReInfo.getSucceed();
	}

	public List<OrgInfoBean> getChildsByParent(String parentid) {
		// TODO Auto-generated method stub
		return this.orgInfoDao.findByParentId(parentid);
	}

	public List<OrgInfoBean> getTopOrgInfoes() {
		// TODO Auto-generated method stub
		return this.orgInfoDao.findByParentIdIsNull();
	}

	public OrgInfoBean getOrgInfo(String id) {
		// TODO Auto-generated method stub
		return this.orgInfoDao.findOne(id);
	}
	/**
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<USBKeyInfo>	getUSBKeyInfoes(Integer pageIndex,Integer pageSize){
		return null;
	}

	public List<USBKeyInfo> searchUSBKey(String username, String usbkey,
			String orginfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<USBKeyInfo> getNoOrgUSBKeyByOrgID(String orgid) {
		List<USBKeyInfo>		listAllUSBKey = this.usbKeyInfoDao.findAll(USBKeyInfo.getSortASC("usbkey"));
		List<String>		listOrgUSB = null;
		if(orgid == null ){
			listOrgUSB = this.orgUSBKeyInfoDao.findAllUSBKey();
		}else{
			OrgInfoBean orgInfo = this.orgInfoDao.findOne(orgid);
			listOrgUSB = this.orgUSBKeyInfoDao.getUSBKeyByOrg(orgInfo.getCode());
		}
		for(int i=0; i<listAllUSBKey.size();){
			if(listAllUSBKey.get(i).getUsbkey()== null || "".equals(listAllUSBKey.get(i).getUsbkey())){
				listAllUSBKey.remove(i);
				continue;
			}
			if(listOrgUSB.contains(listAllUSBKey.get(i).getUsbkey())){
				listAllUSBKey.remove(i);
				continue;
			}
			i++;
		}
		return listAllUSBKey;
	}

	public List<USBKeyInfo> getNoSecurityUSBKey() {
		// TODO Auto-generated method stub
		return this.usbKeyInfoDao.findByReadSecurityIsNullOrSaveSecurityIsNull();
	}

	public USBKeyInfo getUSBKey(String usbkey) {
		// TODO Auto-generated method stub
		USBKeyInfo key =  this.usbKeyInfoDao.findByUsbkey(usbkey);
		if(key == null){
			String eusbkey = this.fileLogDao.findOneByUsbkey(usbkey);
			if(eusbkey != null){
				key = new USBKeyInfo();
				key.setUsbkey(eusbkey);
				key.setUsername(eusbkey);
				return saveUSBKey(key);
			}
		}
		return key;
	}
	
	public USBKeyInfo saveUSBKey(USBKeyInfo usbkey) {
		USBKeyInfo oldusbkeyinfo = this.usbKeyInfoDao.findByUsbkey(usbkey.getUsbkey());
		if(oldusbkeyinfo != null){
			oldusbkeyinfo.setComments(usbkey.getComments());
			oldusbkeyinfo.setReadSecurity(usbkey.getReadSecurity());
			oldusbkeyinfo.setSaveSecurity(usbkey.getSaveSecurity());
			oldusbkeyinfo.setUsername(usbkey.getUsername());
			return this.usbKeyInfoDao.save(oldusbkeyinfo);
		}
		return this.usbKeyInfoDao.save(usbkey);
	}

	public USBKeyInfo updateUSBKeyInfo(USBKeyInfo usbkeyinfo) {
		// TODO Auto-generated method stub
		return this.usbKeyInfoDao.save(usbkeyinfo);
	}

	public List<OrgUSBKeyInfo> getUSBKeyInfoByOrgID(String orgId) {
		OrgInfoBean orginfo = this.orgInfoDao.findOne(orgId);
		return this.orgUSBKeyInfoDao.findByOrginfoCodeLike(orginfo.getCode());
	}

	public String getOrgFullName(String id){
		StringBuffer strFullName = new StringBuffer();
		OrgInfoBean oib = this.orgInfoDao.findOneByCode(id);
		strFullName.append(oib.getName());
		while(oib.getParentId() != null || "".equals(oib.getParentId())){
			oib = this.orgInfoDao.findOne(oib.getParentId());
			strFullName.insert(0, oib.getName()+"->");
		}
		return strFullName.toString();
	}
	
	public ReInfo assignUSBKey(String orgID, String usbkeys) {
		// TODO Auto-generated method stub
		OrgInfoBean oib = this.orgInfoDao.findOne(orgID);
		String[] arrayUsbkey = usbkeys.split(",");
		if(usbkeys==null || usbkeys.equals(",")){
			return ReInfo.getSucceed();
		}
		for(int i=0; i<arrayUsbkey.length; i++){
			OrgUSBKeyInfo uki = new OrgUSBKeyInfo();
			uki.setOrginfoCode(oib.getCode());
			uki.setUsbkey(arrayUsbkey[i]);
			uki.setRole(OrgUSBKeyInfo.ROLE_EMPLOYEE);
			this.orgUSBKeyInfoDao.save(uki);
		}
		return ReInfo.getSucceed();
	}

	public OrgUSBKeyInfo findOrgUSBKeyInfo(String id) {
		// TODO Auto-generated method stub
		return this.orgUSBKeyInfoDao.findOne(id);
	}

	public void deleteOrgUSBKeyInfo(String id) {
		// TODO Auto-generated method stub
		this.orgUSBKeyInfoDao.delete(id);
		return ;
	}

	public OrgInfoBean findOrgInfoByOrgCode(String orgcode) {
		// TODO Auto-generated method stub
		return this.orgInfoDao.findOneByCode(orgcode);
	}

	public OrgUSBKeyInfo updateOrgUSBKeyRole(String id) {
		// TODO Auto-generated method stub
		OrgUSBKeyInfo ouki = this.orgUSBKeyInfoDao.findOne(id);
		if(ouki.getRole().equals(OrgUSBKeyInfo.ROLE_EMPLOYEE)){
			ouki.setRole(OrgUSBKeyInfo.ROLE_LEADER);
		}else{
			ouki.setRole(OrgUSBKeyInfo.ROLE_EMPLOYEE);
		}
		this.orgUSBKeyInfoDao.save(ouki);
		return ouki;
	}

	public List<OrgInfoBean> getAllOrginfo(boolean isOptions) {
		// TODO Auto-generated method stub
		List<OrgInfoBean> list =  this.orgInfoDao.findAll(OrgInfoBean.getSortASC("code"));
		if(isOptions){
			OrgInfoBean orginfo = new OrgInfoBean();
			orginfo.setCode("");
			orginfo.setName("整个公司");
			list.add(0, orginfo);
		}
		return list;
	}
	
	
}
