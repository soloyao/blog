package com.zmy.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zmy.pojo.User;
//import com.zmy.service.PermissionService;
import com.zmy.service.RoleService;
import com.zmy.service.UserService;

public class DatabaseRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
//	@Autowired
//	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 能进入到这里，表示账号已经通过验证了
		String userName = (String) principalCollection.getPrimaryPrincipal();
		// 通过service获取角色和权限
//		Set<String> permissions = permissionService.listPermissions(userName);
		Set<String> roles = roleService.listRolesByUserName(userName);

		// 授权对象
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		// 把通过service获取到的角色和权限放进去
//		s.setStringPermissions(permissions);
		System.out.println(roles);
		s.setRoles(roles);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取账号密码
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String userName = t.getPrincipal().toString();
		String password = new Md5Hash(new String(t.getPassword())).toString();
		// 获取数据库中的密码
		User user = userService.getByName(userName);
		String passwordInDB = user.getPassword();
		if (null == passwordInDB || !passwordInDB.equals(password)) {
			throw new AuthenticationException();
		}
//		String salt = user.getSalt();
//		String salt = "1";
		// 认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
		// 盐也放进去
		// 这样通过applicationContext-shiro.xml里配置的 HashedCredentialsMatcher 进行自动校验
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, password, getName());
		return a;
	}

}