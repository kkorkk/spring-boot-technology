package com.demo.springbootshirodemo;

import com.demo.springbootshirodemo.dao.UserInfoRepository;
import com.demo.springbootshirodemo.entity.SysPermission;
import com.demo.springbootshirodemo.entity.SysRole;
import com.demo.springbootshirodemo.entity.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        for(SysRole role : userInfo.getRoleList()){
            simpleAuthorizationInfo.addRole(role.getRole());
            for(SysPermission sysPermission : role.getPermissionList()){
                simpleAuthorizationInfo.addStringPermission(sysPermission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始认证");
        String username = (String)authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if(userInfo == null){
            throw new UnknownAccountException("用户不存在");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSalt()),
                getName()
        );
        return simpleAuthenticationInfo;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName("md5");
        shaCredentialsMatcher.setHashIterations(1);
        shaCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

}
