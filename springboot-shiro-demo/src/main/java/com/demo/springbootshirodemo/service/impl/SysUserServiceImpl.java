package com.demo.springbootshirodemo.service.impl;

import com.demo.springbootshirodemo.dao.SysUserMapper;
import com.demo.springbootshirodemo.entity.SysUser;
import com.demo.springbootshirodemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：kkorkk.
 * @Date：2018/10/29 9:43
 * @Description：用户信息业务接口实现类
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public int save(SysUser sysUser) {
        return sysUserMapper.insertSelective(sysUser);
    }
}
