package com.demo.springbootshirodemo.service.impl;

import com.demo.springbootshirodemo.dao.SysRoleMenuMapper;
import com.demo.springbootshirodemo.entity.SysRoleMenu;
import com.demo.springbootshirodemo.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：kkorkk.
 * @Date：2018/10/29 9:49
 * @Description：系统角色与菜单关联关系业务接口实现类
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenu> selectByRoleId(Integer roleId) {
        return sysRoleMenuMapper.selectByRoleId(roleId);
    }
}
