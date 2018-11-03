package com.demo.springbootshirodemo.service.impl;

import com.demo.springbootshirodemo.dao.SysRoleMapper;
import com.demo.springbootshirodemo.entity.SysRoleMenu;
import com.demo.springbootshirodemo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：kkorkk.
 * @Date：2018/10/29 9:52
 * @Description：系统角色业务接口实现类
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

}
