package com.demo.springbootshirodemo.service.impl;

import com.demo.springbootshirodemo.dao.SysUserRoleMapper;
import com.demo.springbootshirodemo.entity.SysRole;
import com.demo.springbootshirodemo.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：kkorkk.
 * @Date：2018/10/29 9:53
 * @Description：系统用户角色信息业务接口实现类
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRole> selectByUserId(Integer userId) {
        return sysUserRoleMapper.selectByUserId(userId);
    }
}
