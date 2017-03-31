package com.yuki.shiro.realms;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

import com.yuki.account.dto.User;
import com.yuki.account.service.UserService;

public class ShiroRealm extends AuthenticatingRealm {
	@Resource
	UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("doGetAuthenticationInfo:" + token);

		// 1.把AuthenticationToken转化为UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2.从UsernamePasswordToken获取username
		String username = upToken.getUsername();

		// 3.调用数据库的方法，从数据库中查询username,对应的信息
		User user = userService.getUserByUsername(username);

		// 4.如果用户不存在，则可以抛出UnknownAccountException
		if (user == null) {
			throw new UnknownAccountException("用户不存在!");
		}

		// 5.根据用户信息的情况决定是否抛出 用户被锁定异常

		// 6.根据用户信息 构建
		Object principal = user;// 认证信息，可以是username，也可以是数据表中对应的实体类对象如user
		Object credentials = user.getPassword();// 数据库中的密码
		String realmName = getName();// 当前realm的name
		ByteSource credentialsSalt =ByteSource.Util.bytes(username);//用用户的username作为盐值进行盐值加密

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info;
	}
}
