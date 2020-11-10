package com.yuekehoutai.shiro;

import java.util.List;

import javax.annotation.Resource;

import com.yuekehoutai.domain.Menu;
import com.yuekehoutai.domain.Worker;
import com.yuekehoutai.service.WorkerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;




public class WorkerRealm extends AuthorizingRealm{
	@Resource
	private WorkerService workersService;
	/*
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Worker worker =  (Worker) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		try {
			List<Menu> permissions = workersService.selectWokerPermissions(worker);
			for(Menu permission:permissions) {
				simpleAuthorizationInfo.addStringPermission(permission.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return simpleAuthorizationInfo;
	}
	/*
	 * 登陆
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String tel=(String)token.getPrincipal();//取出令牌中的用户的电话号码
		try {
			Worker worker = workersService.selectByWorkerName(tel);
			if(worker!=null) {
				//封装认证信息  shiro会使用这个对象进行后续的密码比对
				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(worker,worker.getPassword(),getName());
				return authenticationInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
