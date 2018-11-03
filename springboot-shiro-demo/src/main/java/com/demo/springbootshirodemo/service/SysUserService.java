package com.demo.springbootshirodemo.service;

import com.demo.springbootshirodemo.dao.SysUserMapper;
import com.demo.springbootshirodemo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：kkorkk.
 * @Date：2018/10/27 10:48
 * @Description：用户信息业务处理类
 */
public interface SysUserService {

    SysUser findByUsername(String username);

    int save(SysUser sysUser);

}
