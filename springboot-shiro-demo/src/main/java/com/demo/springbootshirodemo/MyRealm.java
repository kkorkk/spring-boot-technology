package com.demo.springbootshirodemo;

import com.demo.springbootshirodemo.common.CoreConstants;
import com.demo.springbootshirodemo.entity.SysMenu;
import com.demo.springbootshirodemo.entity.SysRole;
import com.demo.springbootshirodemo.entity.SysRoleMenu;
import com.demo.springbootshirodemo.entity.SysUser;
import com.demo.springbootshirodemo.service.SysMenuService;
import com.demo.springbootshirodemo.service.SysRoleMenuService;
import com.demo.springbootshirodemo.service.SysUserRoleService;
import com.demo.springbootshirodemo.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Autowired
    SysMenuService sysMenuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUser userInfo = (SysUser) principalCollection.getPrimaryPrincipal();
        List<SysRole> sysRoleList = sysUserRoleService.selectByUserId(userInfo.getuId());
        List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
        sysRoleList.stream().forEach(sysRole -> {
            //获取每个角色对应的菜单
            sysRoleMenuList.addAll(sysRoleMenuService.selectByRoleId(sysRole.getRoleId()));
        });
        //从菜单列表中抽取出权限信息
        List<String> permissionsList = new ArrayList<>();
        sysRoleMenuList.stream()
                .map(sysRoleMenu -> sysRoleMenu.getMenuId())
                .forEach(menuId->{
                    permissionsList.add(sysMenuService.getById(menuId).getPermissions());
                });
        simpleAuthorizationInfo.addStringPermissions(permissionsList);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始认证");
        String username = (String)authenticationToken.getPrincipal();
        System.out.println(MessageFormat.format("用户{0}在登录",username));
        SysUser sysUser = sysUserService.findByUsername(username);
        if(sysUser == null){
            throw new UnknownAccountException("用户不存在");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                sysUser,
                sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getSalt()),
                getName()
        );
        return simpleAuthenticationInfo;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(CoreConstants.PASSWORD_ALGORITHM_NAME);
        shaCredentialsMatcher.setHashIterations(CoreConstants.PASSWORD_HASH_ITERATIONS);
        shaCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

}
