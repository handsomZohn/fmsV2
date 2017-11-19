package com.casic.fms.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casic.fms.bean.User;
import com.casic.fms.service.AuthenticationService;

/**
 * 自实现用户与权限查询.
 * 演示关系，密码用明文存储，因此使用默认 的SimpleCredentialsMatcher.
 */
@Component
public class ShiroDbRealm extends AuthorizingRealm {

	private AuthenticationService authenticationService;

	/**
	 * 认证回调函数, 登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = authenticationService.findUserByLoginName(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user, user.getPassword(),getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User userShiro = (User) principals.getPrimaryPrincipal();
		User user = authenticationService.findUserByLoginName(userShiro.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.addStringPermissions(authenticationService.getShiroPermissons(user.getId()));
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	@Autowired
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

//	/**
//	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
//	 */
//	public static class ShiroUser implements Serializable {
//
//		private static final long serialVersionUID = -1748602382963711884L;
//		private String loginName;
//		private String name;
//
//		public ShiroUser(String loginName, String name) {
//			this.loginName = loginName;
//			this.name = name;
//		}
//
//		public String getLoginName() {
//			return loginName;
//		}
//
//		/**
//		 * 本函数输出将作为默认的<shiro:principal/>输出.
//		 */
//		@Override
//		public String toString() {
//			return loginName;
//		}
//
//		public String getName() {
//			return name;
//		}
//	}
}
